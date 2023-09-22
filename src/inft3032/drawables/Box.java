// Starting code Copyright 2014 University of South Australia
// Written by Michael Marner <michael.marner@unisa.edu.au>
//
package inft3032.drawables;

import inft3032.math.Vector3;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL3;



public class Box extends Shape {
	
	private float width;
	private float height;
	private float depth;
	
	public Box(float width, float height, float depth, Material m) {
		super(m);
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public void init(GL3 gl) {
	}
	
	public void draw(GL3 gl) {
	}
	
}
