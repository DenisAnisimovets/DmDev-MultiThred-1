package com.denanis.homework.thread;

import com.denanis.homework.model.ColorCrystal;
import com.denanis.homework.model.Crystal;

import java.util.ArrayList;
import java.util.Random;

public class Planet extends Thread {
    private boolean isActive = true;
    private static final int MAX_CRYSTAL_COUNT = 5;
    private static final Random RANDOM = new Random();
    private final ArrayList<Crystal> whiteCrystalsArray = new ArrayList();
    private final ArrayList<Crystal> redCrystalsArray = new ArrayList();
    private final Object lock = new Object();

    public Object getLock() {
        return lock;
    }

    public synchronized ArrayList<Crystal> getWhiteCrystalsArray() {
        return whiteCrystalsArray;
    }

    public synchronized ArrayList<Crystal> getRedCrystalsArray() {
        return redCrystalsArray;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void run() {
        int whiteCrystals = 0;
        int redCrystals = 0;
        int allCrystals = 0;
        synchronized (this) {
            while (isActive) {
                allCrystals = RANDOM.nextInt(MAX_CRYSTAL_COUNT);
                allCrystals++;
                whiteCrystals = RANDOM.nextInt(allCrystals);
                redCrystals = allCrystals - whiteCrystals;

                for (int i = 0; i < whiteCrystals; i++) {
                    whiteCrystalsArray.add(new Crystal(ColorCrystal.WHITE));
                }

                for (int i = 0; i < redCrystals; i++) {
                    redCrystalsArray.add(new Crystal(ColorCrystal.RED));
                }

                System.out.println("Добавлено на планету всего: " + allCrystals + " Красных: " + redCrystals + " Белых: " + whiteCrystals);

                try {
                    notifyAll();
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("Соревнование закончено!");
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
