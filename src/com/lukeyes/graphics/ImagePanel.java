package com.lukeyes.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4006510389787597613L;
	private static final long dotsPerInch = 30;
	
	private long m_dotsPerInch;

	public ImagePanel() {
		init();
	}

	public ImagePanel(LayoutManager layout) {
		super(layout);
		init();
	}

	public ImagePanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		init();
	}

	public ImagePanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		init();
	}
	
	public void setBox(IBox box) {
		m_points = box.getSVGObjects();
		m_dotsPerInch = calculateDPI(box.getMaxDimension());
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		g.clearRect(0, 0, getWidth(), getHeight());
		
		if( m_points != null ) {
			
			long dotsPerInch = getDotsPerInch();
			
			for( SVGObject svgObject : m_points) {
				
				svgObject.draw(g, dotsPerInch);
			}
		}
	}
	
	private long getDotsPerInch() {
		
		return m_dotsPerInch;
	}

	private long calculateDPI(Point2D.Float maxDimension) {		
		
		Dimension size = this.getSize();
		
		float dotsPerInchX = (float) (size.getWidth()/maxDimension.getX());
		float dotsPerInchY = (float) (size.getHeight()/maxDimension.getY());
		
		float calculatedDPI = Math.min(dotsPerInchX, dotsPerInchY);
		double truncatedDPI = Math.floor(calculatedDPI);
		
		
		return (long) Math.min(truncatedDPI, dotsPerInch);
	}
	
	private void init() {
		
		mDimension = new Dimension(1024,768);
		setPreferredSize(mDimension);
		setSize(mDimension);
	}
	
	private Dimension mDimension;
	
	private List<SVGObject> m_points;

}
