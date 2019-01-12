package com.lukeyes.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.List;

public class Polygon implements SVGObject {

	public Polygon() {
		// TODO Auto-generated constructor stub
		m_color = Color.RED;
	}
	
	public void addPoint(float x, float y) {
		m_points.add(new Point2D.Float(x,y));
	}
	
	public void setOrigin(Float point) {
		m_origin = point;
	}
	
	public List<Float> getPoints() {
		return m_points;
	}
	
	public Float getOrigin() {
		return m_origin;
	}

	@Override
	public String toSVG(float dpi) {
		float origin_x = (float) (m_origin.getX() * dpi);
		float origin_y = (float) (m_origin.getY() * dpi);
		
		String transformString = String.format("<g transform=\"translate(%f,%f) \">", origin_x, origin_y);
		String polygonString = String.format("<polygon style=\"stroke:#%02x%02x%02x; fill:none; stroke-width:1\" points=\"",
				m_color.getRed(),
				m_color.getGreen(),
				m_color.getBlue());
		for( int i = 0; i < m_points.size(); i++) {
			final Point2D.Float currentPoint = (Float) m_points.get(i);
			
			float curr_x = (float) currentPoint.getX() * dpi;
			float curr_y = (float) currentPoint.getY() * dpi;
			
			polygonString = polygonString.concat(String.format("%f,%f ", curr_x, curr_y));	
		}
		
		polygonString = polygonString.concat("\" /></g>");
		
		return transformString + polygonString;		
	}
	
	public Float getMaxDimensions() {
		
		float maxX = 0;
		float maxY = 0;

		
		Point2D.Float basePoint = this.getOrigin();

		List<Point2D.Float> pointList = this.getPoints();


		for( int i = 0; i < pointList.size(); i++) {

			final Point2D.Float currentPoint = (Float) pointList.get(i).clone();
	
			float x = (float) (basePoint.getX() + currentPoint.getX());
			float y = (float) (basePoint.getY() + currentPoint.getY());

			if( x > maxX) {
				maxX = x;
			}

			if( y > maxY) {
				maxY = y;
			}
		}
		
		return new Point2D.Float(maxX,maxY);
		
	}
	
	public void draw(Graphics g, long dotsPerInch) {
		
		Color oldColor = g.getColor();
		
		g.setColor(m_color);
		
		Point2D basePoint = this.getOrigin();
		
		List<Float> pointList = this.getPoints();
		
		if( pointList.size() > 1) {
			
			Point2D.Float originPoint = ((Float) pointList.get(0).clone());
			originPoint.setLocation(
					basePoint.getX() + originPoint.getX(),
					basePoint.getY() + originPoint.getY());
			
			for( int i = 1; i < pointList.size(); i++) {
				
				Point2D.Float currentPoint = (Float) pointList.get(i).clone();
				currentPoint.setLocation(basePoint.getX() + currentPoint.getX(), basePoint.getY() + currentPoint.getY());
				
				
				int x0 = (int) (originPoint.x * dotsPerInch);
				int y0 = (int) (originPoint.y * dotsPerInch);
				int x1 = (int) (currentPoint.x * dotsPerInch);
				int y1 = (int) (currentPoint.y * dotsPerInch);
				g.drawLine(x0, y0, x1, y1);
				originPoint = currentPoint;
			}
			
			Point2D.Float currentPoint = (Float) pointList.get(0).clone();
			currentPoint.setLocation(basePoint.getX() + currentPoint.getX(), basePoint.getY() + currentPoint.getY());

			int x0 = (int) (originPoint.x * dotsPerInch);
			int y0 = (int) (originPoint.y * dotsPerInch);
			int x1 = (int) (currentPoint.x * dotsPerInch);
			int y1 = (int) (currentPoint.y * dotsPerInch);
			g.drawLine(x0, y0, x1, y1);
		}
		
		g.setColor(oldColor);
	}

	List<Point2D.Float> m_points = new ArrayList<Point2D.Float>();
	Float m_origin;
	Color m_color;
	long m_strokeWidth;

}
