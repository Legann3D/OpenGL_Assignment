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
import inft3032.scene.Camera;
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
	Shader shader;
	private boolean shadersLoaded = false;

	public AssignGLListener(Scene s) {
		this.scene = s;
	}
	
	// Called once at the start. Initialisation code goes here
	public void init(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		for (Shape s : scene.shapes) {
			s.init(gl);
		}		
		// Sets the clear colour to black
		gl.glClearColor(0, 0, 0, 0);
		
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glDepthFunc(GL.GL_LESS);
		// Enables back-face culling
		gl.glEnable(GL.GL_CULL_FACE);	
		
		Camera camera = new Camera();
		// Calculate the view center position based on camera position and direction
		Vector3 viewCenter = new Vector3(
		        camera.getPosition().getX() + camera.getDirection().getX(),
		        camera.getPosition().getY() + camera.getDirection().getY(),
		        camera.getPosition().getZ() + camera.getDirection().getZ()
		    );

		// Get projection and view matrices
		Matrix4 viewMatrix = MatrixFactory.lookAt(camera.getPosition(), viewCenter, camera.getUp());
		Matrix4 projectionMatrix = MatrixFactory.perspective(camera.getHeightAngle(), camera.getAspectRatio(), 0.1f, 100.0f);
		
		
		
		if (!shadersLoaded) {
			// Try to load the shaders from the scene file only if they haven't been loaded yet
			try {
				//BufferedReader reader = new BufferedReader(new FileReader("scenes/SimpleTriangle.scene"));
				//BufferedReader reader = new BufferedReader(new FileReader("scenes/ColouredTriangle.scene"));
				BufferedReader reader = new BufferedReader(new FileReader("scenes/PerspectiveTest.scene"));
				String vertShaderPath = null;
				String fragShaderPath = null;

				String line;
				while ((line = reader.readLine()) != null) {
					//if (line.trim().startsWith("\"shaders/SimpleShader.vert\"")) {
					//if (line.trim().startsWith("\"shaders/VertexColour.vert\"")) {
					if (line.trim().startsWith("\"shaders/Transform.vert\"")) {
						vertShaderPath = line.trim().substring(1, line.trim().length() - 1);
					//} else if (line.trim().startsWith("\"shaders/SimpleShader.frag\"")) {
					//} else if (line.trim().startsWith("\"shaders/VertexColour.frag\"")) {
					} else if (line.trim().startsWith("\"shaders/VertexColour.frag\"")) {
						fragShaderPath = line.trim().substring(1, line.trim().length() - 1);
					}
				}

				reader.close();

				if (vertShaderPath != null && fragShaderPath != null) {
					// Construct and compile the shader using the paths obtained from the scene file
					shader = new Shader(new File(vertShaderPath), new File(fragShaderPath));
					
					// Enable the shader for rendering
					shader.compile(gl);
					shader.enable(gl);
					
					shader.setUniform("projection", projectionMatrix, gl);
					shader.setUniform("view", viewMatrix, gl);


					shadersLoaded = true; // Set the flag to indicate that shaders have been loaded
				} else {
					System.out.println("Shader file paths not found in SimpleTriangle.scene");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
		
	
	// Called every frame. You should have your update and render code here
	public void display(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

	    for (Shape s : scene.shapes) {	
	        s.draw(gl);   
	    }

	}

	// Called once at the end. You should clean up any resources here
	public void dispose(GLAutoDrawable drawable) {
	}

	// Called when the window is resized. You should update your projection matrix here.
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL3 gl = drawable.getGL().getGL3();
		width = drawable.getWidth();
		height = drawable.getHeight();
		float aspectRatio = (float) width / height;
		Matrix4 newProjectionMatrix = MatrixFactory.perspective(scene.camera.getHeightAngle(), aspectRatio, 0.1f, 100.0f);
		shader.enable(gl);
		shader.setUniform("projection", newProjectionMatrix, gl);

	}
}
