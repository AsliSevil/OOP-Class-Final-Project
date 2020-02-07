/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.creature;

import dev.asli.chickengame.Game;
import dev.asli.chickengame.Handler;
import dev.asli.chickengame.environment.Egg;
import dev.asli.chickengame.gfx.Animation;
import dev.asli.chickengame.gfx.Assets;
import dev.asli.chickengame.gfx.Tile;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author aslisevil
 */
public class Player extends Creature{
    
    //ANIMATIONS
    private Animation animLeft, animRight;
    private BufferedImage animStill;
    private int point = 0;
    private Rectangle player_rect;
    private ArrayList<Egg> egg;
    
    public Player(Handler handler,float x,float y,int lengthHeight)
    {
        super(handler,x,y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT,lengthHeight);
        
        egg = new ArrayList<Egg>();
        
        animStill = Assets.player_still;
        animRight = new Animation(500, Assets.player_right);
        animLeft = new Animation(500, Assets.player_left);
        player_rect = new Rectangle();
    }
    public void incrementPoint(int value) {
        this.point += value;
    }
    public void tick()
    {
        //Animation
        animLeft.tick();
        animRight.tick();
        //Movement
        getInput();
        move();
//        egg.tick(this);
        tickEgg();
        handler.getGameCamera().centerOnEntity(this);
    }
    public void tickEgg()
    {
        for(int i = 0; i<egg.size(); i++)
        {
            egg.get(i).tick();
        }
    }
    public void getInput()
    {
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().left  )
        {
            xMove = -speed;
        }
        if(handler.getKeyManager().right)
        {
            xMove = speed; 
        }
        if(handler.getKeyManager().space)
        {
            egg.add(new Egg(this,handler.getKeyManager()));
        }
            
        //yMove++;
    }
    
    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        player_rect.setBounds((int) (x - handler.getGameCamera().getxOffset() + 5), 
                (int) (y - handler.getGameCamera().getyOffset() + 5), width - 15, height - 15);
        if(y<= Tile.TILEHEIGHT * lengthHeight)
            y++;
        
        renderEgg(g);
        
//        egg.render(g);
       /*g.drawRect((int) (x - handler.getGameCamera().getxOffset() + 5), 
                (int) (y - handler.getGameCamera().getyOffset() + 5), width - 15, height - 15);
        System.out.println((int) (y - handler.getGameCamera().getyOffset() + 5 + height - 15));
               */
    }
    public void renderEgg(Graphics g)
    {
        for(int i=0; i<egg.size(); i++)
        {
            egg.get(i).render(g);
        }
    }
    
    public BufferedImage getCurrentAnimationFrame()
    {
        if(xMove < 0) //LEFT
            return animLeft.getCurrentFrame();
        else if(xMove > 0) //RIGHT
            return animRight.getCurrentFrame();
        else 
            return animStill;
    }
    
    public Rectangle getBounds()
    {
        return player_rect;
    }
    public int getPoint()
    {
        return this.point;
    }
    public ArrayList<Egg> getEgg()
    {
        return egg;
    }
}