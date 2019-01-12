package com.lukeyes.graphics;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.graphics.SVGObject;

import java.awt.geom.Point2D;
import java.util.List;

public interface IBox {
	
	void generatePolygons(GeneratorOptions options);
	List<SVGObject> getSVGObjects();
	Point2D.Float getMaxDimension();

}
