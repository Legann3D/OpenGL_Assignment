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
	int vao;
	int vbo;

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
		
		float[] vertices = new float[] {
			    v1.pos.getX(), v1.pos.getY(), v1.pos.getZ(), v1.colour.getX(), v1.colour.getY(), v1.colour.getZ(),
			    v2.pos.getX(), v2.pos.getY(), v2.pos.getZ(), v2.colour.getX(), v2.colour.getY(), v2.colour.getZ(),
			    v3.pos.getX(), v3.pos.getY(), v3.pos.getZ(), v3.colour.getX(), v3.colour.getY(), v3.colour.getZ()
			};
		
		int[] temp = new int[] {1};
		
		gl.glGenVertexArrays(1, IntBuffer.wrap(temp));
		vao = temp[0];
					
		gl.glGenBuffers(1, IntBuffer.wrap(temp));
		vbo = temp[0];
		
		gl.glBindVertexArray(vao);		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);	
		
		gl.glBufferData(GL.GL_ARRAY_BUFFER, vertices.length * 4, FloatBuffer.wrap(vertices), GL.GL_STATIC_DRAW);
		
		gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, 6*4, 0);	
		gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false, 6*4, 3*4);	
		gl.glEnableVertexAttribArray(0);
		gl.glEnableVertexAttribArray(1);
		
	}
	

	/**
	 * Renders the triangle to the current GL context.
	 */
	public void draw(GL3 gl) {
    	gl.glBindVertexArray(vao);
        gl.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, 3);
        
	}

}
