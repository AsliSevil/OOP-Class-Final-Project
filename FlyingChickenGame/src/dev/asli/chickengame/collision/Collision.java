/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.collision;

import dev.asli.chickengame.creature.Player;
import dev.asli.chickengame.entity.Entity;
import dev.asli.chickengame.environment.Bread;
import dev.asli.chickengame.environment.Cat;
import dev.asli.chickengame.environment.Egg;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author aslisevil
 */
public class Collision {
    
    private static int p_xSolUst, p_xSagAlt, p_ySolUst, p_ySagAlt;
    private static int e_xSolUst, e_xSagAlt, e_ySolUst, e_ySagAlt;
    
    private static Rectangle rect_p , rect_e;
    
    public static boolean isColliding(Player player,Bread entity)
    {
        return player.getBounds().intersects(entity.getBounds());
    }
    public static boolean isColliding(Player player,Cat entity)
    {
        return player.getBounds().intersects(entity.getBounds());
    }
    public static boolean isColliding(Bread bread,Egg egg)
    {
        return bread.getBounds().intersects(egg.getBounds());
    }
}
