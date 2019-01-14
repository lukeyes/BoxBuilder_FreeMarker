package com.lukeyes.boxbuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukeyes.boxbuilder.boxtypes.*;
import com.lukeyes.graphics.IBox;
import com.lukeyes.graphics.ImagePanel;
import com.lukeyes.graphics.SVGObject;

public class BoxBuilder_FreeMarker extends JFrame implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = -983922208537044586L;

    private ImagePanel m_imagePanel;

    private IBox m_box;

    private JMenuBar m_menuBar;
    private JMenuItem m_menuItemNew;
    private JMenuItem m_menuItemSave;
    private JMenuItem m_menuItemOpen;
    private JMenuItem m_menuItemExport;

    private GeneratorOptions m_options;

    private File m_lastFile = null;

    private Font m_font = null;

    BoxBuilder_FreeMarker() {

        init();
    }

    private void init() {

        m_font = new Font(Font.SANS_SERIF,Font.PLAIN,36);
        m_imagePanel = new ImagePanel();

        // Add the buttons and the log to this panel.
        getContentPane().add(m_imagePanel, BorderLayout.CENTER);

        m_options = generateOptions();

        //createNewBox(options);

        m_menuItemSave = createMenuItem("Save");
        m_menuItemNew = createMenuItem("New");
        m_menuItemOpen = createMenuItem("Open");
        m_menuItemExport = createMenuItem("Export");

        //Build second menu in the menu bar.
        JMenu menu = new JMenu("File");
        menu.add(m_menuItemNew);
        menu.add(m_menuItemSave);
        menu.add(m_menuItemOpen);
        menu.add(m_menuItemExport);
        menu.setFont(m_font);

        m_menuBar = new JMenuBar();
        m_menuBar.add(menu);
        this.setJMenuBar(m_menuBar);
    }

    private JMenuItem createMenuItem(String text){
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.addActionListener(this);
        menuItem.setFont(m_font);

        return menuItem;
    }

    private void createNewBox(GeneratorOptions options) {

        m_options = options;

        switch(m_options.boxType) {
            case ARDUINO:
                m_box = new ArduinoBox();
                break;
            case ARTIE_1:
                m_box = new ArtieBox();
                break;
            case ARTIE_2:
                m_box = new ArtieBox2();
                break;
            case ARTIE_3:
                m_box = new ArtieBox3();
                break;
            case ELECTRONICS:
                m_box = new ElectronicsBox();
                break;
            case FRITA:
                m_box = new FritaBox();
                break;
            case SABERTOOTH:
                m_box = new SabertoothBox();
                break;
        }

        m_box.generatePolygons(options);

        m_imagePanel.setBox(m_box);
    }

    private GeneratorOptions generateOptions() {

        GeneratorOptions options = new GeneratorOptions();

        options.boxType = BoxType.Type.FRITA;
        options.boxHeight = 8;
        options.boxLength = 8;
        options.boxWidth = 8;
        options.spacing = .5f;
        options.outerMaterialWidth = (float) 0.17;
        options.screwDiameter = 0.167F;
        options.screwLength = 1;
        options.lip = 2 * options.outerMaterialWidth;
        options.nutThickness = (float) 0.203;
        options.nutWidth = (float) 0.344;
        options.innerMaterialWidth = (float) 0.17;
        options.bottomNutSpacing = (float) 0.17;

        return options;

    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event dispatch thread.
     */
    private static void createAndShowGUI()
    {
        // Create and set up the window.
        JFrame frame = new BoxBuilder_FreeMarker();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                // Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());

        if( source == m_menuItemNew) {
            createNewFile();
        } else if( source == m_menuItemSave ) {
            saveFile();
        } else if( source == m_menuItemOpen ) {
            openFile();
        } else if( source == m_menuItemExport ) {
            exportFile();
        }

    }

    private void saveFile() {
        JFileChooser fileChooser = (m_lastFile == null) ? new JFileChooser() : new JFileChooser(m_lastFile);
        fileChooser.setDialogTitle("Specify a file to save");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            BufferedWriter writer;

            m_lastFile = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + m_lastFile.getAbsolutePath());

            try {
                writer = new BufferedWriter(new FileWriter(m_lastFile.getAbsolutePath()));

                ObjectMapper mapper = new ObjectMapper();
                String generatorOptionsAsString = mapper.writeValueAsString(m_options);
                writer.write(generatorOptionsAsString);
                writer.close();

                System.out.println(toSVG());

            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        }
    }

    private void exportFile() {
        JFileChooser fileChooser = (m_lastFile == null) ? new JFileChooser() : new JFileChooser(m_lastFile);
        fileChooser.setDialogTitle("Specify a file to export");

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            BufferedWriter writer;

            m_lastFile = fileChooser.getSelectedFile();

            try {
                writer = new BufferedWriter(new FileWriter(m_lastFile.getAbsolutePath()));

                String svgExportString = toSVG();


                writer.write(svgExportString);
                writer.close();

            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        }
    }

    private String toSVG() {

        Point2D.Float maxDimension = m_box.getMaxDimension();
        String svgString =
                String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"%fin\" height=\"%fin\" viewBox=\" 0 0 %f %f\">",
                        maxDimension.getX(), maxDimension.getY(), maxDimension.getX() * 90, maxDimension.getY() * 90);
        svgString = svgString.concat("\n");
        for( SVGObject svgObject : m_box.getSVGObjects()) {
            svgString = svgString.concat(svgObject.toSVG(90) + "\n");
        }
        svgString = svgString.concat("\n");

        svgString = svgString.concat("</svg>");

        return svgString;
    }

    private void openFile() {
        JFileChooser fileChooser = (m_lastFile == null) ? new JFileChooser() : new JFileChooser(m_lastFile);
        fileChooser.setDialogTitle("Specify a file to open");

        int userSelection = fileChooser.showOpenDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {

            BufferedReader reader;

            m_lastFile = fileChooser.getSelectedFile();
            System.out.println("Open file: " + m_lastFile.getAbsolutePath());

            try {
                reader = new BufferedReader(new FileReader(m_lastFile.getAbsolutePath()));

                ObjectMapper mapper = new ObjectMapper();


                StringBuilder builder = new StringBuilder();
                String aux = "";

                while ((aux = reader.readLine()) != null) {
                    builder.append(aux);
                }

                String text = builder.toString();
                reader.close();

                GeneratorOptions options = mapper.readValue(text, GeneratorOptions.class);
                createNewBox(options);


            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
        }
    }

    private JTextField newTextFieldFromFloat(float num) {
        JTextField textField = new JTextField(String.valueOf(num));
        textField.setFont(m_font);
        return textField;
    }

    private void createNewFile() {
        System.out.println("Popup menu here");

        JTextField boxLength = newTextFieldFromFloat(m_options.boxLength);
        JTextField boxWidth = newTextFieldFromFloat(m_options.boxWidth);
        JTextField boxHeight = newTextFieldFromFloat(m_options.boxHeight);
        JTextField thickness = newTextFieldFromFloat(m_options.outerMaterialWidth);
        JTextField spacing = newTextFieldFromFloat(m_options.spacing);
        JTextField screwLength = newTextFieldFromFloat(m_options.screwLength);
        JTextField screwDiameter = newTextFieldFromFloat(m_options.screwDiameter);
        JTextField lip = newTextFieldFromFloat(m_options.lip);
        JTextField nutThickness = newTextFieldFromFloat(m_options.nutThickness);
        JTextField nutWidth = newTextFieldFromFloat(m_options.nutWidth);
        JTextField innerMaterialWidth = newTextFieldFromFloat(m_options.innerMaterialWidth);
        JTextField bottomNutThickness = newTextFieldFromFloat(m_options.bottomNutSpacing);

        String[] boxTypes = BoxType.stringList();

//Create the combo box, select item at index 4.
//Indices start at 0, so 4 specifies the pig.
        JComboBox boxList = new JComboBox(boxTypes);
        boxList.setFont(m_font);

        int index = BoxType.ordinal(m_options.boxType);
        boxList.setSelectedIndex(index);

        Object[] message = {
                "Box Length", boxLength,
                "Box Width", boxWidth,
                "Box Height", boxHeight,
                "Outer material width", thickness,
                "Inner Material Width", innerMaterialWidth,
                "Spacing", spacing,
                "Screw Length", screwLength,
                "Screw Diameter", screwDiameter,
                "Lip", lip,
                "Nut Thickness", nutThickness,
                "Nut Width", nutWidth,
                "Bottom nut spacing", bottomNutThickness,
                "Box Type", boxList
        };

        int option = JOptionPane.showConfirmDialog(this, message, "New File", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            System.out.println("Box Length: " + boxLength.getText());
            System.out.println("Box Width: " + boxWidth.getText());
            System.out.println("Box Width: " + boxLength.getText());
            System.out.println("Thickness: " + thickness.getText());
            System.out.println("Spacing: " + spacing.getText());
            System.out.println("Screw Length: " + screwLength.getText());
            System.out.println("Screw Diameter: " + screwDiameter.getText());
            System.out.println("Lip: " + lip.getText());
            System.out.println("Nut Thickness: " + nutThickness.getText());
            System.out.println("Nut Width: " + nutWidth.getText());
            System.out.println("Box Type: " + boxList.getSelectedItem().toString());

            GeneratorOptions options = new GeneratorOptions();
            options.boxLength = numFromTextField(boxLength);
            options.boxWidth = numFromTextField(boxWidth);
            options.boxHeight = numFromTextField(boxHeight);
            options.outerMaterialWidth = numFromTextField(thickness);
            options.spacing = numFromTextField(spacing);
            options.screwLength = numFromTextField(screwLength);
            options.screwDiameter = numFromTextField(screwDiameter);
            options.lip = numFromTextField(lip);
            options.nutThickness = numFromTextField(nutThickness);
            options.nutWidth = numFromTextField(nutWidth);
            options.innerMaterialWidth = numFromTextField(innerMaterialWidth);
            options.bottomNutSpacing = numFromTextField(bottomNutThickness);

            options.boxType = BoxType.fromString(boxList.getSelectedItem().toString());
            createNewBox(options);

        } else {
            System.out.println("New file canceled");
        }
    }

    private float numFromTextField(JTextField textField) {
        if( textField == null )
            return 0;

        try {
            Float number = Float.valueOf(textField.getText());
            return number.floatValue();
        } catch (NumberFormatException e) {
            return 0;
        }
    }


}
