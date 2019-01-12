package com.lukeyes.boxbuilder.boxtypes;

import com.lukeyes.boxbuilder.*;
import com.lukeyes.graphics.Circle;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ArduinoBox extends Box {

    @Override
    public void generatePolygons(GeneratorOptions options) {

        m_svgObjects = new ArrayList<SVGObject>();
        final List<SVGObject> left = generateLeftRight(0.25f,0,options);
        m_svgObjects.addAll(left);

        m_svgObjects.addAll(generateLeftRight(0.25f,2.75f,options));

        final List<SVGObject> front = generateFrontBack(0.25f,5.5f,options);
        m_svgObjects.addAll(front);

        m_svgObjects.addAll(generateFrontBack(0.25f,8.25f,options));

        final List<SVGObject> top = generateTop(6.875f,0,options);
        m_svgObjects.addAll(top);

        final List<SVGObject> bottom = generateBottom(6.25f, 5.0f, options);
        m_svgObjects.addAll(bottom);
    }

    private List<SVGObject> generateLeftRight(float x, float y, GeneratorOptions options) {
        List<SVGObject> polygons = new ArrayList<>();

        float materialWidth = options.outerMaterialWidth;

        Polygon face = new Polygon();
        face.setOrigin(new Point2D.Float(x, y));

        face.addPoint(materialWidth, materialWidth);
        face.addPoint(1.5f, materialWidth);
        face.addPoint(1.5f, 0);
        face.addPoint(2.5f,0);
        face.addPoint(2.5f, materialWidth);
        face.addPoint(4.0f-materialWidth, materialWidth);
        face.addPoint(4.0f-materialWidth, 1.0f);
        face.addPoint(4.0f, 1.0f);
        face.addPoint(4.0f, 1.5f);
        face.addPoint(4.0f - materialWidth, 1.5f);
        face.addPoint(4.0f - materialWidth, 2.5f - materialWidth);
        face.addPoint(2.5f, 2.5f-materialWidth);
        face.addPoint(2.5f, 2.5f);
        face.addPoint(1.5f, 2.5f);
        face.addPoint(1.5f, 2.5f-materialWidth);
        face.addPoint(materialWidth, 2.5f-materialWidth);
        face.addPoint(materialWidth, 1.5f);
        face.addPoint(0, 1.5f);
        face.addPoint(0, 1.0f);
        face.addPoint(materialWidth, 1.0f);
        polygons.add(face);

        return polygons;
    }

    List<SVGObject> generateFrontBack(float x, float y, GeneratorOptions options) {
        List<SVGObject> polygons = new ArrayList<SVGObject>();

        float materialWidth = options.outerMaterialWidth;

        Polygon face = new Polygon();

        face.setOrigin(new Point2D.Float(x,y));

        face.addPoint(0, materialWidth);
        face.addPoint(2.0f, materialWidth);
        face.addPoint(2.0f, 0.0f);
        face.addPoint(3.5f, 0);
        face.addPoint(3.5f, materialWidth);
        face.addPoint(5.5f, materialWidth);
        face.addPoint(5.5f, 2.5f-materialWidth);
        face.addPoint(3.5f, 2.5f-materialWidth);
        face.addPoint(3.5f, 2.5f);
        face.addPoint(2.0f, 2.5f);
        face.addPoint(2.0f, 2.5f-materialWidth);
        face.addPoint(0, 2.5f-materialWidth);
        polygons.add(face);

        Polygon hole1 = new Polygon();
        hole1 = Section.drawSquare(x + 0.25f, y + 1.0f, materialWidth, 0.5f);
        polygons.add(hole1);

        Polygon hole2 = Section.drawSquare(x + 5.25f - materialWidth, y + 1.0f, materialWidth, 0.5f);
        polygons.add(hole2);

        float midWidth = 2.0f/2.0f;
        float midHeight = materialWidth/2.0f;
        float cx1 = x + midWidth;
        float cy1 = y+ 0.25f + midHeight;
        float cx2 = x + 2.0f+1.5f+midWidth-options.screwDiameter;
        Polygon cross1 = Section.generateSouthCross(options, new Point2D.Float(cx1,y+materialWidth));
        polygons.add(cross1);

        Polygon cross2 = Section.generateSouthCross(options, new Point2D.Float(cx2,y+materialWidth));
        polygons.add(cross2);

        return polygons;
    }

    List<SVGObject> generateTop(float x, float y, GeneratorOptions options) {
        List<SVGObject> polygons = new ArrayList<SVGObject>();

        float materialWidth = options.outerMaterialWidth;

        polygons.add(Section.drawSquare(x,y,5.5f,4.5f));
        polygons.add(Section.drawSquare(x+0.25f, y+1.75f, materialWidth, 1.0f));
        polygons.add(Section.drawSquare(x+5.25f-materialWidth, 1.75f, materialWidth, 1.0f));
        polygons.add(Section.drawSquare(x+2.0f, y+0.25f, 1.5f, materialWidth));
        polygons.add(Section.drawSquare(x+2.0f, y+4.25f-materialWidth, 1.5f, materialWidth));

        float midWidth = 2.0f/2.0f;
        float midHeight = materialWidth/2.0f;
        float cx1 = x + midWidth;
        float cy1 = y+ 0.25f + midHeight;
        float cx2 = x + 2.0f+1.5f+midWidth;

        float radius = options.screwDiameter/2.0f;
        SVGObject circle1 = Circle.create(cx1,cy1,radius);
        polygons.add(circle1);


        SVGObject circle2 = Circle.create(cx2,cy1,radius);
        polygons.add(circle2);

        float cy2 = y + 4.25f-midHeight;
        SVGObject circle3 = Circle.create(cx1,cy2,radius);
        polygons.add(circle3);

        SVGObject circle4 = Circle.create(cx2,cy2,radius);
        polygons.add(circle4);

        return polygons;
    }

    List<SVGObject> generateBottom(float x, float y, GeneratorOptions options) {
        List<SVGObject> polygons = new ArrayList<SVGObject>();

        float materialWidth = options.outerMaterialWidth;

        polygons.add(Section.drawSquare(x,y,6.75f,4.75f));
        polygons.add(Section.drawSquare(x+2.625f, y+ 0.75f, 1.5f, materialWidth));
        polygons.add(Section.drawSquare(x+2.625f, y + 4.0f - materialWidth, 1.5f, materialWidth));
        polygons.add(Section.drawSquare(x+1.0f, y + 1.875f, materialWidth, 1.0f));
        polygons.add(Section.drawSquare(x+5.875f-materialWidth, y+1.875f, materialWidth, 1.0f));

        return polygons;
    }

}
