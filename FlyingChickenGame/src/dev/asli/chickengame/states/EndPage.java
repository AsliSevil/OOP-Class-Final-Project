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
public class EndPage extends State{
    
    private BufferedImage endPage;
    private int x = 0, y=0, width=640, height=640;
    
    //BUTTON HANDLERS:
    private boolean mainPageButton = false;
    private boolean newGameButton = false;

    public EndPage(Handler handler)
    {
        super(handler);
        endPage = Assets.endPage;
    }
    
    @Override
    public void tick() 
    {
        if(handler.getMouseManager().isIsLeftPressed())
        {
            //System.out.println(handler.getMouseManager().getMouseX() + "  " + handler.getMouseManager().getMouseY());
            if(handler.getMouseManager().getMouseX() >= 215 && handler.getMouseManager().getMouseX() <= 254
                    && handler.getMouseManager().getMouseY() >= 225 && handler.getMouseManager().getMouseY() <= 283)
            {
                newGameButton = true;
            }
            if(handler.getMouseManager().getMouseX() >= 420 && handler.getMouseManager().getMouseX() <= 457
                    && handler.getMouseManager().getMouseY() >= 225 && handler.getMouseManager().getMouseY() <= 371)
            {
                mainPageButton = true;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(endPage, x, y, width, height, null);
    }

    @Override
    public void breadGenerator() {
        
    }
    
    public boolean getGameButton()
    {
        return this.newGameButton;
    }
    public boolean getMainButton()
    {
        return this.mainPageButton;
    }
    public void setGameButton(boolean b)
    {
        this.newGameButton = b;
    }
    public void setMainButton(boolean b)
    {
        this.mainPageButton = b;
    }
}
