/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.tiles;

import dev.asli.chickengame.gfx.Assets;
import dev.asli.chickengame.gfx.Tile;

/**
 *
 * @author aslisevil
 */
public class RockTile extends Tile{
    
    public RockTile(int id)
    {
        super(Assets.rock, id);
    }
    
    public boolean isSolid()
    {
        return true;
    }
}
