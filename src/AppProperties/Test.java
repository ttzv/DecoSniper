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

    static class TestPropsModule1 extends Propsicl {

        @Override
        public void defaultPropsVals() {
            defPropSet(PDef.BackupDir, "testtttttttttt/ttt/t/t/t/");
            defPropSet(PDef.SteamIdDir, "val/val1/val2");
        }
    }


    public static void main(String[] args) throws IOException {

        DecoSniperProps decoSniperProps = new DecoSniperProps();
        TestPropsModule1 testPropsModule1 = new TestPropsModule1();

        decoSniperProps.init();

        decoSniperProps.setProperty(PDef.SteamIdDir, "differentdir/dir/dir1");

        decoSniperProps.saveFile();

        testPropsModule1.init();

        testPropsModule1.setProperty(PDef.BackupDir, "Changed/changed1");

        decoSniperProps.setProperty(PDef.BackupDir, "b/b/b/b");

        testPropsModule1.setProperty(PDef.SteamIdDir, "iD/Steam/ID");

        testPropsModule1.saveFile();




    }
}


