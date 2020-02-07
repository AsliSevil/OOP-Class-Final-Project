/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.states;

import dev.asli.chickengame.Handler;
import dev.asli.chickengame.gfx.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author aslisevil
 */
public class HelpPage extends State{

    private BufferedImage helpPage;
    private int x = 0, y = 0, width = 640, height = 640;
    
    //BUTTON HANDLERS:
    private boolean mainPageButton = false;
    private boolean gamePageButton = false;
    
    public HelpPage(Handler handler)
    {
        super(handler);
        helpPage = Assets.helpPage;
    }
    
    @Override
    public void tick() 
    {
        if(handler.getMouseManager().isIsLeftPressed())
        {
            if(handler.getMouseManager().getMouseX() >=24 && handler.getMouseManager().getMouseX()<= 140
                && handler.getMouseManager().getMouseY() >= 11 && handler.getMouseManager().getMouseY() <= 48)
            {
                mainPageButton = true;
            }   
            if(handler.getMouseManager().getMouseX() >= 506 && handler.getMouseManager().getMouseX() <= 622
                && handler.getMouseManager().getMouseY() >= 11 && handler.getMouseManager().getMouseY() <= 48)
            {
                gamePageButton = true;
            }
            //System.out.println(mainPageButton + "  " + gamePageButton);
        }
        
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(helpPage, x, y, width, height, null);
    }

    @Override
    public void breadGenerator() {
        
    }
    public boolean getMainButton()
    {
        return this.mainPageButton;
    }
    public boolean getGameButton()
    {
        return this.gamePageButton;
    }
    public void setMainButton(boolean b)
    {
        this.mainPageButton = b;
    }
    public void setGameButton(boolean b)
    {
        this.gamePageButton = b;
    }
}
