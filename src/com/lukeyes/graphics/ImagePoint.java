package com.lukeyes.graphics;

import java.awt.geom.Point2D;

public class ImagePoint {

	public ImagePoint(Point2D.Float float1, char code) {
		
		m_point = float1;
		m_code = code;
	}	
	
	public Point2D.Float getPoint() {
		return m_point;
	}
	
	public char getCode() {
		return m_code;
	}
	
	public ImagePoint clone() {
		
		return new ImagePoint((Point2D.Float) m_point.clone(), m_code);
		
	}
	
	private Point2D.Float m_point;
	private char m_code;

}
