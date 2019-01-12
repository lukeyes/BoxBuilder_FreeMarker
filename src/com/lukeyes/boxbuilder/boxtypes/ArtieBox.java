package com.lukeyes.boxbuilder.boxtypes;

import com.lukeyes.boxbuilder.*;
import com.lukeyes.boxbuilder.frita.SectionLeftAndRight;
import com.lukeyes.boxbuilder.frita.SectionTopAndBottom;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ArtieBox extends FritaBox {
	
	@Override
	public void generatePolygons(GeneratorOptions options) {
		
		m_svgObjects = new ArrayList<SVGObject>();
		
		float x1 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y1 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		List<SVGObject> frontPolys = generateBack(options, x1, y1);
		m_svgObjects.addAll(frontPolys);
		
		float x2 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y2 = 2 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 1 * options.boxHeight;
		List<SVGObject> backPolys = generateFrontAndBack(options, x2, y2);
		m_svgObjects.addAll(backPolys);
		
		float x3 = 2 * options.spacing + 1 * options.boxWidth + 0* options.boxLength + 0 * options.boxHeight;
		float y3 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		Section leftAndRight1 = new SectionLeftAndRight();
		leftAndRight1.generate(options, options.lip, new Point2D.Float(x3,y3));
		List<SVGObject> leftAndRight1Polys = leftAndRight1.getPolygons();
		m_svgObjects.addAll(leftAndRight1Polys);
		
		float x4 = 2 * options.spacing + 1 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		float y4 = 2 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 1 * options.boxHeight;
		Section leftAndRight2 = new SectionLeftAndRight();
		leftAndRight2.generate(options, options.lip, new Point2D.Float(x4,y4));
		List<SVGObject> leftAndRight2Polys = leftAndRight2.getPolygons();
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
		
		float x8 = 3 * options.spacing + 1 * options.boxWidth + 1 * options.boxLength + 0 * options.boxHeight;
		float y8 = 1 * options.spacing + 0 * options.boxWidth + 0 * options.boxLength + 0 * options.boxHeight;
		
		List<Polygon> doorFrame = generateDoorFrame(options,x8,y8);
		m_svgObjects.addAll(doorFrame);
	}
	
	
	private List<Polygon> generateDoorFrame(GeneratorOptions options, float x, float y) {
		
		List<Polygon> polygons = new ArrayList<Polygon>();
		
		Point2D.Float doorDimensions = getDoorDimensions(options);
		
		float innerBorderMargin = 0.25f;
		float outerBorderMargin = doorBorderMargin(options);
		
		float outerWidth = doorDimensions.x + (2 * outerBorderMargin);
		float outerHeight = doorDimensions.y + (2 * outerBorderMargin);
		float innerWidth = (float) Math.max(doorDimensions.x - 2*innerBorderMargin, 0);
		float innerHeight = (float) Math.max(doorDimensions.y - 2*innerBorderMargin, 0);
		
		
		Polygon outerSquare = Section.drawSquare(x,y,outerWidth, outerHeight);
		
		float inner_x = x + outerBorderMargin + innerBorderMargin;
		float inner_y = y + outerBorderMargin + innerBorderMargin;
		
		Polygon innerSquare = Section.drawSquare(inner_x, inner_y, innerWidth, innerHeight);		
		
		polygons.add(outerSquare);
		polygons.add(innerSquare);		
		
		return polygons;
	}
	
	private float doorBorderMargin(GeneratorOptions options) {
		
		float nutWidth = 0.344f; //options.nutThickness;
		
		return 3 * nutWidth;		
	}
	
	protected List<SVGObject> generateBack(GeneratorOptions options, float x, float y) {
		List<SVGObject> polygons = super.generateFrontAndBack(options, x, y);
		
		Polygon door = generateDoor(options, x, y);
		
		polygons.add(door);		
		
		return polygons;
	}
	
	private Point2D.Float getDoorDimensions(GeneratorOptions options) {
		float borderWidth = options.outerMaterialWidth + options.lip + doorBorderMargin(options);
		float thickness1 = options.outerMaterialWidth + options.lip;
		float thickness2 = Math.max(thickness1, options.screwLength);
		float borderHeight = thickness2+ doorBorderMargin(options);
		
		float doorWidth = options.boxWidth - (2*borderWidth);
		float doorHeight = options.boxHeight - (2*borderHeight);
		
		return new Point2D.Float(doorWidth, doorHeight);		
	}

	private Polygon generateDoor(GeneratorOptions options, float x, float y) {
		
		Point2D.Float doorDimensions = getDoorDimensions(options);
		
		float center_x = x + options.boxWidth/2;
		float center_y = y + options.boxHeight/2;
		
		float door_x = center_x-(doorDimensions.x/2);
		float door_y = center_y-(doorDimensions.y/2);
		
		Polygon door = Section.drawSquare(door_x, door_y, doorDimensions.x, doorDimensions.y);
		return door;
	}

}
