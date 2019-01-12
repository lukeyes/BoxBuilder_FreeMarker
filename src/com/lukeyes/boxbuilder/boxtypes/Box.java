package com.lukeyes.boxbuilder.boxtypes;

import com.lukeyes.graphics.IBox;
import com.lukeyes.graphics.SVGObject;

import java.awt.geom.Point2D;
import java.util.List;


public abstract class Box implements IBox {

    @Override
    public List<SVGObject> getSVGObjects() {
        return m_svgObjects;
    }

    @Override
    public Point2D.Float getMaxDimension() {

        float maxX = 0;
        float maxY = 0;

        // super brute force, loop through all points and find largest one
        if( m_svgObjects != null ) {

            for( SVGObject svgObject : m_svgObjects) {

                Point2D.Float maxDimension = svgObject.getMaxDimensions();

                float x = (float) (maxDimension.getX());
                float y = (float) (maxDimension.getY());

                if( x > maxX) {
                    maxX = x;
                }

                if( y > maxY) {
                    maxY = y;
                }
            }
        }

        return new Point2D.Float(maxX,maxY);
    }

    protected List<SVGObject> m_svgObjects;
}
