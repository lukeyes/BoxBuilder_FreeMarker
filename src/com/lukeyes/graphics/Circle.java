package com.lukeyes.graphics;

import java.awt.*;
import java.awt.geom.Point2D;

public class Circle implements SVGObject {

    float m_radius;
    Point2D.Float m_center;
    Color m_color;

    public Circle() {
        m_color = Color.RED;
    }


    @Override
    public void draw(Graphics g, long dotsPerInch) {

        Color oldColor = g.getColor();

        g.setColor(m_color);

        Point2D basePoint = m_center;//this.getOrigin();

        int x0 = (int) ((m_center.x-m_radius) * dotsPerInch);
        int y0 = (int) ((m_center.y-m_radius) * dotsPerInch);
        int x1 = (int) ((2*m_radius) * dotsPerInch);
        int y1 = (int) (( 2*m_radius) * dotsPerInch);

         g.drawOval(x0, y0,x1, y1);

        g.setColor(oldColor);

    }

    @Override
    public String toSVG(float dpi) {
        float origin_x = (float) (m_center.getX() * dpi);
        float origin_y = (float) (m_center.getY() * dpi);
        float radius = m_radius * dpi;

        String polygonString = String.format(
                "<circle cx=\"%f\" cy=\"%f\" r=\"%f\" style=\"stroke:#%02x%02x%02x; fill:none; stroke-width:1\"/>",
                origin_x,
                origin_y,
                radius,
                m_color.getRed(),
                m_color.getGreen(),
                m_color.getBlue());
        return polygonString;
    }

    @Override
    public Point2D.Float getMaxDimensions() {
        return new Point2D.Float(m_radius*2,m_radius*2);
    }

    public static Circle create(float centerX, float centerY, float radius) {
        Circle circle = new Circle();
        circle.m_center = new Point2D.Float(centerX, centerY);
        circle.m_radius = radius;
        return circle;
    }
}
