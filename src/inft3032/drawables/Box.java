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
	private float[] vertices;
	private int vao;
    private int vertexCount;
	
	
	public Box(float width, float height, float depth, Material m) {
		super(m);
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	public void init(GL3 gl) {
		
		float halfWidth = width / 2;
        float halfHeight = height / 2;
        float halfDepth = depth / 2;

        // Define vertices for the box
        vertices = new float[]{
        		// Front face
        		-halfWidth, -halfHeight, halfDepth, 0, 0, 1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, -halfHeight, halfDepth, 0, 0, 1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, halfDepth, 0, 0, 1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, -halfHeight, halfDepth, 0, 0, 1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, halfDepth, 0, 0, 1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, halfDepth, 0, 0, 1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),

        	    // Left face
        	    -halfWidth, -halfHeight, -halfDepth, -1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, -halfHeight, halfDepth, -1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, halfDepth, -1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, -halfHeight, -halfDepth, -1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, halfDepth, -1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, -halfDepth, -1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),

        	    // Back face
        	    halfWidth, -halfHeight, -halfDepth, 0, 0, -1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, -halfHeight, -halfDepth, 0, 0, -1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, -halfDepth, 0, 0, -1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, -halfHeight, -halfDepth, 0, 0, -1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, -halfDepth, 0, 0, -1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, -halfDepth, 0, 0, -1, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),

        	    // Right face
        	    halfWidth, -halfHeight, halfDepth, 1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, -halfHeight, -halfDepth, 1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, -halfDepth, 1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, -halfHeight, halfDepth, 1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, -halfDepth, 1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, halfDepth, 1, 0, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),

        	    // Top face
        	    -halfWidth, halfHeight, halfDepth, 0, 1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, halfDepth, 0, 1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, -halfDepth, 0, 1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, halfDepth, 0, 1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, halfHeight, -halfDepth, 0, 1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, halfHeight, -halfDepth, 0, 1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),

        	    // Bottom face
        	    -halfWidth, -halfHeight, -halfDepth, 0, -1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, -halfHeight, -halfDepth, 0, -1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, -halfHeight, halfDepth, 0, -1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, -halfHeight, halfDepth, 0, -1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    halfWidth, -halfHeight, halfDepth, 0, -1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ(),
        	    -halfWidth, -halfHeight, -halfDepth, 0, -1, 0, material.diffuse.getX(), material.diffuse.getY(), material.diffuse.getZ()
        };

        // Generate vertex buffer object (VBO) and vertex array object (VAO)
        int[] temp = new int[] {1};
		
		gl.glGenVertexArrays(1, IntBuffer.wrap(temp));
		vao = temp[0];
					
		gl.glGenBuffers(1, IntBuffer.wrap(temp));
		int vbo = temp[0];
		
		gl.glBindVertexArray(vao);		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);	
		
		gl.glBufferData(GL.GL_ARRAY_BUFFER, vertices.length * 4, FloatBuffer.wrap(vertices), GL.GL_STATIC_DRAW);

        // Specify vertex attribute pointers
        int stride = 9 * 4;
        gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, stride, 0); // Position
        gl.glVertexAttribPointer(1, 3, GL.GL_FLOAT, false, stride, 3 * 4); // Normal
        gl.glVertexAttribPointer(2, 3, GL.GL_FLOAT, false, stride, 6 * 4); // Color

        // Enable vertex attributes
        gl.glEnableVertexAttribArray(0);
        gl.glEnableVertexAttribArray(1);
        gl.glEnableVertexAttribArray(2);

        // Unbind VAO and VBO
        gl.glBindBuffer(GL3.GL_ARRAY_BUFFER, 0);
        gl.glBindVertexArray(0);
        vertexCount = vertices.length / 9;
	}
	
	public void draw(GL3 gl) {
		gl.glBindVertexArray(vao);
		gl.glDrawArrays(GL.GL_TRIANGLES, 0, vertexCount);
	}
	
}
