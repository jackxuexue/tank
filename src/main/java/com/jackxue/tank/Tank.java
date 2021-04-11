package com.jackxue.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x,  y, SPEED = 3;
    private static final  int WEIGHT = 50;
    private static final  int HEIGHT = 50;
    private Dir dir;
    private boolean kU, kD, kL, kR;
    private TankFrame mainFrame;
    private boolean isDead = false;
    private int fireFreq = 0;
    private Group group = Group.BAD;
    public Tank(int x, int y, Dir dir, TankFrame frame, Group group){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.mainFrame = frame;
        this.group = group;
    }

    public void paint(Graphics g){
        if(isDead ){
            return;
        }
        g.fillRect(x, y, WEIGHT, HEIGHT);
        move();
        if(fireFreq >= 10) {
            fire();
            fireFreq = 0;
        }
        fireFreq ++;
    }

    /**
     * 按键按下的处理函数
     * @param e
     */
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                kU = true;
                break;
            case KeyEvent.VK_DOWN:
                kD = true;
                break;
            case KeyEvent.VK_LEFT:
                kL = true;
                break;
            case KeyEvent.VK_RIGHT:
                kR = true;
                break;
        }
        //设置坦克方向
        setMainDir();
    }


    /**
     * 按键抬起函数
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                kU = false;
                break;
            case KeyEvent.VK_DOWN:
                kD = false;
                break;
            case KeyEvent.VK_LEFT:
                kL = false;
                break;
            case KeyEvent.VK_RIGHT:
                kR = false;
                break;
        }
    }

    /**
     * 发射子弹
     */
    private void fire() {
        mainFrame.getBullets().add(new Bullet(x+(WEIGHT/2)-(Bullet.width/2),
                y+(WEIGHT/2)-(Bullet.height/2), dir, mainFrame, group));
    }

    private void move() {
        if(kU == true) y -= SPEED;
        if(kD == true) y += SPEED;
        if(kL == true) x -= SPEED;
        if(kR == true) x += SPEED;
    }

    private void setMainDir() {
        if(kU == true) dir = Dir.U;
        else if(kD == true) dir = Dir.D;
        else if(kL == true) dir = Dir.L;
        else if(kR == true) dir = Dir.R;
    }

    public boolean isDead() {
        return isDead;
    }

    public void die(){
        isDead = true;
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

    public static int getWEIGHT() {
        return WEIGHT;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
