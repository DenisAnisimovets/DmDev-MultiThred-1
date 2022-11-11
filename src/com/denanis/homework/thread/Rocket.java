package com.denanis.homework.thread;

import com.denanis.homework.model.ColorCrystal;
import com.denanis.homework.model.Crystal;
import com.denanis.homework.model.Mags;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

public class Rocket extends Thread {
    private final Planet planet;
    private final Mags mags;

    public Rocket(Planet planet, Mags mags) {
        this.planet = planet;
        this.mags = mags;
    }

    @Override
    public void run() {
        while (true) {
            Object lock = planet.getLock();
            synchronized (lock) {
                while (planet.isActive()) {
                    ArrayList<Crystal> redCrystalsArray = planet.getRedCrystalsArray();
                    ArrayList<Crystal> whiteCrystalsArray = planet.getWhiteCrystalsArray();
                    if(!redCrystalsArray.isEmpty()) {
                        mags.getRedCrystals().add(new Crystal(ColorCrystal.RED));
                        redCrystalsArray.remove(0);
                        System.out.println(mags.getName() + " забирают красный кристалл. Всего у них: " + mags.getRedCrystals().size());

                        if(mags.archiveVictory()) {
                            planet.setActive(false);
                        }
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if(!whiteCrystalsArray.isEmpty()) {
                        mags.getWhiteCrystals().add(new Crystal(ColorCrystal.WHITE));
                        whiteCrystalsArray.remove(0);
                        System.out.println(mags.getName() + " забирают белый кристалл. Всего у них: " + mags.getWhiteCrystals().size());

                        if(mags.archiveVictory()) {
                            planet.setActive(false);
                        }
                        try {
                            lock.notifyAll();
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            lock.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
