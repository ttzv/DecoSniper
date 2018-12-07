package configHandler;

public enum Config {

    SteamIdDir("SteamIdDir"),
    BackupDir("BackupDir");

    private String sName;

    Config(String sName){
        this.sName = sName;
    }
}
