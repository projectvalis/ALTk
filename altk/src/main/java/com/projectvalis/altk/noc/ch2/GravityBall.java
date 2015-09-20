package com.projectvalis.altk.noc.ch2;

import java.awt.Color;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.projectvalis.altk.noc.ch1.Ball;
import com.projectvalis.altk.noc.ch1.Vector;


/**
 * extension of ball class that is able to exert gravitational force on other
 * ball objects.
 * 
 * @author snerd
 *
 */
public class GravityBall extends Ball {

	private static final Logger LOGGER = 
			LoggerFactory.getLogger(GravityBall.class.getName());
	

	// the gravitational constant for this universe. totally arbitrary number, 
	// but it seems to work here. 
	double gravConstantD = 0.2;
	
	// keeps things from going nanners with respect to ball movement
	double lowerDistanceConstraintD = 5.0;
	double upperDistanceConstraintD = 25.0;
	
	/**
	 * full control constructor. controls size and color of the ball
	 * 
	 * @param location
	 * 		where the thing starts in the window
	 * @param velocity
	 * 		what the rate of change of location for the thing should be
	 * @param strokeColor
	 * 		color of the outline of the circle
	 * @param fillColor
	 * 		color of the interior of the circle
	 * @param width
	 * 		horizontal size of the circle
	 * @param height
	 * 		vertical size of the circle
	 * @param mass
	 * 		indicator of mass in the physics sense
	 */
	public GravityBall (Vector location, Vector velocity, Vector acceleration, 
				Color strokeColor, Color fillColor, double width,
				double height, double mass) {
		
		super(location, velocity, acceleration, strokeColor, fillColor, width,
				height, mass);
	}
	
	 
	/**
	 * handles the gravitational pull from this object against a given ball 
	 * target.
	 * 
	 * GRAVITY = 
	 *   ( (gravitational constant) * (mass1) * (mass2) ) / (distance^2) ) *
	 *   	 (unit vector between this and target)
	 *   
	 * @param ball
	 * 		the ball against which gravity from this ball is to be applied. 
	 * @return
	 * 		vector representing the gravitational force against target ball.
	 */
	Vector attract(Ball ball) {
		
		// get unit vector telling us the direction of force.
		// *remember* a vector is the difference between two points. it tells 
		// you how many x-units and how many y-units from a given location to
		// go to reach a given destination.
		//
		Vector ballLocationV = new Vector(
				ball.getLocation().getLeft(), ball.getLocation().getRight());
		
		Vector forceV = this.locationV.clone();
		forceV.subtract(ballLocationV);
		
		double distanceD = forceV.getMagnitude();
		
		distanceD = (distanceD < lowerDistanceConstraintD) ? 
				(lowerDistanceConstraintD) : (distanceD);
				
		distanceD = (distanceD > upperDistanceConstraintD) ? 
				(upperDistanceConstraintD) : (distanceD);
		
		forceV.normalize();
		
		// get magnitude of force vector
		//
		double strengthD = 
				(gravConstantD * massD * ball.massD) / (distanceD * distanceD);
		
		forceV.multiply(strengthD);

		// fin
		//
		return forceV;
	}
	
	
}
