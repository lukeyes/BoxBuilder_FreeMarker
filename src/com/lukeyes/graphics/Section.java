package com.lukeyes.graphics;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.boxbuilder.ISection;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.List;

public class Section implements ISection {

	protected List<SVGObject> m_polygons;

	
	public Section() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generate(GeneratorOptions options, float lip, Float root) {
		// TODO Auto-generated method stub

	}
	
	public List<SVGObject> getPolygons() {
		return m_polygons;
	}
	
	public static Polygon drawSquare(float x, float y, float width, float height) {
		
		Polygon square = new Polygon();
		
		square.setOrigin(new Point2D.Float(x,y));
		square.addPoint(0, 0);
		square.addPoint(width, 0);
		square.addPoint(width, height);
		square.addPoint(0,height);

		return square;		
	}
	
	public static Polygon generateSouthCross(GeneratorOptions options, Point2D.Float root) {
		
		Polygon polygon = new Polygon();

		float nutWidth = (float) options.nutWidth;
		float nutThickness = (float) options.nutThickness;
		
		float L1 = options.screwLength - options.outerMaterialWidth;
		float L1_2 =( L1 - nutThickness)/2;
		
		float w1 = nutWidth - options.screwDiameter;
		float w1_2 = w1 / 2;
		
		polygon.setOrigin(root);
		polygon.addPoint(0, 0);

		polygon.addPoint(0, L1_2);
		polygon.addPoint(-w1_2, L1_2);
		polygon.addPoint(-w1_2, L1_2 + nutThickness);	
		polygon.addPoint(0, L1_2 + nutThickness);
		
		polygon.addPoint(0, L1);
		polygon.addPoint(options.screwDiameter, L1);
		polygon.addPoint(options.screwDiameter, L1_2 + nutThickness);
		polygon.addPoint(options.screwDiameter + w1_2, L1_2 + nutThickness);
		polygon.addPoint(options.screwDiameter + w1_2, L1_2);
		polygon.addPoint(options.screwDiameter, L1_2);
		polygon.addPoint(options.screwDiameter, 0);
		
		return polygon;
	}
	
	public static Polygon generateNorthCross(GeneratorOptions options, Point2D.Float root) {
		
		Polygon polygon = new Polygon();

		float nutWidth = options.nutWidth;
		float nutThickness = options.nutThickness; //(float) 0.203;	
		
		float materialWidth = options.outerMaterialWidth;
		float L1 = options.screwLength - options.outerMaterialWidth;
		float L1_2 =( L1 - nutThickness)/2;
		
		float w1 = nutWidth - options.screwDiameter;
		float w1_2 = w1 / 2;
		
		polygon.setOrigin(new Point2D.Float(root.x, root.y));

		polygon.addPoint(0, 0);
		polygon.addPoint(0, -L1_2);
		polygon.addPoint( -w1_2, -L1_2);
		polygon.addPoint(-w1_2, -( L1_2 + nutThickness));	
		polygon.addPoint(0, -( L1_2 + nutThickness));
		
		polygon.addPoint(0, -L1);
		polygon.addPoint(options.screwDiameter, -L1);
		polygon.addPoint(options.screwDiameter, -( L1_2 + nutThickness));
		polygon.addPoint(options.screwDiameter + w1_2, -(L1_2 + nutThickness));
		polygon.addPoint(options.screwDiameter + w1_2, -L1_2);
		polygon.addPoint(options.screwDiameter, -L1_2);
		polygon.addPoint(options.screwDiameter, 0);
		
		
		return polygon;
	}


}
