package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Calculates amount of quests that need to be completed before next meld.
 * If desired deco is in next set and current rotationStatus == 2 it calls for a method
 * that checks remaining places and returns minimal decos that need to be melded to reach desired deco
 */
public class RotationSimulator {


    private LinkedHashMap<Integer, Integer> desiredDecoPlacesMap;
    private ArrayList<Integer> decoKeySet;
    private int currentRotation;
    private int rotationStatus;
    private int remainingQuests;
    private int remainingMelds;
    private int observedSet;

    public RotationSimulator(LinkedHashMap<Integer, Integer> desiredDecoPlacesMap, int rotationStatus){
        this.desiredDecoPlacesMap = desiredDecoPlacesMap;
        decoKeySet = new ArrayList<>(desiredDecoPlacesMap.keySet());
        this.rotationStatus = rotationStatus;
        this.observedSet = 1;
    }

    //public


    public void nextRotationStatus() {
        rotationStatus++;
        if (rotationStatus > 3) {
            rotationStatus = 1;
        }
    }

    public int getCurrentRotationStatus() {

        int[] rotation = {1, 1, 2};
        System.out.println("rotationStatus from method is: " + rotationStatus);
        return rotation[rotationStatus-1];

    }


    public byte isQuestPossible(){

        if(rotationStatus > 0 && rotationStatus < 3){
            if (observedSet+1 > decoKeySet.get(0)) {
                System.out.println("Don't do any more quests");
                return 0;
            }
            else{
                observedSet++;
                nextRotationStatus();
                return 1;
            }
        }
        else if(rotationStatus == 3){
            if(observedSet+2 > decoKeySet.get(0)) {
                System.out.println("Don't do any more quests(quest would skip your set)");
                return -1;
            }
            else{
                observedSet+=2;
                nextRotationStatus();
                return 1;
            }
        }
        else{
            observedSet = 1;
            return 0;
        }
    }

    public void calculateMeld(){
        remainingMelds = desiredDecoPlacesMap.get(decoKeySet.get(0));
    }

    public void simulate(){
        while (observedSet < decoKeySet.get(0)){
            System.out.println("current observed: " + observedSet);
            if(isQuestPossible() == 1){
                remainingQuests++;
            }
            else if(isQuestPossible() == -1){
                calculateMeld();
                break;
            }
        }
    }


    public String toString(){
        if(remainingMelds == 0) {
            return "Quests Left: " + remainingQuests;
        }
        else if (remainingQuests == 0){
            return "Melds Left: " + remainingMelds;
        }
        else{
            return "Quests Left: " + remainingQuests + " Melds Left: " + remainingMelds;
        }
    }

    public int getRemainingQuests() {
        return remainingQuests;
    }

    public int getRemainingMelds() {
        return remainingMelds;
    }

    public void decrementRemainingQuests(){
        remainingQuests--;
    }
}
