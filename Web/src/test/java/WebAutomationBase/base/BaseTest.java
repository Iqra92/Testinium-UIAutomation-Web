package WebAutomationBase.base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {

  protected static WebDriver driver;

  protected static WebDriverWait webDriverWait;

  private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

  public static String browserName = "chrome";

  DesiredCapabilities capabilities = new DesiredCapabilities();


  @BeforeScenario

  public void setUp() throws Exception {

    String baseUrl = "https://dev-gambling.ligabet.com/";
//    String baseUrl = "https://dashboard-test.pagsmile.com/";
    String selectPlatform = "win";
    String selectBrowser = "chrome";

    //WebDriverManager.chromedriver().setup();  https://webtest.etasimacilik.com/

    if (StringUtils.isEmpty(System.getenv("key"))) {
      if ("win".equalsIgnoreCase(selectPlatform)) {
        if ("chrome".equalsIgnoreCase(selectBrowser)) {
          ChromeOptions options = new ChromeOptions();
          capabilities = DesiredCapabilities.chrome();
          capabilities.setCapability("autoGrantPermissions", false);
          capabilities.setCapability("dismissAlert", true);
          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("profile.default_content_setting_values.notifications", 2);
          prefs.put("credentials_enable_service", false);
          prefs.put("profile.password_manager_enabled", false);
          options.setExperimentalOption("prefs", prefs);
          //  options.addArguments("--kiosk");
          options.addArguments("--start-fullscreen");
          options.addArguments("--disable-notifications");
          options.addArguments("disable-popup-blocking");
          System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver.exe");
          driver = new ChromeDriver(options);
          driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
          Thread.sleep(5000);

        } else if ("firefox".equalsIgnoreCase(selectBrowser)) {
          FirefoxOptions options = new FirefoxOptions();
          capabilities = DesiredCapabilities.firefox();
          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("profile.default_content_setting_values.notifications", 2);
          options.addArguments("--kiosk");
          options.addArguments("--disable-notifications");
          options.addArguments("--start-fullscreen");
          FirefoxProfile profile = new FirefoxProfile();
          capabilities.setCapability(FirefoxDriver.PROFILE, profile);
          capabilities.setCapability("marionette", true);
          System.setProperty("webdriver.gecko.driver", "web_driver/geckodriver.exe");
          driver = new FirefoxDriver(options);
          driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        }


      } else if ("mac".equalsIgnoreCase(selectPlatform)) {
        if ("chrome".equalsIgnoreCase(selectBrowser)) {
          ChromeOptions options = new ChromeOptions();
          capabilities = DesiredCapabilities.chrome();
          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("profile.default_content_setting_values.notifications", 2);
          options.setExperimentalOption("prefs", prefs);
          // options.addArguments("--kiosk");
          options.addArguments("--disable-notifications");
          options.addArguments("--start-fullscreen");
          System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver");
          driver = new ChromeDriver(options);
          driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        } else if ("firefox".equalsIgnoreCase(selectBrowser)) {
          FirefoxOptions options = new FirefoxOptions();
          capabilities = DesiredCapabilities.firefox();
          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("profile.default_content_setting_values.notifications", 2);

          options.addArguments("--kiosk");
          options.addArguments("--disable-notifications");
          //  options.addArguments("--start-fullscreen");
          FirefoxProfile profile = new FirefoxProfile();
          capabilities.setCapability(FirefoxDriver.PROFILE, profile);
          capabilities.setCapability("marionette", true);
          System.setProperty("webdriver.gecko.driver", "web_driver/geckodriver");
          driver = new FirefoxDriver(options);
          driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        }

      }

    } else {
      FirefoxOptions options = new FirefoxOptions();
      capabilities = DesiredCapabilities.firefox();
      //ChromeOptions options = new ChromeOptions();
      //capabilities = DesiredCapabilities.chrome();

      //options.setExperimentalOption("w3c", false);

      // options.addArguments("disable-translate");

      // options.addArguments("--disable-notifications");

      options.addArguments("--start-fullscreen");

      Map<String, Object> prefs = new HashMap<>();

      //options.setExperimentalOption("prefs",prefs);

      capabilities.setCapability(ChromeOptions.CAPABILITY, options);

      capabilities.setCapability("key", System.getenv("key"));

      browserName = System.getenv("browser");

      driver = new RemoteWebDriver(new URL("http://hub.testinium.io/wd/hub"), capabilities);

    }

    //driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);

    driver.manage().window().fullscreen();

    driver.get(baseUrl);
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


  }


//  @AfterScenario
//  public void tearDown() {
//    driver.quit();
//
//  }

}