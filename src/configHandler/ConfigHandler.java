package configHandler;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {

    private File cfgFile;
    private File defCfgFile;

    private Properties properties;
    private Properties defProps;


    public ConfigHandler(){

        String fileSeparator = System.getProperty("file.separator");
        String cfgFilePath = "cfg" + fileSeparator + "config.properties";
        String defCfgFilePath = "cfg" + fileSeparator + "default.properties";

        cfgFile = new File(cfgFilePath);
        defCfgFile = new File(defCfgFilePath);

        this.defProps = new Properties();
        this.properties = new Properties();
    }


    //if cfg exist == true
    private void loadCfgFile(File file, Properties properties) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        properties.load(fis);
        fis.close();
    }

    private void saveCfgFile(File file, Properties properties) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        properties.store(fos, "");
        fos.close();
    }

    private void useProperties(Properties properties) {

    }

    /**
     * configure default properties object
     */
    private void createDefaultProps() throws IOException {
        saveCfgFile(defCfgFile, defProps);
    }

    public void firstRun() throws IOException {
        if((!defCfgFile.exists() || defCfgFile.length() == 0) && (!cfgFile.exists() || cfgFile.length() == 0)){
            defCfgFile.getParentFile().mkdir();
            defCfgFile.createNewFile();
            cfgFile.createNewFile();
            createDefaultProps();
            System.out.println("created default");
        }

        if(cfgFile.exists()){
            if(cfgFile.length() == 0){
                System.out.println(cfgFile.getName() + "is empty, loading defaults");
            } else {
               loadCfgFile(cfgFile, properties);
            }
        }
        if(!properties.isEmpty()){
            useProperties(properties);
        } else if(defCfgFile.exists()){
            if(defCfgFile.length() != 0) {
                loadCfgFile(defCfgFile, defProps);
                useProperties(defProps);
            } else {
                System.out.println(defCfgFile.getName() + "is empty, loading defaults");
            }
        }
    }

    public void saveCustomProperties() throws IOException {
        saveCfgFile(cfgFile, properties);
    }

    public Properties getProperties() {
        return properties;
    }

}
