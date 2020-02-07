/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.creature;

import dev.asli.chickengame.Game;
import dev.asli.chickengame.Handler;
import dev.asli.chickengame.entity.Entity;
import dev.asli.chickengame.gfx.Tile;

/**
 *
 * @author aslisevil
 */
public abstract class Creature extends Entity{
    
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;
    
    protected int health;
    protected float speed;
    protected float xMove, yMove;
    
    protected int lengthHeight;
    
    public Creature(Handler handler,float x,float y,int width,int height,int lengthHeight)
    {
        super(handler,x,y,width,height);
        this.lengthHeight = lengthHeight;
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    
    public void move()
    {
        moveX();
        //moveY();
        //if(y<= Tile.TILEHEIGHT * 5)
          // yMove++;
    }
    
    public void moveX()
    {
        if(xMove > 0) // Moving right
        {
            //int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            
            if(x<=Tile.TILEWIDTH * 9)
            {
                x += xMove;
            }
        }
        else if(xMove < 0) // LEFT
        {
            //int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            
            if(x>=0)
            {
                x += xMove;
            }
        }
        
    }
    
   /* public void moveY()
    {
        if(yMove < 0 ) // UP
        {
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            
            if(!collisionWithTile((int)(x + bounds.x)/Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
            {
                y += yMove;
            }else
            {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }
        else if(yMove > 0) // DOWN
        {
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            
            if(y<= Tile.TILEHEIGHT * 19)
            {
                y += yMove;
            }else
            {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
        
    }*/
    public int getHealth(){return this.health;}
    public float getSpeed(){return this.speed;}
    public float getxMove(){return this.xMove;}
    public float getyMove(){return this.yMove;}
    
    public void setHealth(int health)
    {
        this.health = health;
    }
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    public void setxMove(float xMove)
    {
        this.xMove = xMove;
    }
    public void setyMove(float yMove)
    {
        this.yMove = yMove;
    }
}
