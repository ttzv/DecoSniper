package logic.questsCounter;

import java.util.*;

/**
 * Calculates amount of quests that need to be completed before next meld.
 * If desired deco is in next setVanishingText and current rotationStatus == 2 it calls for a method
 * that checks remaining places and returns minimal decos that need to be melded to reach desired deco
 */
public class RotationSimulator {


    private Map<Integer, Integer> desiredDecoPlacesMap;
    private ArrayList<Integer> decoKeySet;
    private int currentRotation;
    private int rotationStatus;
    private int remainingQuests;
    private int remainingMelds;
    private int observedSet;

    public RotationSimulator(Map<Integer, Integer> desiredDecoPlacesMap, int rotationStatus){
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

    /**
     * Simulates completing a single quest. This manipulates the rotationStatus and current observedSet. Also you cannot
     * progress quests when there are no sets left, if you do, rotationStatus and observedSet won't change.
     * @returns true if quest was completed succesfully, false if there was not enough sets generated.
     */
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
                System.out.println("Don't do any more quests(quest would skip your setVanishingText)");
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
        if(decoKeySet.size() >= 1) {
            while (observedSet < decoKeySet.get(0)) {
                System.out.println("current observed: " + observedSet);
                if (isQuestPossible() == 1) {
                    remainingQuests++;
                } else if (isQuestPossible() == -1) {
                    calculateMeld();
                    break;
                }
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
