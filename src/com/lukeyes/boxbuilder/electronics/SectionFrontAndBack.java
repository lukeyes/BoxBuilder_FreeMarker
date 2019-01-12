package com.lukeyes.boxbuilder.electronics;

import com.lukeyes.boxbuilder.GeneratorOptions;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.List;

public class SectionFrontAndBack extends com.lukeyes.boxbuilder.frita.SectionFrontAndBack {

    @Override
    public void generate(GeneratorOptions options, float lip, Point2D.Float root) {

        m_polygons = super.generateFrontAndBack(options, lip, root);

        float materialWidth = options.outerMaterialWidth;

        float innerDepth = options.boxHeight - (2 * materialWidth);
        float tabDepth = innerDepth / 3;

        float innerWidth = (options.boxWidth - (2 * lip + 2 * materialWidth));
        float tabWidth = innerWidth / 3;

        Polygon insideSquare =
                drawSquare(
                        root.x + lip + materialWidth + tabWidth,
                        root.y + materialWidth + innerDepth - ( options.bottomNutSpacing + options.innerMaterialWidth),
                        tabWidth,
                        options.innerMaterialWidth);
        m_polygons.add(insideSquare);
    }


}
