// Starting code Copyright 2014 University of South Australia
// Written by Michael Marner <michael.marner@unisa.edu.au>
//
package inft3032.drawables;

import javax.media.opengl.GL;
import javax.media.opengl.GL3;

import java.nio.*;

/**
 * This class represents a Triangle polygon.
 * @author Michael Marner <michael.marner@unisa.edu.au>
 *
 */
public class Triangle extends Shape {
	
	Vertex v1;
	Vertex v2;
	Vertex v3;

	/**
	 * Constructs a new Triangle object using the vertices specified.
	 * 
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public Triangle(Vertex v1, Vertex v2, Vertex v3, Material m) {
		super(m);
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}


	/**
	 * OpenGL initialisation for the Triangle.
	 */
	public void init(GL3 gl) {
	}
	

	/**
	 * Renders the triangle to the current GL context.
	 */
	public void draw(GL3 gl) {
	}

}
