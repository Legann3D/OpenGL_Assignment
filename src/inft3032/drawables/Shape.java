// Starting code Copyright 2014 University of South Australia
// Written by Michael Marner <michael.marner@unisa.edu.au>
//
package inft3032.drawables;

import inft3032.math.Matrix4;


public abstract class Shape implements Drawable {
	public Matrix4 transform;
	public Material material;
	
	public Shape(Material m) {
		transform = new Matrix4();
		material = m;
	}
}
