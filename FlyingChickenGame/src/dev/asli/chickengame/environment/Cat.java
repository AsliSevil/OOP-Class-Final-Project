/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.environment;

import dev.asli.chickengame.Handler;
import dev.asli.chickengame.entity.Entity;
import dev.asli.chickengame.gfx.Assets;
import dev.asli.chickengame.gfx.Tile;
import dev.asli.chickengame.states.Level1;
import dev.asli.chickengame.states.Level2;
import dev.asli.chickengame.states.Level3;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author aslisevil
 */
public class Cat{

    private Handler handler;
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private BufferedImage cat1, cat2;
    private int random;
    private float x, y;
    private final int width1=64, height1=64,
                      width2=48, height2=48;
    private boolean setVisible = false;
    private int timer = 0;
    
    //COLLISION ICIN RECTANGLE:
    private Rectangle rect;
    
    //LEVELLER:
    private int level;
    
    public Cat(Handler handler,Level1 level)
    {
        this.handler = handler;
        this.level1 = level;
        randomCatLoc();
        random = randomCatLoader();
        rect = new Rectangle();
        
        cat1 = Assets.cat1;
        cat2 = Assets.cat2;
        
        this.level = 1;
    }
    public Cat(Handler handler,Level2 level)
    {
        this.handler = handler;
        this.level2 = level;
        randomCatLoc();
        random = randomCatLoader();
        rect = new Rectangle();
        
        cat1 = Assets.cat1;
        cat2 = Assets.cat2;
        
        this.level = 2;
    }
    public Cat(Handler handler,Level3 level)
    {
        this.handler = handler;
        this.level3 = level;
        randomCatLoc();
        random = randomCatLoader();
        rect = new Rectangle();
        
        cat1 = Assets.cat1;
        cat2 = Assets.cat2;
        
        this.level = 3;
    }
    
    public void randomCatLoc()
    {
        int temp_x, temp_y;
       
        temp_x = new Random().nextInt(Tile.TILEWIDTH * 9);
        
        if(handler.getGame().getLevel() ==  1)
        {
            if(level1.getPlayer().getBounds().y + level1.getPlayer().getBounds().height < 640)
            {
                temp_y = new Random().nextInt(580) + (int)level1.getPlayer().getBounds().getMaxX();
                y = temp_y;
                x = temp_x;
            }
        }
        if(handler.getGame().getLevel() == 2)
        {
            if(level2.getPlayer().getBounds().y + level2.getPlayer().getBounds().height < 640)
            {
                temp_y = new Random().nextInt(580) + (int)level2.getPlayer().getBounds().getMaxX();
                y = temp_y;
                x = temp_x;
            }
        }
        if(handler.getGame().getLevel() == 3)
        {
            if(level3.getPlayer().getBounds().y + level3.getPlayer().getBounds().height < 640)
            {
                temp_y = new Random().nextInt(580) + (int)level3.getPlayer().getBounds().getMaxX();
                y = temp_y;
                x = temp_x;
            }
        }
    }
    
    public int randomCatLoader()
    {
        random = new Random().nextInt(2)+0;
        return random;
    }
    
    public void tick() {
        
        if(random == 0)
        {
            if(timer < 100)
            {
                timer++;
                x++;
            }
            else if(timer<200)
            {
                timer++;
                x--;
            }
            else
                timer = 0;
            if((y + height1) > 0)
            {
                y--;
            }
        }else if(random == 1)
        {
            if(timer < 100)
            {
                timer++;
                x++;
            }
            else if(timer<200)
            {
                timer++;
                x--;
            }
            else
                timer = 0;
            if((y + height2) > 0)
            {
                y--;
            }
        }
    }

    public void render(Graphics g,Rectangle rectangle) {
        if(isOut(rectangle))
        {
            if(random == 0)
            {
                g.drawImage(cat1, (int)x, (int)y, width1, height1, null);
                rect.setBounds((int)x + 10,(int)y + 10 , width1 - 20 , height1-20);
            }
            else if(random == 1)
            {
                g.drawImage(cat2, (int)x, (int)y, width2, height2, null);
                rect.setBounds((int)x + 5,(int)y + 5, width2 - 5, height2 - 5);
            } 
        }
    }
    public boolean isOut(Rectangle rectangle)
    {
        if(setVisible == true && rectangle.getBounds().y + rectangle.getBounds().height <640)
            return true;
        return false;
    }
    public void setVisible()
    {
        setVisible = true;
    }
    public Rectangle getBounds()
    {
        return rect;
    }
    public int getRandom()
    {
        return random;
    }
}
