package com;
/**
 * Created with IntelliJ IDEA.
 * User: Panda
 * Date: 13/04/12
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
public class MemoryMonitor implements Runnable {
    @Override
	public void run() {
        while (true) {
            System.gc();
            try {
                Thread.sleep(20000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}