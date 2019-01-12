package com.lukeyes.boxbuilder.boxtypes;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.boxbuilder.electronics.SectionFrontAndBack;
import com.lukeyes.boxbuilder.electronics.SectionLeftAndRight;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;


public class ElectronicsBox extends FritaBox {

    @Override
    public void generatePolygons(GeneratorOptions options) {
        super.generatePolygons(options);

        float x5 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight + options.lip;
        float y5 = 4 * options.spacing + 0 * options.boxWidth + 1 * options.boxLength + 2 * options.boxHeight - options.outerMaterialWidth;
        List<SVGObject> innerBoxPolys = generateInnerBox(options, new Point2D.Float(x5,y5));
        m_svgObjects.addAll(innerBoxPolys);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private List<SVGObject> generateInnerBox(GeneratorOptions options, Point2D.Float origin) {
        List<SVGObject> innerBoxPolygons = new ArrayList<>();
        Polygon innerBox = new Polygon();
        innerBox.setOrigin(origin);

        float lip = options.lip;
        float materialWidth = options.outerMaterialWidth;
        float innerHeight = options.boxLength - (2 * lip + 2 * materialWidth);
        float heightSection = innerHeight / 3;

        float innerWidth = (options.boxWidth - (2 * lip + 2 * materialWidth));
        float widthSection = innerWidth / 3;

        innerBox.addPoint(materialWidth, materialWidth);
        innerBox.addPoint(materialWidth + widthSection, materialWidth);
        innerBox.addPoint(materialWidth + widthSection, 0);
        innerBox.addPoint(materialWidth + 2 * widthSection, 0);
        innerBox.addPoint(materialWidth + 2 * widthSection, materialWidth);
        innerBox.addPoint(materialWidth + innerWidth, materialWidth);

        innerBox.addPoint(materialWidth  + innerWidth, materialWidth + heightSection);
        innerBox.addPoint(2 * materialWidth + innerWidth, materialWidth + heightSection);
        innerBox.addPoint(2 * materialWidth + innerWidth, materialWidth + 2 * heightSection);
        innerBox.addPoint(materialWidth + innerWidth, materialWidth + 2 * heightSection);
        innerBox.addPoint(materialWidth + innerWidth, materialWidth + innerHeight);

        innerBox.addPoint(materialWidth + 2 * widthSection, materialWidth + innerHeight);
        innerBox.addPoint(materialWidth + 2 * widthSection, 2 * materialWidth + innerHeight);
        innerBox.addPoint(materialWidth + widthSection, 2 * materialWidth + innerHeight);
        innerBox.addPoint(materialWidth + widthSection, materialWidth + innerHeight);
        innerBox.addPoint(materialWidth, materialWidth + innerHeight);

        innerBox.addPoint(materialWidth, materialWidth + 2 * heightSection);
        innerBox.addPoint(0, materialWidth + 2 * heightSection);
        innerBox.addPoint(0, materialWidth + heightSection);
        innerBox.addPoint(materialWidth, materialWidth + heightSection);

        innerBoxPolygons.add(innerBox);
        return innerBoxPolygons;
    }


    protected List<SVGObject> generateFrontAndBack(GeneratorOptions options,
                                                   float x1, float y1) {
        Section frontAndBack1 = new SectionFrontAndBack();
        frontAndBack1.generate(options, options.lip, new Point2D.Float(x1, y1));
        List<SVGObject> frontAndBack1Polys = frontAndBack1.getPolygons();
        return frontAndBack1Polys;
    }

    protected List<SVGObject> generateLeftAndRight(GeneratorOptions options, float x3, float y3) {
        Section leftAndRight1 = new SectionLeftAndRight();
        leftAndRight1.generate(options, options.lip, new Point2D.Float(x3,y3));
        return leftAndRight1.getPolygons();
    }
}
