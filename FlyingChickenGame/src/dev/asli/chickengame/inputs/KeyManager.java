/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author aslisevil
 */
public class KeyManager implements KeyListener{
    
    private boolean[] keys;
    public boolean right, left, space;
    private char lastKey; 
    public int lock = 0;
    
    public KeyManager()
    {
        keys = new boolean[256];
    }
    
    public void tick()
    {
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 32)
            lock = 1;
    }
    
    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
        //System.out.println("click out");
        if(e.getKeyCode() == 32)
            lock = 0;
    }
    
    @Override
    public void keyTyped(KeyEvent e)
    {
        lastKey = e.getKeyChar();
    }
    public int getLastKey()
    {
        return this.lastKey;
    }
    public void setLastKey(char c)
    {
        this.lastKey = c;
    }
}
