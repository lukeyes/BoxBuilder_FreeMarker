package com.lukeyes.boxbuilder.electronics;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.graphics.Polygon;

import java.awt.geom.Point2D;

/**
 * Created by luke on 2/21/2017.
 */
public class SectionLeftAndRight extends com.lukeyes.boxbuilder.frita.SectionLeftAndRight {

    @Override
    public void generate(GeneratorOptions options, float lip, Point2D.Float root) {

        m_polygons = super.generateLeftAndRight(options, lip, root);

        float materialWidth = options.outerMaterialWidth;

        float innerLength = options.boxLength - (2 * lip + 2 * materialWidth);
        float tabLength = innerLength / 3;

        float innerDepth = options.boxHeight - (2 * materialWidth);
        float tabDepth = innerDepth / 3;

        Polygon insideSquare =
                drawSquare(
                        root.x + lip + materialWidth + tabLength,
                        root.y + materialWidth + innerDepth - ( options.bottomNutSpacing + options.innerMaterialWidth),
                        tabLength,
                        options.innerMaterialWidth);
        m_polygons.add(insideSquare);
    }
}
