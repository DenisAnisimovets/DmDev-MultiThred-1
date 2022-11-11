package com.denanis.homework.model;

import java.util.ArrayList;

public class Mags {
    private final String name;
    private final ArrayList whiteCrystals;
    private final ArrayList redCrystals;

    public Mags(String name, ArrayList<Crystal> whiteCrystals, ArrayList<Crystal> redCrystals) {
        this.name = name;
        this.whiteCrystals = whiteCrystals;
        this.redCrystals = redCrystals;
    }

    public String getName() {
        return name;
    }

    public ArrayList getWhiteCrystals() {
        return whiteCrystals;
    }

    public ArrayList getRedCrystals() {
        return redCrystals;
    }

    public boolean archiveVictory() {
        if(getRedCrystals().size() >=50 & getWhiteCrystals().size() >=50) {
            System.out.print(getName() + " are the winners!!! ");
            System.out.println("У них красных: " + getRedCrystals().size() + ", белых: " + getWhiteCrystals().size());
            return true;
        } else {
            return false;
        }

    }

}
