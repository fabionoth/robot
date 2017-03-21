/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fabionoth.model;

/**
 *
 * @author fabio
 */
public class Robot {
    
    private Long id;
    private int x;
    private int y;
    private CardinalPoints c;

    public Robot(Long id, int x, int y, CardinalPoints c) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.c = c;
    }

    public Robot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public CardinalPoints getC() {
        return c;
    }

    public void setC(CardinalPoints c) {
        this.c = c;
    }
    
}
