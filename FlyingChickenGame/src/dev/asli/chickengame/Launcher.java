/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame;

/**
 *
 * @author aslisevil
 */
public class Launcher {
    
    public static void main(String[] args)
    {
        Game game = new Game("Flying Chicken Game", 640, 640);
        game.start();
    }
}
