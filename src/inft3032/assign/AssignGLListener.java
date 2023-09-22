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
import inft3032.math.Vector3;
import inft3032.scene.Scene;

import java.io.File;

import javax.media.opengl.GL;
import javax.media.opengl.GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;


public class AssignGLListener implements GLEventListener {

	Scene scene;

	public AssignGLListener(Scene s) {
		this.scene = s;
	}
	
	// Called once at the start. Initialisation code goes here
	public void init(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();
		
		// Sets the clear colour to black
		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		
		// Enables back-face culling
		gl.glEnable(GL.GL_CULL_FACE);
		gl.glCullFace(GL.GL_BACK);
		
		
		for (Shape s : scene.shapes) {
			s.init(gl);
		}
	}
	
	// Called every frame. You should have your update and render code here
	public void display(GLAutoDrawable drawable) {
		GL3 gl = drawable.getGL().getGL3();

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
	}
}
