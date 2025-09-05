/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.model;

/**
 *
 * @author hcadavid
 */
public class Point {
   
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
    }    
    
    /** 
     * @return int
     */
    public int getX() {
        return x;
    }

    /** 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /** 
     * @return int
     */
    public int getY() {
        return y;
    }

    /** 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
    
    
    
}
