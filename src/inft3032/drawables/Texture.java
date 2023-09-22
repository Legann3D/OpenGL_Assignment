// Starting code Copyright 2014 University of South Australia
// Written by Michael Marner <michael.marner@unisa.edu.au>
//
package inft3032.drawables;

import javax.media.opengl.GL3;

/**
 * A class to represent a Texture.
 * <p/>
 * A texture contains an index, the filename of the .bmp texture file, and the bitmap
 * loaded into a local Image object.
 * Created by Tony Sobey on 27/03/2004 at 03:09:41
 *
 * @author a.sobey
 * @version 1.0
 */
public class Texture {
    public int index;
    public String fileName;
    public Image image;
    
    public Texture(int index, String filename, Image image) {
    	this.index = index;
    	this.fileName = filename;
    	this.image = image;
    }
    
    public void init(GL3 gl) {
    	
    }
}
