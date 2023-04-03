package maganto.utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ApplicationConfig {
    //method to read a specific key value pair from config file

    public static String readFromConfigProperties(String fileName,String key){
        Properties properties=new Properties();
        String workingDirectory=System.getProperty("user.dir");
        String value;
        try {
            properties.load(new FileInputStream(workingDirectory+ File.separator+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        value=properties.getProperty(key);
        System.out.println(String.format("%s=%s",key,value));
        return value;
    }
}
