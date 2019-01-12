package com.lukeyes.graphics;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;
import java.util.List;

/**
 * Created by luke on 11/22/2015.
 */
public interface SVGObject {

    void draw(Graphics g, long dotsPerInch);

    String toSVG(float dpi);

    Point2D.Float getMaxDimensions();

}
