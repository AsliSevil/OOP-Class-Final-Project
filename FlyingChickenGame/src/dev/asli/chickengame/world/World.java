/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.world;

import dev.asli.chickengame.Handler;
import dev.asli.chickengame.gfx.Tile;
import dev.asli.chickengame.utils.Utils;
import java.awt.Graphics;

/**
 *
 * @author aslisevil
 */
public final class World {
    
    private int width, height;
    private int spawnX, spawnY;
    private static int y_start, y_end;
    public static final int world_width = Tile.TILEWIDTH*10, world_height = Tile.TILEWIDTH*10;

    public int getY_start() {
        return y_start;
    }


    public static int getY_end() {
        return y_end;
    }

    //TO STORE TILES IN EVERY SINGLE POSITION:
    private int[][] tiles;
    private Handler handler;
    
    public World(Handler handler,String path)
    {
        this.handler = handler;
        loadWorld(path);
    }
    
    public void tick()
    {
        
    }
    
    public void render(Graphics g)
    {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
    
        this.y_start = yStart;
        this.y_end = yEnd;
        
        for(int y=yStart; y<yEnd; y++)
        {
            for(int x=xStart; x<xEnd; x++)
            {
                getTile(x,y).render(g,(int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }
    
    public Tile getTile(int x,int y)
    {
        if(x<0 || y<0 || x >= width || y >= height)
            return Tile.grassTile;
        Tile t = Tile.tiles[this.tiles[x][y]];
        if(t == null)
            return Tile.rockTile;
        return t;
    }
    
    public void loadWorld(String path)
    {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);
        
        tiles = new int[width][height];
        
        for(int y=0; y<height; y++)
        {
            for(int x=0; x<width; x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[x + y * width + 4]);
            }
        }
    }
    
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
}
