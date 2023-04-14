package Com.Vodafone.Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.*;
import java.util.Properties;

public class Base {
  public static WebDriver driver ;

   public static Properties properties = new Properties();



    public static void openBrowser() throws IOException {

        File file = new File("/Users/ahmed/IdeaProjects/untitled/src/main/java/Vodafone/Config/Config.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);

      if(properties.getProperty("browser").equalsIgnoreCase("chrome")){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(option);

      } else if (properties.getProperty("browser").equalsIgnoreCase("fireFox")) {
          WebDriverManager.firefoxdriver().setup();
          driver = new FirefoxDriver();
      }
      else {
        throw new Error("browser not supported" );

      }
      driver.manage().window().maximize();
      driver.get( properties.getProperty("baseUrl"));
  }
}
