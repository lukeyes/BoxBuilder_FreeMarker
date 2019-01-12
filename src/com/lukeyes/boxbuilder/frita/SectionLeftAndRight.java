package com.lukeyes.boxbuilder.frita;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;
import java.util.List;

public class SectionLeftAndRight extends Section {

	public SectionLeftAndRight() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void generate(GeneratorOptions options, float lip, Float root) {
		
		m_polygons = generateLeftAndRight(options, lip, root);		
	}
	

	protected List<SVGObject> generateLeftAndRight(GeneratorOptions options, float lip, Point2D.Float root) {
		
		List<SVGObject> polygons = new ArrayList<SVGObject>();
		
		float materialWidth = options.outerMaterialWidth;
		
		float innerLength = options.boxLength - (2 * lip + 2 * materialWidth);
		float tabLength = innerLength / 3;		
		
		float innerDepth = options.boxHeight - (2 * materialWidth);
		float tabDepth = innerDepth / 3;


		Polygon face = new Polygon();
		face.setOrigin(new Point2D.Float(root.x, root.y));
		
		face.addPoint(lip + materialWidth, materialWidth);
		face.addPoint(lip + materialWidth + tabLength, materialWidth);
		face.addPoint(lip + materialWidth + tabLength, 0);
		face.addPoint(options.boxLength - (lip + materialWidth + tabLength), 0);
		face.addPoint(options.boxLength - (lip + materialWidth + tabLength), materialWidth);
		face.addPoint(options.boxLength - (lip + materialWidth), materialWidth);
		
		
		face.addPoint(options.boxLength - (lip + materialWidth), materialWidth + tabDepth);
		face.addPoint(options.boxLength - lip, materialWidth + tabDepth);
		face.addPoint(options.boxLength - lip, options.boxHeight - (materialWidth + tabDepth));
		face.addPoint(options.boxLength - (lip + materialWidth),options.boxHeight - (materialWidth + tabDepth));
		face.addPoint(options.boxLength - (lip + materialWidth), options.boxHeight - materialWidth);

		
		face.addPoint(options.boxLength - (lip + materialWidth + tabLength),  options.boxHeight - materialWidth);
		face.addPoint(options.boxLength - (lip + materialWidth + tabLength), options.boxHeight);
		
		
		face.addPoint(lip + materialWidth + tabLength,options.boxHeight);
		face.addPoint(lip + materialWidth + tabLength,options.boxHeight - materialWidth);
		face.addPoint(lip + materialWidth, options.boxHeight - materialWidth);
		face.addPoint(lip + materialWidth, options.boxHeight - (materialWidth + tabDepth));
		face.addPoint(lip, options.boxHeight - (materialWidth + tabDepth));
		face.addPoint(lip, materialWidth + tabDepth);
		face.addPoint(lip + materialWidth,materialWidth + tabDepth);
		face.addPoint(lip + materialWidth, materialWidth);
		
		polygons.add(face);
		

		return polygons;
	}
}
