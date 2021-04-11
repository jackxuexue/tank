package com.jackxue.tank;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        while (true){
            tf.repaint();
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }
}
