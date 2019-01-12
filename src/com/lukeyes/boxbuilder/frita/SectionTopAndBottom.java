package com.lukeyes.boxbuilder.frita;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class SectionTopAndBottom extends Section {

	public SectionTopAndBottom() {
		
	}
	
	@Override
	public void generate(GeneratorOptions options, float lip, Point2D.Float root) {
		m_polygons = generateTopAndBottom(options, lip, root);
	}
	
	private List<SVGObject> generateTopAndBottom(
			GeneratorOptions options,
			float lip,
			Point2D.Float root) {
		
		float materialWidth = options.outerMaterialWidth;
		List<SVGObject> points = new ArrayList<SVGObject>();
		
		// draw side A
		
		Polygon face = new Polygon();
		
		face.setOrigin(new Point2D.Float(root.x,root.y));
		face.addPoint(0,0);
		face.addPoint(options.boxWidth, 0);
		face.addPoint(options.boxWidth, options.boxLength);
		face.addPoint(0, options.boxLength);
		
		points.add(face);
		
		float innerWidth = (options.boxWidth - (2 * lip + 2 * materialWidth));
		float tabWidth = innerWidth / 3;
		Polygon list1 = drawSquare(root.x + lip + materialWidth + tabWidth, root.y + lip, tabWidth, materialWidth);		
		points.add(list1);		
		
		// generate screw holes
		float screw1_A_X = root.x + lip + materialWidth + tabWidth/2 - options.screwDiameter/2;
		float screw1Y = root.y + lip + materialWidth/2 - options.screwDiameter/2;	
		Polygon screw1 = drawSquare(screw1_A_X, screw1Y, options.screwDiameter, options.screwDiameter);
		points.add(screw1);
		
		float screw1_B_X = root.x + lip + materialWidth + 2*tabWidth + tabWidth/2 - options.screwDiameter/2;
		Polygon screw1_B = drawSquare(screw1_B_X, screw1Y, options.screwDiameter, options.screwDiameter);
		points.add(screw1_B);		
		
		float innerLength = options.boxLength - (2 * lip + 2 * materialWidth);
		float tabLength = innerLength / 3;
		Polygon list2 = 
				drawSquare(
						root.x + options.boxWidth - (lip + materialWidth), 
						root.y + lip + materialWidth + tabLength,
						materialWidth,
						tabLength);
		points.add(list2);		
		
		Polygon list3 = 
				drawSquare(
						root.x + lip + materialWidth + tabWidth, 
						root.y + options.boxLength - (lip + materialWidth),
						tabWidth,
						materialWidth);
		
		points.add(list3);
		
		float screw2Y = root.y + options.boxLength - (lip + materialWidth) + (materialWidth/2) - options.screwDiameter/2;
		
		Polygon screw2_A = drawSquare(screw1_A_X, screw2Y, options.screwDiameter, options.screwDiameter);
		points.add(screw2_A);
		
		Polygon screw2_B = drawSquare(screw1_B_X, screw2Y, options.screwDiameter, options.screwDiameter);
		points.add(screw2_B);
		
		Polygon list4 = 
				drawSquare(root.x + lip, root.y + lip + materialWidth + tabLength, materialWidth, tabLength);
		
		points.add(list4);
		
		return points;
	}
	

}
