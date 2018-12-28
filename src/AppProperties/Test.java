package AppProperties;

import java.io.IOException;
import java.util.Properties;


public class Test {

    static class DecoSniperProps extends Propsicl {

        @Override
        public void defaultPropsVals() {
            defPropSet(PDef.SteamIdDir, "test/test1/idnumber");
            defPropSet(PDef.BackupDir, "testback/testback1/dir/dir1");
        }

    }


    public static void main(String[] args) throws IOException {

        DecoSniperProps decoSniperProps = new DecoSniperProps();

        decoSniperProps.init();

        decoSniperProps.setProperty(PDef.SteamIdDir, "differentdir/dir/dir1");

        decoSniperProps.saveFile();

        System.out.println(decoSniperProps.retrieveProp(PDef.SteamIdDir));
        System.out.println(decoSniperProps.retrieveProp(PDef.BackupDir));


    }
}


