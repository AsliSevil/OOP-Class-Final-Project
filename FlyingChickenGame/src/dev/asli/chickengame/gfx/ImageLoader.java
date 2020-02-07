/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author aslisevil
 */
public class ImageLoader {
     public static BufferedImage loadImage(String path)
    {
        try{
          // return ImageIO.read(new File(path));
        return ImageIO.read(ImageLoader.class.getResource(path));
        }catch(IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
