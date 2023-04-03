package maganto.utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
public class JenkinsBrowserSetup {
    public boolean setHeadlessModeIfLinux(ChromeOptions chromeOptions){
        String osname=System.getProperty("os.name");
        WebDriverManager.chromedriver().setup();
        if(osname.toLowerCase().contains("linux")){
            System.out.println("Using headless browser mode");
            chromeOptions.addArguments(Arrays.asList("--headless","--disable-gpu"));
            chromeOptions.addArguments("window-size=1200,1100");
            return true;
        }
        else
            return false;
    }
}
