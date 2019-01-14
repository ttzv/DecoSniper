package logic.decoRecord;

import java.util.*;

public class DecoRecord {
    private ArrayList<ArrayList<Integer>> decoList;
    private ArrayList<Integer> decoSet;
    private Scanner reader;
    private int numberOfSets = 0;
    private int observedSet = 1;
    private int focusedSet = 0;
    /**
     * Variable containing information about place of rotation (1-1-2).
     * 1 - first place (default)
     * 2 - second place
     * 3 - third place
     */
    private int rotationStatus=1;

    public DecoRecord(){
        decoList = new ArrayList<>();
        reader = new Scanner(System.in);
        //nextSet(); //creates new set at start
    }

    /**
     * Simulates completing a single quest. This manipulates the rotationStatus and current observedSet. Also you cannot
     * progress quests when there are no sets left, if you do, rotationStatus and observedSet won't change.
     * @returns true if quest was completed succesfully, false if there was not enough sets generated.
     */
    //TODO: implement
    public void doMeld(){

    }

    /**
     * Skip number of places(decos) - possible only by melding
     */
    public void skipByPlaces(int skipped){
        for (int i = 0; i <= skipped; i++){
            decoList.remove(0);
        }
    }

    /**
     * Save places of desired decos (multiple supported)
     */
    public void saveDecoPlace(int place, int id){
        System.out.println(decoList.size());
        decoList.get(numberOfSets - 1).set(place-1, id);

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

    /**
     *
     * @return true if property was correctly modified, otherwise false
     */

    public boolean focusedSetPropertyIncrement(){
        if (focusedSet < numberOfSets) {
            focusedSet++;
            return true;
        }
        else{

            return false;
        }
    }

    public boolean focusedSetPropertyDecrement(){
        if (focusedSet > 1){
            focusedSet--;
            return true;
        }
        else return false;
    }

    public int getFocusedSetProperty() {
        return focusedSet-1;
    }

    /**
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

    public int getRotationStatus() {
        return rotationStatus;
    }

    /**
     * Checks starting position in rotation and returns how many places to skip
     * @return 1 or 2 depending on place in rotation
     */
    public int getSkipRotation() {
        int[] rotation = {1, 1, 2};
        System.out.println("rotationStatus from method is: " + rotationStatus);
        return rotation[rotationStatus-1];
    }

    /**
     * Resets everything
     */
    public void clearAll(){
        decoList.clear();
        if(decoSet != null) {
            decoSet.clear();
        }
        numberOfSets = 0;
        focusedSet = 0;
        observedSet = 1;
    }

    public ArrayList<ArrayList<Integer>> getDecoList() {
        return decoList;
    }

    public int getNumberOfSets() {
        return numberOfSets;
    }

    /*public void start(){

        System.out.println("List of default Valuable Decos: ");
        System.out.println(Deco.getDefaultValuableDecos());

        System.out.println("Which place of rotation are you in (1, 2, 3): ");
        rotationStatus = Integer.parseInt(reader.nextLine());

        DesiredDecos desiredDecos = new DesiredDecos(decoList);
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
                    "\nDesiredDecos(HashMap): " + desiredDecos.getValuableDecosMap() +
                    "\nCurrent rota status: " + getSkipRotation() +
                    "\nCurrent observedSet: " + observedSet);


           /* System.out.println("Do quest? y/n: ");
            stringInput = reader.nextLine();
            if(!stringInput.isEmpty() && stringInput.equals("y".trim().toLowerCase())){
                System.out.println("Completing quest: " + doQuest());
            }
        }

        System.out.println("Testing simulation...");

        RotationSimulator rotaSimNew = new RotationSimulator(desiredDecos.getValuableDecosMap(), getSkipRotation());
        rotaSimNew.simulate();
        System.out.println("(new)Result is: \n" + rotaSimNew);

    }*/
}
