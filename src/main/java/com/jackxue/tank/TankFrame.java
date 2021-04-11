package com.jackxue.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TankFrame extends Frame {
    private  int GAME_WIDTH = 800;
    private  int GAME_HEIGHT = 600;
    private Tank tank;
    private List<Bullet> bullets = new ArrayList<>();
    private List<Tank> enemyList = new ArrayList<>();
    private Image offScreenImage = null;

    public TankFrame() throws HeadlessException {
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("tank war");
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
        tank = new Tank(300, 159, Dir.R, this, Group.GOOD);
        for (int i = 0; i < 1; i++) {
            enemyList.add(new Tank(50+i*60, 150, Dir.R, this, Group.BAD));
        }
        enemyList.add(tank);
    }


    @Override
    public void update(Graphics g) { //双缓冲
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(Color.WHITE);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        tank.paint(g);
        g.drawString("子弹数量：" + bullets.size(), 10, 60);

        /**  画坦克打出的子弹   **/
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()){
            Bullet next = iterator.next();
            if(next.isDead()){
                iterator.remove();
            }
            else {
                next.paint(g);
            }
        }

        /**  画坦克   **/
        Iterator<Tank> enemyIterator = enemyList.iterator();
        while (enemyIterator.hasNext()){
            Tank next = enemyIterator.next();
            if(next.isDead()){
                enemyIterator.remove();
            }
            else {
                next.paint(g);
            }
        }
    }

    class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
        }
    }

    public int getGameWidth() {
        return GAME_WIDTH;
    }

    public int getGameHeight() {
        return GAME_HEIGHT;
    }


    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Tank> getEnemyList() {
        return enemyList;
    }
}
