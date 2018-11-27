package logic.questsCounter;

import decos.Deco;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Class used for determining which decos we are hunting for.
 * If user makes no change to list of desired decos then default values are loaded.
 * Parses main decoList to find if any desired decos were found, then places them in a LinkedHashMap
 * which is further handed to class simulating quests.
 *
 */
public class DesiredDecos {

    private ArrayList<ArrayList<Integer>> decoList; //main decoList
    private ArrayList<Integer> desiredDecoList; //list with desired decos. changeable. at start default values are loaded.
    private LinkedHashMap<Integer, Integer> desiredDecoPlacesMap; //map with places of found desired decos;

    public DesiredDecos(ArrayList<ArrayList<Integer>> decoList) {
        this.decoList = decoList;
        desiredDecoPlacesMap = new LinkedHashMap<>();
    }

    /**
     * Parses main decoList in search of desiredDecos values
     *
     * @return Map with places of desired decos.
     */
    public LinkedHashMap getValuableDecosMap(){
        if(desiredDecoList != null) {
            for (Integer dDeco : desiredDecoList) {
                for (int set = 0; set < decoList.size(); set++) {
                    if (decoList.get(set).contains(dDeco)) {
                        int slot = decoList.get(set).indexOf(dDeco) + 1;
                        desiredDecoPlacesMap.put(set + 1, slot);
                    }
                }
            }

            return this.desiredDecoPlacesMap;
        }else{
            throw new NullPointerException("no items in desired, set some first");
        }

    }

    public void setDesiredDecoList(ArrayList<Integer> valuablesList){
        this.desiredDecoList = valuablesList;
    }

    public ArrayList<Integer> getDesiredDecoList() {
        return desiredDecoList;
    }
}
