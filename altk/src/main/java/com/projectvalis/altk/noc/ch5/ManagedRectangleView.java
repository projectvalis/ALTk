package com.projectvalis.altk.noc.ch5;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.jbox2d.common.Vec2;


/**
 * handles rendering of managed rectangle models
 * 
 * @author snerd
 *
 */
public class ManagedRectangleView extends ManagedElementView {

	
	@Override
	protected void renderPresentation(Graphics2D g2, 
			                          Vec2 posVector,
			                          Vec2 sizeVector) {
		
		// java draws from the upper left corner. here we are 
		// ensuring the center of the shape is placed at the desired x/y coord
		float rectangleLocationX = posVector.x - (sizeVector.x / 2);
		float rectangleLocationY = posVector.y - (sizeVector.y / 2);		
		
		Rectangle2D rectangle2D = 
				new Rectangle2D.Double(rectangleLocationX, 
						               rectangleLocationY,
		                               sizeVector.x,
		                               sizeVector.y);
		
		g2.setColor(this.fillColorC);
		g2.fill(rectangle2D);
		g2.setColor(this.strokeColorC);
		g2.setStroke(this.stroke);
		g2.draw(rectangle2D);		
	}

}


