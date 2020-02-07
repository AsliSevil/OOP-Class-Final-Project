/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.states;

import dev.asli.chickengame.Game;
import dev.asli.chickengame.Handler;
import java.awt.Graphics;

/**
 *
 * @author aslisevil
 */
public abstract class State {
    
    private static State currentState = null;
    protected Game game;
    protected Handler handler;
    
    public State(Handler handler)
    {
        this.handler = handler;
    }
    
    public static void setState(State state)
    {
        currentState = state;
    }
    
    public static State getState()
    {
        return currentState;
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void breadGenerator();
    
}
