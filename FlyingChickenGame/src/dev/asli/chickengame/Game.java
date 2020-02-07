/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame;

import dev.asli.chickengame.display.Display;
import dev.asli.chickengame.gfx.Assets;
import dev.asli.chickengame.gfx.GameCamera;
import dev.asli.chickengame.inputs.KeyManager;
import dev.asli.chickengame.inputs.MouseManager;
import dev.asli.chickengame.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 *
 * @author aslisevil
 */
public class Game implements Runnable{
    private Display display;
    private int width;
    private int height;
    public String title;
    private int counting = 0;
    
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;
    
    //STATE
    private MainPage mainPage;
    private EndPage endPage;
    private HelpPage helpPage;
    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    
    //INPUT
    private KeyManager keyManager;
    
    //CAMERA
    private GameCamera gameCamera;
    
    //HANDLER
    private Handler handler;
    
    //TIMER
    private int timePassed = 0;
    
    //LEVELS:
    private int level = 0;

    
    
    //MOUSE MANAGER
    private MouseManager mouseManager;
    
    public Game(String title,int width,int height)
    {
        this.height = height;
        this.width = width;
        this.title = title;
        
        
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    private void init()
    {
        display = new Display(title,width,height);
        display.getJframe().addKeyListener(keyManager);
        display.getJframe().addMouseListener(mouseManager);
        display.getJframe().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();
        
        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        
        //endPage = new EndPage(handler);
        //helpPage = new HelpPage(handler);
        level1 = new Level1(handler);
        mainPage = new MainPage(handler);
        State.setState(mainPage);
        
        gameCamera = new GameCamera(handler,0,0);
    }
    public void tick()
    {
        keyManager.tick();
        
        if(State.getState() != null)
            State.getState().tick();
        
        if(State.getState() == mainPage)
        {
            if(mainPage.getGameButton() == true)
            {
                State.setState(level1);
                handler.setWorld(level1.getWorld());
                level = 1;
                counting = 0;
            }
            if(mainPage.getHelpButton() == true)
            {
                helpPage = new HelpPage(handler);
                State.setState(helpPage);
            }
        }
        if(State.getState() == helpPage)
        {
            if(helpPage.getGameButton() == true)
            {
                State.setState(level1);
                handler.setWorld(level1.getWorld());
                counting = 0;
                level = 1;
            }
            if(helpPage.getMainButton() == true)
            {
                mainPage = new MainPage(handler);
                State.setState(mainPage);
                level = 0;
            }
        }
        if(State.getState() == endPage)
        {
            if(endPage.getGameButton() == true)
            {
                level1= new Level1(handler);
                State.setState(level1);
                handler.setWorld(level1.getWorld());
                level = 1;
                counting = 0;
            }
            if(endPage.getMainButton() == true)
            {
                mainPage = new MainPage(handler);
                State.setState(mainPage);
                level = 0;
            }
        }
        
        
    }
    public void render()
    {  
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
       
        if(State.getState() != null)
        {
            State.getState().render(g);
        }
        
        bs.show();
        g.dispose();  
    }
    public void run()
    {
        init();
        int fps = 60; //FRAMES PER SECOND
        double timePerTick = 1_000_000_000.00 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;
        
        while(running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now-lastTime;
            lastTime = now;
            
            if(delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1_000_000_000)
            {
                counting++;
                //System.out.println("Ticks and frames : " + ticks);
                setLevel();
                ticks = 0;
                timer = 0;
            }
            
        }
        stop();
    }
    
    public void setLevel()
    {
        if(level == 1)
        {
            if( level1.getGameOver() == false)
            {
                level1.setBread();
                if(counting == 3)
                {
                    level1.setCat();
                counting = 0;
                }
            }
            if(level1.isGameOver() == true || level1.getGameOver() == true)
            {
                if(level == 1)
                {
                    if(level1.goToNextLevel())
                    {
                        level2 = new Level2(handler);
                        State.setState(level2);
                        handler.setWorld(level2.getWorld());
                        level++;
                    }
                
                else if(level1.isRetryLevel())
                {
                    level1 = new Level1(handler);
                    State.setState(level1);
                    counting = 0;
                }
                
                }
            }  
        }
        if(level == 2)
        {
                if( level2.getGameOver() == false)
            {
                level2.setBread();
                if(counting == 2)
                {
                    level2.setCat();
                counting = 0;
                }
            }
            if(level2.isGameOver() == true || level2.getGameOver() == true)
            {
                
                if(level == 2)
                {
                    if(level2.goToNextLevel())
                    {
                        level3 = new Level3(handler);
                        State.setState(level3);
                        handler.setWorld(level3.getWorld());
                        level++;
                    }
                
                else if(level2.isRetryLevel())
                {
                    level2 = new Level2(handler);
                    State.setState(level2);
                    counting = 0;
                }
                
                }
            }  
        }
        if(level == 3)
        {
                if( level3.getGameOver() == false)
            {
                level3.setBread();
                if(counting == 1)
                {
                    level3.setCat();
                counting = 0;
                }
            }
            if(level3.isGameOver() == true || level3.getGameOver() == true)
            {
                if(level == 3)
                {
                    if(level3.goToNextLevel())
                    {
                        endPage = new EndPage(handler);
                        State.setState(endPage);
                    }
                
                else if(level3.isRetryLevel())
                {
                    level3 = new Level3(handler);
                    State.setState(level3);
                    counting = 0;
                }
                
                }
            }  
        }
    }
    
    public GameCamera getGameCamera()
    {
        return this.gameCamera;
    }
    public KeyManager getKeyManager()
    {
        return this.keyManager;
    }
    
    public synchronized void start()
    {
        if(running)
        {
            return;
        }
        running = true;

        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop()
    {
        if(!running)
            return;
        running = false;
        try{
            thread.join();
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    
    public void setWidth(int width){this.width = width;}
    public void setHeight(int height){this.height = height;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public Display getDisplay(){return this.display;}
    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public void setMouseManager(MouseManager mouseManager) {
        this.mouseManager = mouseManager;
    }
    
    public int getLevel()
    {
        return this.level;
    }
}

    


