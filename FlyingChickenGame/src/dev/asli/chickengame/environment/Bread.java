/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.environment;

import dev.asli.chickengame.Handler;
import dev.asli.chickengame.creature.Creature;
import dev.asli.chickengame.entity.Entity;
import dev.asli.chickengame.gfx.Assets;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author aslisevil
 */
public class Bread extends Entity{
    
    private BufferedImage bread;
    private static int big_width = 32, big_height = 32,
                        small_width = 16, small_height = 16;
    private int bread_size;
    private Handler handler;
    private Rectangle bread_rect;
    private int pointValue;
    
    public Bread(Handler handler,float x, float y,int bread_size)
    {
        super(handler,x,y,big_width,big_height);
        this.handler = handler;
        bread = Assets.bread;
        bread_rect = new Rectangle();
        this.bread_size = bread_size;
    }

    @Override
    public void tick() {
        if(handler.getGameCamera().getxOffset() >= x)
        {
            if(handler.getGameCamera().getxOffset() <= x + width)
                x--;
        }
        if((y + Bread.big_height *2) >= handler.getWorld().getY_start())
        {
                y--;
        }
    }

    public void render(Graphics g,Rectangle rectangle) {
        if(isOut(rectangle))
        {
            //0 CIKTIYSA 16
        if(bread_size == 0)
        {
            g.drawImage(bread, (int) (x), 
                (int) (y), small_width, small_height, null);
            bread_rect.setBounds((int) (x), 
                (int) (y), small_width, small_height);
            this.pointValue = 3;
        }
        //1 CIKTIYSA 32
        else if(bread_size == 1)
        {
           g.drawImage(bread, (int) (x), 
                (int) (y), big_width, big_height, null);
            bread_rect.setBounds((int) (x), 
                (int) (y), big_width, big_height);
            this.pointValue = 5;
        }
        }
    }

    public boolean isOut(Rectangle rectangle)
    {
        if(setVisible == true && rectangle.getBounds().y + rectangle.getBounds().height <640)
            return true;
        return false;
    }
    
    public BufferedImage getBread() {
        return bread;
    }

    public void setBread(BufferedImage bread) {
        this.bread = bread;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
    public Rectangle getBounds()
    {
        return bread_rect;
    }

    @Override
    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int pointValue()
    {
        return pointValue;
    }
}
