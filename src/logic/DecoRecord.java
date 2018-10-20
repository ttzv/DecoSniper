package logic;

import java.util.*;

public class DecoRecord {
    private ArrayList<ArrayList<Integer>> decoList;
    private ArrayList<Integer> decoSet;
    private ArrayList<Integer> desiredDecoPlaces;
    private LinkedHashMap<Integer, Integer> desiredDecoPlacesMap;
    private Scanner reader;
    private int numberOfSets = 0;
    private int observedSet = 1;
    private int focusedSet = 0;
    //private int[] rotation;

    /**
     * Variable containing information about place of rotation (1-1-2).
     * 0 - not set
     * 1 - first place
     * 2 - second place
     * 3 - third place
     */
    private int rotationStatus=0;

    public DecoRecord(){
        decoList = new ArrayList<>();
        desiredDecoPlaces = new ArrayList<>();
        desiredDecoPlacesMap = new LinkedHashMap<>();
        reader = new Scanner(System.in);
    }

    /**
     * Simulates completing a single quest. This manipulates the rotationStatus and current observedSet. Also you cannot
     * progress quests when there are no sets left, if you do, rotationStatus and observedSet won't change.
     * @returns true if quest was completed succesfully, false if there was not enough sets generated.
     */
    public boolean doQuest(){
        if(rotationStatus > 0 && rotationStatus < 3){
            if (observedSet+1 > getNumberOfSets()) {
                System.out.println("Not enough sets generated");
                return false;
            }
            else{
                observedSet++;
                nextRotationStatus();
                return true;
            }
        }
        else if(rotationStatus == 3){
            if(observedSet+2 > getNumberOfSets()) {
                System.out.println("Not enough sets generated");
                return false;
            }
            else{
                nextRotationStatus();
                observedSet+=2;
                return true;
            }
        }
        else{
            observedSet = 1;
            return false;
        }
        //skipByS
    }

    public void doMeld(){

    }

    /**
     * Skip number of places(decos) - possible only by melding
     */
    public void skipByPlaces(int skipped){
        for (int i = 0; i <= skipped; i++){
            decoList.remove(i);
        }
    }

    /**
     * Save places of desired decos (multiple supported)
     */
    public void saveDecoPlace(int place, int id){
        /*int placeInList = 3 * (numberOfSets-1) + place;
        System.out.println("place in list: " + placeInList);*/
        System.out.println(decoList.size());
        decoList.get(numberOfSets - 1).set(place-1, id);
        desiredDecoPlaces.add( ( getNumberOfSets()-1 ) * 3 + place);

        //new method of saving below
        desiredDecoPlacesMap.put(numberOfSets, place);

    }

    /**
     * Generates next set of decos
     */
    public void nextSet(){
        numberOfSets++;
        decoSet = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            decoSet.add(0);
        }
        decoList.add(decoSet);

        focusedSet = numberOfSets;
    }



    public ArrayList<ArrayList<Integer>> getDecoList() {
        return decoList;
    }

    public ArrayList<Integer> getDesiredDecoPlaces() {
        return desiredDecoPlaces;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    /**
     *
     * @return Int value of which index of decoList is focused lowered by one
     */
    public int getFocusedSetProperty() {
        return focusedSet-1;
    }

    public void focusedSetPropertyIncrement(){
        if (focusedSet < numberOfSets) {
            focusedSet++;
        }
        else System.out.println("No more sets to load");
    }

    public void focusedSetPropertyDecrement(){
        if (focusedSet > 1){
            focusedSet--;
        }
        else System.out.println("No previous sets");
    }

    /**
     *
     *
     * @return Currently focused set of decorations that is visible in GUI in ArrayList format
     *
     */
    public ArrayList<Integer> getFocusedSet(){
        return decoList.get(getFocusedSetProperty());
    }

    /**
     * Sets specified slot to given ID
     * @param id Decoration ID
     * @param slot 1, 2 or 3 - slot number
     */
    public void setSlotInFocusedSet(int slot, int id){
        System.out.println("focusedproperty: " + getFocusedSetProperty());
        ArrayList<Integer> testing = decoList.get(getFocusedSetProperty());
        decoList.get(getFocusedSetProperty()).set(slot-1, id);
    }

    /**
     * rotates between 1, 2, 3 numbers, starting number is inputted by user, value changes only when quest is completed
     */
    public void nextRotationStatus(){
        rotationStatus++;
        if(rotationStatus > 3){
            rotationStatus = 1;
        }
    }
    /**
     * Checks starting position in rotation and returns how many places to skip
     * @return 1 or 2 depending on place in rotation
     */
    public int getCurrentRotationStatus() {

        int[] rotation = {1, 1, 2};
        System.out.println("rotationStatus from method is: " + rotationStatus);
        return rotation[rotationStatus-1];

    }

    public LinkedHashMap<Integer, Integer> getDesiredDecoPlacesMap() {
        return desiredDecoPlacesMap;
    }

    /**
     * Resets everything
     */
    public void clearAll(){
        decoList.clear();
        decoSet.clear();
        desiredDecoPlaces.clear();
        desiredDecoPlacesMap.clear();
        numberOfSets = 0;
        focusedSet = 0;
        observedSet = 1;
    }

    public void start(){
        System.out.println("Which place of rotation are you in (1, 2, 3): ");
        rotationStatus = Integer.parseInt(reader.nextLine());
        for (int i = 0; i <= 10; i++) {
            System.out.println("Roll decos, if you get desired deco input its place here:");
            nextSet();
            String stringInput = reader.nextLine();
            if(!stringInput.isEmpty()) {
                System.out.println("input deco ID: ");
                String secondInput = reader.nextLine();
                saveDecoPlace(Integer.parseInt(stringInput), Integer.parseInt(secondInput));
            }
            System.out.println("Sets: " + getNumberOfSets() +
                    "\nDecoList: " + getDecoList() +
                    "\nDesiredDecos(Array): " + getDesiredDecoPlaces() +
                    "\nDesiredDecos(HashMap): " + getDesiredDecoPlacesMap() +
                    "\nCurrent rota status: " + getCurrentRotationStatus() +
                    "\nCurrent observedSet: " + observedSet);

           /* System.out.println("Do quest? y/n: ");
            stringInput = reader.nextLine();
            if(!stringInput.isEmpty() && stringInput.equals("y".trim().toLowerCase())){
                System.out.println("Completing quest: " + doQuest());
            }*/
        }
        System.out.println("Testing simulation...");
        RotationSimulator rotaSim = new RotationSimulator(getDesiredDecoPlacesMap(), getCurrentRotationStatus());
        rotaSim.simulate();
        System.out.println("Result is: \n" + rotaSim);

        int questCounter = rotaSim.getRemainingQuests();
        int meldCounter = rotaSim.getRemainingMelds();

    }



}
