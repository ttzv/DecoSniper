package AppProperties;

import java.util.HashMap;

class PDef {
    //KEYS - for ease of use define static Strings here to quickly use them in your program
    static String SteamIdDir = "SteamIdDir";
    static String BackupDir = "BackupDir";


    String[] defKeys = {
            "SteamIdDir"
    };

    static HashMap<String, String> defVals;

    public PDef(){
        //populate HashMap of Values here

    }
}
