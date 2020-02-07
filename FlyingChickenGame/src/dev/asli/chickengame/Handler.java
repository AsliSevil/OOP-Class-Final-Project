/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame;

import dev.asli.chickengame.gfx.GameCamera;
import dev.asli.chickengame.inputs.KeyManager;
import dev.asli.chickengame.inputs.MouseManager;
import dev.asli.chickengame.world.World;

/**
 *
 * @author aslisevil
 */
public class Handler {
    
    private Game game;
    private World world;
    
    public Handler(Game game)
    {
        this.game = game;
    }
    
    public Game getGame(){return this.game;}
    public World getWorld(){return this.world;}
    public void setGame(Game game){this.game = game;}
    public void setWorld(World world){this.world = world;}
    public int getWidth(){return this.game.getWidth();}
    public int getHeight(){return this.game.getHeight();}
    public GameCamera getGameCamera(){return this.game.getGameCamera();}
    public KeyManager getKeyManager(){return this.game.getKeyManager();}
    public MouseManager getMouseManager(){return this.game.getMouseManager();}
}
