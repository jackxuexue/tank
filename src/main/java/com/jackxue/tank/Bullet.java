package com.jackxue.tank;

import java.awt.*;
import java.util.List;

public class Bullet {
    private int x = 0,  y = 0;
    public static int width = 20, height = 20;
    private Dir dir;
    private TankFrame mainFrame;
    private static final int SPEED = 12;
    private boolean isDead = false;
    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir, TankFrame mainFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.mainFrame = mainFrame;
        this.group = group;
        System.out.println("x:" + x + " y:" + y);
    }

    /**
     * 画子弹的方法
     * @param g
     */
    public void paint(Graphics g){
        if(isDead ){
            return;
        }
        g.fillOval(x, y, width, height);
        move();
        collision();
    }

    private void collision() {
        List<Tank> enemyList = mainFrame.getEnemyList();
        for (Tank tank : enemyList) {
            if(group != tank.getGroup()) {
                if (x >= tank.getX() && x <= (tank.getX() + tank.getWEIGHT()) &&
                        y >= tank.getY() && y <= (tank.getY() + tank.getHEIGHT())) {
                    die();
                    tank.die();
                }
            }
        }
    }

    /**
     * 移动方法
     */
    private void move() {
        //根据方向移动
        switch (dir){
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
        }
        //判断是否出界限了
        if(x < 0 || x > mainFrame.getGameWidth() || y < 0 || y > mainFrame.getGameHeight()){
            isDead = true;
        }
    }


    public boolean isDead() {
        return isDead;
    }

    public void die(){
        isDead = true;
    }
}
