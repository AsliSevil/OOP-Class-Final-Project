/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.environment;

import dev.asli.chickengame.Handler;
import dev.asli.chickengame.creature.Player;
import dev.asli.chickengame.gfx.Assets;
import dev.asli.chickengame.inputs.KeyManager;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author aslisevil
 */
public class Egg {
    
    private int x, y;
    private final int width = 16, height = 16;
    private BufferedImage egg;
    private Player player;
    private KeyManager keyManager;
    private char position;
    
    //COLLISION:
    private Rectangle rect;
    
    //TIMER:
    private int timer = 0;
            
    public Egg(Player player,KeyManager keyManager)
    {
        this.player = player;
        this.keyManager = keyManager;
       
        this.rect = new Rectangle();
        
        this.position = 's';
        if(keyManager.left)
            this.position = 'l';
        if(keyManager.right)
            this.position = 'r';
        
        this.x = (int) player.getBounds().getCenterX();
        this.y = (int) player.getBounds().getCenterY();
        egg = Assets.egg;
    }
    
    public void tick()
    {
        timer++;
        if(timer == 2)
        {
            if(position == 'l')
                this.x -= 10;
            if(position == 'r')
                this.x += 10;
            if(position == 's')
                this.y += 10;
            
            timer = 0;
        }
    }
    
    public void render(Graphics g)
    {
        g.drawImage(egg, this.x, this.y, width, height, null);
        rect.setBounds(this.x, this.y, width, height);
    }
    public Rectangle getBounds()
    {
        return this.rect;
    }
}
