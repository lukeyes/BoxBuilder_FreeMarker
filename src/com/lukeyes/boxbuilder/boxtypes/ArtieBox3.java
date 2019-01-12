package com.lukeyes.boxbuilder.boxtypes;

import com.lukeyes.boxbuilder.*;
import com.lukeyes.graphics.Circle;
import com.lukeyes.graphics.Polygon;
import com.lukeyes.graphics.SVGObject;
import com.lukeyes.graphics.Section;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class ArtieBox3 extends FritaBox {

	public ArtieBox3() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generatePolygons(GeneratorOptions options) {

		m_svgObjects = new ArrayList<>();

		float borderWidth = 3* options.outerMaterialWidth + 3 * options.nutWidth;
		float openWidth = options.boxWidth - 2 * borderWidth;
		float openHeight = options.boxHeight - 2 * borderWidth;

		float boxWidth1 = options.boxWidth - 2 * (2 * options.outerMaterialWidth);
		float boxHeight1 = options.boxHeight - 2 *(2 * options.outerMaterialWidth);

		// Box 1
		generateBox(
				options.outerMaterialWidth *2,
				options.outerMaterialWidth *2,
				options, 
				boxWidth1, 
				boxHeight1, 
				openWidth, 
				openHeight,
				false);

		float boxWidth2 = options.boxWidth - 2 * options.outerMaterialWidth;
		float boxHeight2 = options.boxHeight - 2* options.outerMaterialWidth;

		// Box 2
		generateBox(
				options.outerMaterialWidth + options.boxWidth + options.spacing,
				options.outerMaterialWidth,
				options, 
				boxWidth2, 
				boxHeight2, 
				openWidth, 
				openHeight,
				true);

		// Box 3
		generateBox(
				2*(options.boxWidth + options.spacing),
				0,
				options,
				options.boxWidth,
				options.boxHeight,
				openWidth,
				openHeight,
				false);

	}

	private void generateBox(
			float x, float y,
			GeneratorOptions options, 
			float boxWidth, float boxHeight,
			float openWidth, float openHeight,
			boolean drawNutTrap) {

		List<SVGObject> sideA =
				generateSideA(
						x,y,
						boxWidth, 
						boxHeight, 
						openWidth, 
						openHeight,
						options.outerMaterialWidth,
						options.nutWidth,
						options.screwDiameter,
						drawNutTrap);
		m_svgObjects.addAll(sideA);

		Polygon sideB =
				generateSideB(
						boxWidth, 
						boxHeight, 
						openWidth, 
						openHeight, 
						options.outerMaterialWidth);

		sideB.setOrigin(new Point2D.Float(x, y + options.spacing + options.boxHeight));
		m_svgObjects.add(sideB);

		List<SVGObject> sideC =
				generateSideC(
						x, 
						y + options.spacing * 2 + 2 * options.boxHeight,
						boxWidth, 
						boxHeight, 
						openWidth, 
						openHeight, 
						options.outerMaterialWidth,
						options.nutWidth,
						options.screwDiameter, 
						drawNutTrap);
		m_svgObjects.addAll(sideC);
	}

	private List<SVGObject> generateSideA(
			float originX,
			float originY,
			float boxWidth, 
			float boxHeight, 
			float openWidth, 
			float openHeight, 
			float materialWidth,
			float nutWidth,
			float screwDiameter,
			boolean drawNutTrap) {

		List<SVGObject> polygonList = new ArrayList<>();

		final float tabWidth = boxWidth/5;
		final float tabHeight = boxHeight/5;

		List<SVGObject> svgObjects = drawPanelSection(
                originX, originY,
                boxWidth, boxHeight,
                openWidth, openHeight,
                nutWidth,
                screwDiameter,
                drawNutTrap);

        polygonList.addAll(svgObjects);

		Polygon polygon = new Polygon();

		// Side 1
		polygon.addPoint(0, 0);
		polygon.addPoint(tabWidth, 0);
		polygon.addPoint(tabWidth, materialWidth);
		polygon.addPoint(2*tabWidth, materialWidth);
		polygon.addPoint(2*tabWidth, 0);
		polygon.addPoint(3*tabWidth, 0);
		polygon.addPoint(3*tabWidth, materialWidth);
		polygon.addPoint(4*tabWidth, materialWidth);
		polygon.addPoint(4*tabWidth, 0);
		polygon.addPoint(boxWidth, 0);

		// Side 2
		polygon.addPoint(boxWidth, tabHeight);
		polygon.addPoint(boxWidth-materialWidth, tabHeight);
		polygon.addPoint(boxWidth-materialWidth, 2*tabHeight);
		polygon.addPoint(boxWidth, 2*tabHeight);
		polygon.addPoint(boxWidth, 3*tabHeight);
		polygon.addPoint(boxWidth-materialWidth, 3*tabHeight);
		polygon.addPoint(boxWidth-materialWidth, 4 * tabHeight);
		polygon.addPoint(boxWidth, 4 * tabHeight);
		polygon.addPoint(boxWidth, boxHeight);


		// Side 3
		polygon.addPoint(boxWidth, boxHeight);
		polygon.addPoint(4*tabWidth, boxHeight);
		polygon.addPoint(4*tabWidth, boxHeight-materialWidth);
		polygon.addPoint(3*tabWidth, boxHeight - materialWidth);
		polygon.addPoint(3*tabWidth, boxHeight);
		polygon.addPoint(2*tabWidth, boxHeight);
		polygon.addPoint(2*tabWidth, boxHeight-materialWidth);
		polygon.addPoint(tabWidth, boxHeight-materialWidth);
		polygon.addPoint(tabWidth, boxHeight);
		polygon.addPoint(0, boxHeight);

		// Side 4
		polygon.addPoint(0, 4*tabHeight);
		polygon.addPoint(materialWidth, 4* tabHeight);
		polygon.addPoint(materialWidth, 3*tabHeight);
		polygon.addPoint(0, 3 * tabHeight);
		polygon.addPoint(0, 2 * tabHeight);
		polygon.addPoint(materialWidth, 2 * tabHeight);
		polygon.addPoint(materialWidth, tabHeight);
		polygon.addPoint(0, tabHeight);

		polygonList.add(polygon);

		polygon.setOrigin(new Point2D.Float(originX, originY));

		return polygonList;	

	}

	private Polygon generateSideB(
			float boxWidth, 
			float boxHeight, 
			float openWidth, 
			float openHeight, 
			float materialWidth) {

		final float tabWidth = boxWidth/5;
		final float tabHeight = boxHeight/5;

		Polygon polygon = new Polygon();

		// Side 1
		polygon.addPoint(materialWidth, materialWidth);
		polygon.addPoint(tabWidth, materialWidth);
		polygon.addPoint(tabWidth, 0);
		polygon.addPoint(2*tabWidth, 0);
		polygon.addPoint(2*tabWidth, materialWidth);
		polygon.addPoint(3*tabWidth, materialWidth);
		polygon.addPoint(3*tabWidth, 0);
		polygon.addPoint(4*tabWidth, 0);
		polygon.addPoint(4*tabWidth, materialWidth);
		polygon.addPoint(boxWidth-materialWidth, materialWidth);

		// Side 2
		polygon.addPoint(boxWidth - materialWidth, tabHeight);
		polygon.addPoint(boxWidth, tabHeight);
		polygon.addPoint(boxWidth, 2 * tabHeight);
		polygon.addPoint(boxWidth - materialWidth, 2 * tabHeight);
		polygon.addPoint(boxWidth - materialWidth, 3 * tabHeight);
		polygon.addPoint(boxWidth, 3 * tabHeight);
		polygon.addPoint(boxWidth, 4 * tabHeight);
		polygon.addPoint(boxWidth - materialWidth, 4 * tabHeight);
		polygon.addPoint(boxWidth - materialWidth, boxHeight - materialWidth);

		// Side 3
		polygon.addPoint(4*tabWidth, boxHeight-materialWidth);
		polygon.addPoint(4*tabWidth, boxHeight);
		polygon.addPoint(3*tabWidth, boxHeight);
		polygon.addPoint(3*tabWidth, boxHeight-materialWidth);
		polygon.addPoint(2*tabWidth, boxHeight-materialWidth);
		polygon.addPoint(2*tabWidth, boxHeight);
		polygon.addPoint(tabWidth, boxHeight);
		polygon.addPoint(tabWidth, boxHeight-materialWidth);
		polygon.addPoint(materialWidth, boxHeight-materialWidth);

		// Side 4
		polygon.addPoint(materialWidth, 4*tabHeight);
		polygon.addPoint(0, 4 * tabHeight);
		polygon.addPoint(0, 3*tabHeight);
		polygon.addPoint(materialWidth, 3*tabHeight);
		polygon.addPoint(materialWidth, 2 * tabHeight);
		polygon.addPoint(0, 2 * tabHeight);
		polygon.addPoint(0, tabHeight);
		polygon.addPoint(materialWidth, tabHeight);


		return polygon;
	}

	private List<SVGObject> generateSideC(
			float originX, float originY, 
			float boxWidth, float boxHeight, 
			float openWidth, float openHeight, 
			float materialWidth, 
			float nutWidth, 
			float screwDiameter,
			boolean drawNutTrap) {

		List<SVGObject> polygonList = new ArrayList<SVGObject>();

		List<SVGObject> svgObjects = drawPanelSection(
                originX, originY,
                boxWidth, boxHeight,
                openWidth, openHeight,
                nutWidth,
                screwDiameter,
                drawNutTrap);

        polygonList.addAll(svgObjects);

		Polygon polygon = new Polygon();

		float tabWidth = boxWidth/5;
		float tabHeight = boxHeight/5;	


		polygon.addPoint(materialWidth, 0);
		polygon.addPoint(tabWidth, 0);
		polygon.addPoint(tabWidth, materialWidth);
		polygon.addPoint(2*tabWidth, materialWidth);
		polygon.addPoint(2*tabWidth, 0);
		polygon.addPoint(3*tabWidth, 0);
		polygon.addPoint(3*tabWidth, materialWidth);
		polygon.addPoint(4*tabWidth, materialWidth);
		polygon.addPoint(4*tabWidth, 0);
		polygon.addPoint(boxWidth-materialWidth, 0);

		// Side 2
		polygon.addPoint(boxWidth-materialWidth, tabHeight);
		polygon.addPoint(boxWidth, tabHeight);
		polygon.addPoint(boxWidth, 2*tabHeight);
		polygon.addPoint(boxWidth-materialWidth, 2* tabHeight);
		polygon.addPoint(boxWidth-materialWidth, 3* tabHeight);
		polygon.addPoint(boxWidth, 3*tabHeight);
		polygon.addPoint(boxWidth, 4*tabHeight);
		polygon.addPoint(boxWidth-materialWidth, 4*tabHeight);		
		polygon.addPoint(boxWidth-materialWidth, boxHeight);

		// Side 3
		polygon.addPoint(4*tabWidth, boxHeight);
		polygon.addPoint(4*tabWidth, boxHeight - materialWidth);
		polygon.addPoint(3*tabWidth, boxHeight - materialWidth);
		polygon.addPoint(3*tabWidth, boxHeight);
		polygon.addPoint(2*tabWidth, boxHeight);
		polygon.addPoint(2*tabWidth, boxHeight - materialWidth);
		polygon.addPoint(tabWidth, boxHeight - materialWidth);
		polygon.addPoint(tabWidth, boxHeight);
		polygon.addPoint(materialWidth, boxHeight);

		// Side 4
		polygon.addPoint(materialWidth, 4*tabHeight);
		polygon.addPoint(0, 4 * tabHeight);
		polygon.addPoint(0, 3 * tabHeight);
		polygon.addPoint(materialWidth, 3 * tabHeight);
		polygon.addPoint(materialWidth, 2 * tabHeight);
		polygon.addPoint(0, 2 * tabHeight);
		polygon.addPoint(0, tabHeight);
		polygon.addPoint(materialWidth, tabHeight);

		polygon.setOrigin(new Point2D.Float(originX, originY));

		polygonList.add(polygon);

		return polygonList;
	}	
	
	private List<SVGObject> drawPanelSection(float originX, float originY, float boxWidth,
			float boxHeight, float openWidth, float openHeight, float nutWidth,
			float screwDiameter, boolean drawNutTrap) {

        List<SVGObject> svgObjects = new ArrayList<SVGObject>();

		// find center
		final float centerX = boxWidth/2;
		final float centerY = boxHeight/2;

		// now find topleft of open
		final float openX = centerX - (openWidth/2);
		final float openY = centerY - (openHeight/2);
		Polygon innerSquare = Section.drawSquare(originX + openX, originY + openY, openWidth, openHeight);
		svgObjects.add(innerSquare);

        float screwMargin = (nutWidth - screwDiameter)/2;

        float radius = screwDiameter/2;

        float topLeftScrewX = openX - (nutWidth + screwMargin) - radius;
        float topLeftScrewY = openY - (nutWidth + screwMargin) - radius;

        float topRightScrewX = (centerX + openWidth/2) + (nutWidth + screwMargin) + radius;
        float bottomLeftScrewY = (centerY + openHeight/2) + (nutWidth + screwMargin) + radius;

		if( drawNutTrap ) {

			//  top left
			float tlX = openX - 2 * nutWidth;
			float tlY = openY - 2 * nutWidth;

			Polygon nutTrapTopLeft = Section.drawSquare(originX + tlX, originY + tlY, nutWidth, nutWidth);
			svgObjects.add(nutTrapTopLeft);

			float tRX = centerX + (openWidth/2) + nutWidth;
			Polygon nutTrapTopRigth = Section.drawSquare(originX + tRX, originY + tlY, nutWidth, nutWidth);
			svgObjects.add(nutTrapTopRigth);

			float blY = centerY + (openHeight/2) + nutWidth;
			Polygon nutTrapBottomLeft = Section.drawSquare(originX+tlX, originY+blY, nutWidth, nutWidth);
			svgObjects.add(nutTrapBottomLeft);

			Polygon nutTrapBottomRight = Section.drawSquare(originX+tRX, originY+blY, nutWidth, nutWidth);
			svgObjects.add(nutTrapBottomRight);

		} else {

            SVGObject screwHoleTopLeft = Circle.create(originX + topLeftScrewX, originY + topLeftScrewY, radius);
			svgObjects.add(screwHoleTopLeft);

            SVGObject screwHoleTopRight = Circle.create(originX + topRightScrewX, originY + topLeftScrewY, radius);
			svgObjects.add(screwHoleTopRight);

            SVGObject screwHoleBottom = Circle.create(originX + topLeftScrewX, originY + bottomLeftScrewY, radius);
			svgObjects.add(screwHoleBottom);

            SVGObject screwHoleBottomRight = Circle.create(originX + topRightScrewX, originY + bottomLeftScrewY, radius);
			svgObjects.add(screwHoleBottomRight);

		}

        // draw guide screw holes.
        float horizSpaceBetweenMainScrews = topRightScrewX - topLeftScrewX;
        float horizSpaceBetweenAllScrews = horizSpaceBetweenMainScrews/3;
        float verticalSpaceBetweenMainScrews = bottomLeftScrewY - topLeftScrewY;
        float verticalSpaceBetweenAllScrews = verticalSpaceBetweenMainScrews/3;

        float guideScrewX_1 = topLeftScrewX + horizSpaceBetweenAllScrews;
        SVGObject screwHoleTop1 = Circle.create(originX + guideScrewX_1, originY + topLeftScrewY, radius);
        svgObjects.add(screwHoleTop1);

        SVGObject screwHoleTop2 =
                Circle.create(
                        originX + guideScrewX_1 + horizSpaceBetweenAllScrews,
                        originY + topLeftScrewY,
                        radius);
        svgObjects.add(screwHoleTop2);

        SVGObject screwHoleBottom1 = Circle.create(originX + guideScrewX_1, originY + bottomLeftScrewY, radius);
        svgObjects.add(screwHoleBottom1);

        SVGObject screwHoleBottom2 =
                Circle.create(
                        originX + guideScrewX_1 + horizSpaceBetweenAllScrews,
                        originY + bottomLeftScrewY,
                        radius
                );
        svgObjects.add(screwHoleBottom2);

        float guideScrewY_1 = topLeftScrewY + verticalSpaceBetweenAllScrews;
        SVGObject screwHoleLeft1 = Circle.create(originX + topLeftScrewX, originY + guideScrewX_1, radius);
        svgObjects.add(screwHoleLeft1);

        float guideScrewY_2 = topLeftScrewY + 2 * verticalSpaceBetweenAllScrews;
        SVGObject screwHoleLeft2 = Circle.create(originX + topLeftScrewX, originY + guideScrewY_2, radius);
        svgObjects.add(screwHoleLeft2);

        SVGObject screwHoleRight1 = Circle.create(originX + topRightScrewX, originY + guideScrewX_1, radius);
        svgObjects.add(screwHoleRight1);

        SVGObject screwHoleRight2 = Circle.create(originX + topRightScrewX, originY + guideScrewY_2, radius);
        svgObjects.add(screwHoleRight2);

       // SVGObject screwHoleLeft1 = Circle.create(originX + topLeftScrewX, originY + )


        return svgObjects;
	}

}
