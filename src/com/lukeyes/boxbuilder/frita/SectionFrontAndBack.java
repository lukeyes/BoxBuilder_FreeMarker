package com.lukeyes.boxbuilder.frita;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.List;

public class SectionFrontAndBack extends Section {

	public SectionFrontAndBack() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void generate(GeneratorOptions options, float lip, Float root) {
		
		m_polygons = generateFrontAndBack(options, lip, root);		
	}
	

	protected List<SVGObject> generateFrontAndBack(GeneratorOptions options, float lip, Point2D.Float root) {
		
		List<SVGObject> polygons = new ArrayList<SVGObject>();
		
		float materialWidth = options.outerMaterialWidth;
		
		float innerDepth = options.boxHeight - (2 * materialWidth);
		float tabDepth = innerDepth / 3;
		
		float innerWidth = (options.boxWidth - (2 * lip + 2 * materialWidth));
		float tabWidth = innerWidth / 3;

		Polygon face = new Polygon();
		face.setOrigin(new Point2D.Float(root.x, root.y));
		face.addPoint(0, materialWidth);
		face.addPoint(lip + materialWidth + tabWidth, materialWidth);
		face.addPoint(lip + materialWidth + tabWidth, 0);
		face.addPoint(lip + materialWidth + 2 * tabWidth, 0);
		face.addPoint(lip + materialWidth + 2 * tabWidth, materialWidth);
		face.addPoint(options.boxWidth, materialWidth);
		
		face.addPoint(options.boxWidth, options.boxHeight - materialWidth);
		
		face.addPoint(lip + materialWidth + 2 * tabWidth, options.boxHeight - materialWidth);		
		face.addPoint(lip + materialWidth + 2 * tabWidth, options.boxHeight);
		face.addPoint(lip + materialWidth + tabWidth, options.boxHeight);
		face.addPoint(lip + materialWidth + tabWidth, options.boxHeight - materialWidth);
		face.addPoint(0, options.boxHeight - materialWidth);
			
		polygons.add(face);
		
		Polygon tabSquare1 = 		
				drawSquare(
						root.x + options.boxWidth - (lip + materialWidth),
						root.y + materialWidth + tabDepth,
						materialWidth,
						tabDepth);
		polygons.add(tabSquare1);
		
		Polygon tabSquare2 = drawSquare(root.x + lip, root.y + materialWidth + tabDepth, materialWidth, tabDepth); 
		polygons.add(tabSquare2);
		
		float screw1X = root.x + lip + materialWidth + tabWidth/2 - options.screwDiameter/2;
		float screw1Y = root.y + materialWidth;
		Polygon screw1 = generateSouthCross(options, new Point2D.Float(screw1X, screw1Y));
		polygons.add(screw1);
		
		float screw2X = root.x + lip + materialWidth + 2 * tabWidth + tabWidth/2 - options.screwDiameter/2;
		Polygon screw2 = generateSouthCross(options, new Point2D.Float(screw2X, screw1Y));
		polygons.add(screw2);
		
		float screw3Y = root.y + options.boxHeight - materialWidth;
		Polygon screw3 = generateNorthCross(options, new Point2D.Float(screw1X, screw3Y));
		polygons.add(screw3);
		
		Polygon screw4 = generateNorthCross(options, new Point2D.Float(screw2X, screw3Y));
		polygons.add(screw4);
		
		//*/
		//list.add(new ImagePoint(new Point2D.Float(root.x + options.boxWidth, root.y + materialWidth), 'L'));
		//list.add(new ImagePoint(new Point2D.Float(root.x + options.boxWidth, root.y + ))))
		
		return polygons;
	}


}
