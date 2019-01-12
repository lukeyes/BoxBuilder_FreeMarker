package com.lukeyes.boxbuilder;

import java.awt.geom.Point2D;

public interface ISection {
	
	public void generate(GeneratorOptions options, float lip, Point2D.Float root);

}
