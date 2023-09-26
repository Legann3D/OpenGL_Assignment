// Starting code Copyright 2014 University of South Australia
// Written by Michael Marner <michael.marner@unisa.edu.au>
//

package inft3032.assign;

import inft3032.drawables.Box;
import inft3032.drawables.Material;
import inft3032.drawables.Shape;
import inft3032.drawables.Triangle;
import inft3032.drawables.Vertex;
import inft3032.lighting.PointLight;
import inft3032.math.Matrix4;
import inft3032.math.MatrixFactory;
import inft3032.math.Vector2;
import inft3032.math.Vector3;
import inft3032.scene.Scene;
import inft3032.assign.Shader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Scanner;

import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;


public class AssignGLListener implements GLEventListener {

	Scene scene;
	int vao;

	public AssignGLListener(Scene s) {
		this.scene = s;
		
	}
	
	// Called once at the start. Initialisation code goes here
	public void init(GLAutoDrawable drawable) {
		
		Shader shader;
		float[] vertices;
		
		
		GL3 gl = drawable.getGL().getGL3();
		for (Shape s : scene.shapes) {
			s.init(gl);
		}		
		
		// Sets the clear colour to black
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);
		// Enables back-face culling
		gl.glEnable(GL.GL_CULL_FACE);

		int[] temp = new int[] {1};
					
		gl.glGenVertexArrays(1, IntBuffer.wrap(temp));
		vao = temp[0];
					
		gl.glGenBuffers(1, IntBuffer.wrap(temp));
		int vbo = temp[0];
					
		Vertex v1 = new Vertex();
		v1.pos = new Vector3(); // Initialize position
		v1.normal = new Vector3(); // Initialize normal
		v1.texCoord = new Vector2(); // Initialize texture coordinate
		v1.colour = new Vector3(); // Initialize color

		Vertex v2 = new Vertex();
		v2.pos = new Vector3();
		v2.normal = new Vector3();
		v2.texCoord = new Vector2();
		v2.colour = new Vector3();

		Vertex v3 = new Vertex();
		v3.pos = new Vector3();
		v3.normal = new Vector3();
		v3.texCoord = new Vector2();
		v3.colour = new Vector3();

		vertices = new float[] {
		    v1.pos.getX(), v1.pos.getY(), v1.pos.getZ(),
		    v2.pos.getX(), v2.pos.getY(), v2.pos.getZ(),
		    v3.pos.getX(), v3.pos.getY(), v3.pos.getZ()
		};
					
		gl.glBindVertexArray(vao);		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vbo);		
		gl.glBufferData(GL.GL_ARRAY_BUFFER, vertices.length * 4, FloatBuffer.wrap(vertices), GL.GL_STATIC_DRAW);		
		gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, 3*4, 0);		
		gl.glEnableVertexAttribArray(0);
					
		try {
		    // Read shader file paths from SimpleTriangle.scene
		    BufferedReader reader = new BufferedReader(new FileReader("scenes/SimpleTriangle.scene"));
		    String vertShaderPath = null;
		    String fragShaderPath = null;
		    
		    String line;
		    while ((line = reader.readLine()) != null) {
		        if (line.trim().startsWith("\"shaders/SimpleShader.vert\"")) {
		            vertShaderPath = line.trim().substring(1, line.trim().length() - 1);
		        } else if (line.trim().startsWith("\"shaders/SimpleShader.frag\"")) {
		            fragShaderPath = line.trim().substring(1, line.trim().length() - 1);
		        }
		    }
		    
		    reader.close();
		    
		    if (vertShaderPath != null && fragShaderPath != null) {
		        shader = new Shader(new File(vertShaderPath), new File(fragShaderPath));
		        shader.compile(gl);
		    } else {
		        System.out.println("Shader file paths not found in SimpleTriangle.scene");
		    }
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
	}
		
	
	// Called every frame. You should have your update and render code here
	public void display(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
	    for (Shape s : scene.shapes) {
	        s.draw(gl);
	    }
	    gl.glBindVertexArray(vao);
        gl.glDrawArrays(GL.GL_TRIANGLES, 0, 3);
        
	}

	// Called once at the end. You should clean up any resources here
	public void dispose(GLAutoDrawable drawable) {
	}

	// Called when the window is resized. You should update your projection matrix here.
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL3 gl = drawable.getGL().getGL3();
	}
}
