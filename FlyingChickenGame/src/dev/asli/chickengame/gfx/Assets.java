/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author aslisevil
 */
public class Assets {
    
    public static BufferedImage mainPage, endPage, helpPage;
    public static BufferedImage tree, soil, grass, rock, bread, cat1, cat2, heartIcon, egg;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    public static BufferedImage player_still;
    private static final int width = 32, height = 32;
    private static final int width2 = 64, height2 = 64;
    
    public static void init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/res/textures/sheetW.png"));
        
        bread = ImageLoader.loadImage("/res/textures/bread2.png");
        tree = sheet.crop(0, 0, width, height);
        soil = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        rock = sheet.crop(width * 3, 0, width, height);
        player_still = ImageLoader.loadImage("/res/textures/standingStill.png");
        cat1 = ImageLoader.loadImage("/res/textures/Cats/cat1.png");
        cat2 = ImageLoader.loadImage("/res/textures/Cats/cat2.png");
        heartIcon = ImageLoader.loadImage("/res/textures/kalp.png");
        egg = ImageLoader.loadImage("/res/textures/egg.png");
        
        player_right = new BufferedImage[5];
        player_right[0] = ImageLoader.loadImage("/res/textures/toRight/toRight1.png");
        player_right[1] = ImageLoader.loadImage("/res/textures/toRight/toRight2.png");
        player_right[2] = ImageLoader.loadImage("/res/textures/toRight/toRight3.png");
        player_right[3] = ImageLoader.loadImage("/res/textures/toRight/toRight4.png");
        player_right[4] = ImageLoader.loadImage("/res/textures/toRight/toRight5.png");
        
        player_left = new BufferedImage[5];
        player_left[0] = ImageLoader.loadImage("/res/textures/toLeft/toLeft1.png");
        player_left[1] = ImageLoader.loadImage("/res/textures/toLeft/toLeft2.png");
        player_left[2] = ImageLoader.loadImage("/res/textures/toLeft/toLeft3.png");
        player_left[3] = ImageLoader.loadImage("/res/textures/toLeft/toLeft4.png");
        player_left[4] = ImageLoader.loadImage("/res/textures/toLeft/toLeft5.png");
        
        //SAYFALAR::
        mainPage = ImageLoader.loadImage("/res/textures/Pages/MainPage.png");
        helpPage = ImageLoader.loadImage("/res/textures/Pages/HelpPage.png");
        endPage = ImageLoader.loadImage("/res/textures/Pages/EndPage.png");
    }
    
}
