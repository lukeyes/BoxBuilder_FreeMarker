package com.lukeyes.boxbuilder.boxtypes;

import com.lukeyes.boxbuilder.*;
import com.lukeyes.boxbuilder.frita.SectionFrontAndBack;
import com.lukeyes.boxbuilder.frita.SectionLeftAndRight;
import com.lukeyes.boxbuilder.frita.SectionTopAndBottom;
import com.lukeyes.graphics.IBox;
import com.lukeyes.graphics.ImagePoint;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class FritaBox implements IBox {
	
	protected List<SVGObject> m_svgObjects;

	public FritaBox() {
		// TODO Auto-generated constructor stub
	}	

	@Override
	public void generatePolygons(GeneratorOptions options) {
	
		m_svgObjects = new ArrayList<SVGObject>();
		
		float x1 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y1 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		List<SVGObject> frontPolys = generateFrontAndBack(options, x1, y1);
		m_svgObjects.addAll(frontPolys);
		
		float x2 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y2 = 2 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 1 * options.boxHeight;
		List<SVGObject> backPolys = generateFrontAndBack(options, x2, y2);
		m_svgObjects.addAll(backPolys);
		
		float x3 = 2 * options.spacing + 1 * options.boxWidth + 0* options.boxLength + 0 * options.boxHeight;
		float y3 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		List<SVGObject> leftAndRight1Polys = generateLeftAndRight(options, x3, y3);
		m_svgObjects.addAll(leftAndRight1Polys);
		
		float x4 = 2 * options.spacing + 1 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y4 = 2 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 1 * options.boxHeight;
		List<SVGObject> leftAndRight2Polys = generateLeftAndRight(options, x4, y4);
		m_svgObjects.addAll(leftAndRight2Polys);
		
		float x5 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y5 = 3 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 2 * options.boxHeight;
		Section topAndBottom1 = new SectionTopAndBottom();
		topAndBottom1.generate(options, options.lip, new Point2D.Float(x5,y5));
		List<SVGObject> topAndBottom1Polys = topAndBottom1.getPolygons();
		m_svgObjects.addAll(topAndBottom1Polys);
		
		float x6 = 2 * options.spacing + 1 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y6 = 3 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 2 * options.boxHeight;
		Section topAndBottom2 = new SectionTopAndBottom();
		topAndBottom2.generate(options, options.lip, new Point2D.Float(x6,y6));
		List<SVGObject> topAndBottom2Polys = topAndBottom2.getPolygons();
		m_svgObjects.addAll(topAndBottom2Polys);
	}

	protected List<SVGObject> generateLeftAndRight(GeneratorOptions options, float x3, float y3) {
		Section leftAndRight1 = new SectionLeftAndRight();
		leftAndRight1.generate(options, options.lip, new Point2D.Float(x3,y3));
		return leftAndRight1.getPolygons();
	}

	@Override
	public List<SVGObject> getSVGObjects() {
		return m_svgObjects;
	}

	protected List<SVGObject> generateFrontAndBack(GeneratorOptions options,
			float x1, float y1) {
		Section frontAndBack1 = new SectionFrontAndBack();
		frontAndBack1.generate(options, options.lip, new Point2D.Float(x1, y1));
		List<SVGObject> frontAndBack1Polys = frontAndBack1.getPolygons();
		return frontAndBack1Polys;
	}
	
	protected List<ImagePoint> drawSquare(float x, float y, float width, float height) {
		
		List<ImagePoint> points = new ArrayList<ImagePoint>();
		
		points.add(new ImagePoint(new Point2D.Float(x, y), 'M'));
		points.add(new ImagePoint(new Point2D.Float(x + width, y), 'L'));
		points.add(new ImagePoint(new Point2D.Float(x + width, y + height), 'L'));
		points.add(new ImagePoint(new Point2D.Float(x, y + height), 'L'));
		points.add(new ImagePoint(new Point2D.Float(x, y), 'L'));

		return points;		
	}
	
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
/*
	@Override
	public List<SVGObject> getSVGObjects() {
		// TODO Auto-generated method stub
		return m_svgObjects;
	}
*/
}
