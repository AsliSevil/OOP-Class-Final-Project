/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.states;

import dev.asli.chickengame.Handler;
import dev.asli.chickengame.collision.Collision;
import dev.asli.chickengame.creature.Player;
import dev.asli.chickengame.environment.Bread;
import dev.asli.chickengame.environment.Cat;
import dev.asli.chickengame.gfx.Assets;
import dev.asli.chickengame.gfx.Tile;
import dev.asli.chickengame.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
//import javafx.scene.text.Font;

/**
 *
 * @author aslisevil
 */
public class Level3 extends State{
    private Player player;
    private World world;
    private int y_location = 2;
    private int i=0;
    private int[] random_x,random_y;
    private int bread_amount=30;
    private boolean gameOver = false;
    private boolean isNextLevel = false;
    
    //FOR GAME CLASS::
    private boolean goToNextLevel = false;
    private boolean retryLevel = false;

    //ENTITIES:
    private ArrayList<Bread> bread;
    private ArrayList<Cat> cat;
    private int cat_counter = 0;
    
    //HEALTHBAR:
    private BufferedImage hearts;
    private int heartCount = 3;
    private final int heartWidth = 16, heartHeight =16;
    
    //PUAN:
    private String pointWanted = "25";
    Font font ;
    
    public Level3(Handler handler)
    {
        super(handler);
        player = new Player(handler,288,260,29);
        bread = new ArrayList<Bread>();
        cat = new ArrayList<Cat>();
        random_x = new int[bread_amount];
        random_y = new int[bread_amount];
        hearts = Assets.heartIcon;
        
        world = new World(handler,"res/worlds/world3.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() 
    {
        world.tick();
        player.tick();
        if(isGameOver() == false)
        {
            tickBread();
            tickCat();
        }
    }
    public void tickCat()
    {
        for(int j=0; j<cat.size(); j++)
        {
            cat.get(j).tick();
            if(Collision.isColliding(player, cat.get(j)))
            {  
                if(cat.get(j).getRandom() == 1)
                {
                    cat.remove(j);
                    heartCount--;
                    cat_counter--;
                }
                else if(cat.get(j).getRandom() == 0)
                {
                    gameOver = true;
                    retryLevel = true;
                    isNextLevel = false;
                    goToNextLevel = false;
                }
                if(heartCount==0)
                {
                    gameOver = true;
                    retryLevel = true;
                    isNextLevel = false;
                    goToNextLevel = false;
                    
                }
            }
        }
    }
    public void tickBread()
    {
        for(int j=0; j<bread.size(); j++)
        {
            bread.get(j).tick();
            for(int a = 0; a<player.getEgg().size(); a++)
            {
                if(bread.get(j) != null)
                {
                    if(Collision.isColliding(bread.get(j), player.getEgg().get(a)))
                {
                    player.incrementPoint(bread.get(j).pointValue());
                    bread.remove(j);
                    player.getEgg().remove(a);
                    i--;
                    break;
                }
                }
            }
        }
    }
    @Override
    public void render(Graphics g) 
    {
        world.render(g);
        player.render(g);
        isRenderOver(g);    
        renderHearts(g);
        pointRender(g);
        if(isGameOver() == false)
        {
            renderBread(g);
            renderCat(g);
        }
    }
    public void pointRender(Graphics g)
    {
        g.drawString(player.getPoint()+"/"+pointWanted, 30, 30);
    }
    public void renderHearts(Graphics g)
    {
        if(heartCount > 0){
            if(heartCount == 3)
            {
                g.drawImage(hearts, 560, 10, heartWidth, heartHeight, null);
                g.drawImage(hearts, 580, 10, heartWidth, heartHeight, null);
                g.drawImage(hearts, 600, 10, heartWidth, heartHeight, null);
            }
            else if(heartCount == 2)
            {
                g.drawImage(hearts, 580, 10, heartWidth, heartHeight, null);
                g.drawImage(hearts, 600, 10, heartWidth, heartHeight, null);
            }
            else if(heartCount == 1)
            {
                g.drawImage(hearts, 600, 10, heartWidth, heartHeight, null);
            }
                
        }
    }
    public void isRenderOver(Graphics g)
    {
        if(isGameOver() == true && isNextLevel() == true)
        {
            goToNextLevel = true;
        }
        else if(isGameOver() == true && isNextLevel() == false)
        {
            retryLevel = true;
        }
    }
    public void renderCat(Graphics g)
    {
        
        for(int j=0; j<cat.size(); j++)
            {
                    cat.get(j).render(g,player.getBounds());
            }
    }
    public void renderBread(Graphics g)
    {
        for(int j=0; j<bread.size(); j++)
            {
                    bread.get(j).render(g,player.getBounds());
            }
    }
    public void catGenerator()
    {
        cat.add(new Cat(handler,this));
        cat.get(cat_counter).setVisible();
        cat_counter++;
    }
    
    @Override
    public void breadGenerator()
    {
       int temp_x, temp_y, bread_size;
       
       bread_size = new Random().nextInt(2);
       temp_x = new Random().nextInt(Tile.TILEWIDTH * 9);
       random_x[i] = temp_x;
       
        while(true)
        {
            temp_y = new Random().nextInt(580) + (int)player.getBounds().getMaxX();

            if(isDifferent(temp_y,i))
            {
                random_y[i] = temp_y;
                break;
            }
        }
       if(player.getBounds().y + player.getBounds().height < 640)
        {
            bread.add(new Bread(handler,random_x[i],random_y[i],bread_size));
            bread.get(i).setVisible();
            if((i+1) != bread_amount)
            i++;
        }
        
    }
    
    public boolean isDifferent(int temp,int i)
    {
        for(int j=0; j<=i; j++)
        {
            if(temp == random_y[j])
            {
                return false;
            }
        }
        return true;
    }
    
    public void setBread()
    {
        breadGenerator();
    }
    public void setCat()
    {
        catGenerator();
    }
    public Player getPlayer() {
        return player;
    }

    public boolean goToNextLevel() {
        return goToNextLevel;
    }

    public boolean isRetryLevel() {
        return retryLevel;
    }
    public boolean isGameOver() {
        if(player.getBounds().y + player.getBounds().height <631 )
            return false;
        return true;
    }
    public boolean getGameOver()
    {
        return gameOver;
    }
    public boolean isNextLevel()
    {
        if(player.getPoint() >= 25)
            return true;
        return false;
    }
    public World getWorld()
    {
        return this.world;
    }
}
