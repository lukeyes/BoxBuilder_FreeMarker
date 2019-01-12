package com.lukeyes.boxbuilder.boxtypes;

import com.lukeyes.boxbuilder.*;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("SuspiciousNameCombination")
public class SabertoothBox extends Box {
    @Override
    public void generatePolygons(GeneratorOptions options) {
        m_svgObjects = new ArrayList<>();

        List<SVGObject> bottom = generateBottom(0.25f,0.25f,options);
        m_svgObjects.addAll(bottom);
        m_svgObjects.addAll(generateTop(1.0f,6.25f,options));
        m_svgObjects.addAll(generateFrontAndBack(7.25f,0.25f,options));
        m_svgObjects.addAll(generateLeftAndRight(7.25f,2.75f,options));

        m_svgObjects.addAll(generateFrontAndBack(7.25f,5.0f,options));
        m_svgObjects.addAll(generateLeftAndRight(7.25f,8.25f,options));
    }

    List<SVGObject> generateBottom(float x, float y, GeneratorOptions options) {

        List<SVGObject> svgObjects = new ArrayList<>();

        float materialWidth = options.outerMaterialWidth;

        svgObjects.add(Section.drawSquare(x,y,6.5f,4.5f));
        svgObjects.add(Section.drawSquare(x+1.0f,y+1.75f,materialWidth,1.0f));
        svgObjects.add(Section.drawSquare(x+5.5f-materialWidth, y+ 1.75f, materialWidth, 1.0f));
        svgObjects.add(Section.drawSquare(x+2.625f,y+0.25f,1.25f,materialWidth));
        svgObjects.add(Section.drawSquare(x+2.625f,y+4.25f-materialWidth,1.25f,materialWidth));

        return svgObjects;
    }

    List<SVGObject> generateTop(float x, float y, GeneratorOptions options) {
        List<SVGObject> svgObjects = new ArrayList<>();

        float materialWidth = options.outerMaterialWidth;

        svgObjects.add(Section.drawSquare(x,y,5.0f,4.5f));
        svgObjects.add(Section.drawSquare(x+0.25f, y+1.75f, materialWidth, 1.0f));
        svgObjects.add(Section.drawSquare(x+4.75f-materialWidth, y+1.75f, materialWidth, 1.0f));
        svgObjects.add(Section.drawSquare(x+1.875f, y+0.25f, 1.25f, materialWidth));
        svgObjects.add(Section.drawSquare(x+1.875f, y+4.25f-materialWidth, 1.25f, materialWidth));

        return svgObjects;
    }

    List<SVGObject> generateFrontAndBack(float x, float y, GeneratorOptions options) {
        List<SVGObject> svgObjects = new ArrayList<>();

        Polygon face = new Polygon();
        face.setOrigin(new Point2D.Float(x,y));

        float materialWidth = options.outerMaterialWidth;

        face.addPoint(materialWidth, materialWidth);
        face.addPoint(1.5f,materialWidth);
        face.addPoint(1.5f,0);
        face.addPoint(2.5f,0);
        face.addPoint(2.5f,materialWidth);
        face.addPoint(4.0f-materialWidth,materialWidth);
        face.addPoint(4.0f-materialWidth,0.75f);
        face.addPoint(4.0f,0.75f);
        face.addPoint(4.0f,1.25f);
        face.addPoint(4.0f-materialWidth,1.25f);
        face.addPoint(4.0f-materialWidth,2.0f-materialWidth);
        face.addPoint(2.5f,2.0f-materialWidth);
        face.addPoint(2.5f,2.0f);
        face.addPoint(1.5f,2.0f);
        face.addPoint(1.5f,2.0f-materialWidth);
        face.addPoint(materialWidth,2.0f-materialWidth);
        face.addPoint(materialWidth,1.25f);
        face.addPoint(0,1.25f);
        face.addPoint(0,0.75f);
        face.addPoint(materialWidth,0.75f);
        svgObjects.add(face);

        return svgObjects;
    }

    List<SVGObject> generateLeftAndRight(float x, float y, GeneratorOptions options) {
        List<SVGObject> svgObjects = new ArrayList<>();

        Polygon face = new Polygon();
        face.setOrigin(new Point2D.Float(x,y));

        float materialWidth = options.outerMaterialWidth;
        face.addPoint(0,materialWidth);
        face.addPoint(1.875f,materialWidth);
        face.addPoint(1.875f,0);
        face.addPoint(3.125f,0);
        face.addPoint(3.125f,materialWidth);
        face.addPoint(5.0f,materialWidth);
        face.addPoint(5.0f,2.0f-materialWidth);
        face.addPoint(3.125f,2.0f-materialWidth);
        face.addPoint(3.125f,2.0f);
        face.addPoint(1.875f,2.0f);
        face.addPoint(1.875f,2.0f-materialWidth);
        face.addPoint(0,2.0f-materialWidth);

        svgObjects.add(face);

        svgObjects.add(Section.drawSquare(x+0.25f,y+0.75f,materialWidth,0.5f));
        svgObjects.add(Section.drawSquare(x+4.75f-materialWidth,y+0.75f,materialWidth,0.5f));
        svgObjects.add(Section.drawSquare(x+1.0f,y+0.75f,3.0f,0.5f));

        return svgObjects;
    }




}
