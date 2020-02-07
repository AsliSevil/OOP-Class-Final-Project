/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.states;

import dev.asli.chickengame.Handler;
import dev.asli.chickengame.gfx.Assets;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author aslisevil
 */
public class MainPage extends State{
    
    private BufferedImage mainPage;
    private int x, y;
    private int width = 640, height = 640;
    
    //BUTTON HANDLERS:
    private boolean helpButtonClicked = false;
    private boolean gameButtonClicked = false;
    
    public MainPage(Handler handler)
    {
        super(handler);
        this.mainPage = Assets.mainPage;
        this.x = 0;
        this.y = 0;
    }

    @Override
    public void tick() {
        if(handler.getMouseManager().isIsLeftPressed() == true)
        {
            
            if(handler.getMouseManager().getMouseX() >= 83&& handler.getMouseManager().getMouseX() <= 237
                    && handler.getMouseManager().getMouseY() >= 323 && handler.getMouseManager().getMouseY() <= 405)
            {
                gameButtonClicked = true;
            }
            if(handler.getMouseManager().getMouseX() >= 400 && handler.getMouseManager().getMouseX() <= 558
                    && handler.getMouseManager().getMouseY() >= 323 && handler.getMouseManager().getMouseY() <= 406)
            {
                helpButtonClicked = true;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(mainPage, x, y, width, height, null);
    }

    @Override
    public void breadGenerator() {
    }
    
    public boolean getHelpButton()
    {
        return helpButtonClicked;
    }
    
    public boolean getGameButton()
    {
        return gameButtonClicked;
    }
    
    public void setGameButton(boolean b)
    {
        this.gameButtonClicked = b;
    }
    public void setHelpButton(boolean b)
    {
        this.helpButtonClicked = b;
    }
}
