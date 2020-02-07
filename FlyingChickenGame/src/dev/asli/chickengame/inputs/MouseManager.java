/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.asli.chickengame.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author aslisevil
 */
public class MouseManager implements MouseListener, MouseMotionListener{

    
    private boolean isLeftPressed, isRightPressed;
    private int mouseX, mouseY;
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            isLeftPressed = true;
        else if(e.getButton() == MouseEvent.BUTTON3)
            isRightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
            isLeftPressed = false;
        else if(e.getButton() == MouseEvent.BUTTON3)
            isRightPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseY = e.getY();
        mouseX = e.getX();
    }
    public boolean isIsLeftPressed() {
        return isLeftPressed;
    }

    public boolean isIsRightPressed() {
        return isRightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

}
