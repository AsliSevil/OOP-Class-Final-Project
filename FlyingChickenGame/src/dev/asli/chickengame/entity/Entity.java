/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.entity;

import dev.asli.chickengame.Game;
import dev.asli.chickengame.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author aslisevil
 */
public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected Handler handler;
    protected Rectangle bounds;
    protected boolean setVisible = false;
    
    public Entity(Handler handler,float x,float y,int width, int height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        bounds  = new Rectangle(0, 0, width, height);
    }
    public void setX(float x){this.x = x;}
    public void setY(float y){this.y = y;}
    public float getX(){return this.x;}
    public float getY(){return this.y;}
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
    public int getHeight(){return this.height;}
    public int getWidth(){return this.width;}
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public void setVisible() {
        this.setVisible = true;
    }
    public boolean isSetVisible() {
        return setVisible;
    }
}
