package com.denanis.homework;

import com.denanis.homework.model.Crystal;
import com.denanis.homework.model.Mags;
import com.denanis.homework.thread.Planet;
import com.denanis.homework.thread.Rocket;

import java.util.ArrayList;

public class Competition {
    public static void main(String[] args) throws InterruptedException {
        final Mags redMags = new Mags("Mags of fire", new ArrayList<Crystal>(), new ArrayList<Crystal>());
        final Mags whiteMags = new Mags("Mags of air", new ArrayList<Crystal>(), new ArrayList<Crystal>());
        Planet planet = new Planet();
        Rocket redMagsRocket = new Rocket(planet, redMags);
        Rocket whiteMagsRocket = new Rocket(planet, whiteMags);
        planet.start();
        redMagsRocket.start();
        whiteMagsRocket.start();
        planet.join();
        redMagsRocket.join();
        whiteMagsRocket.join();
        System.out.println("Соревнование завершено!");
    }
}
