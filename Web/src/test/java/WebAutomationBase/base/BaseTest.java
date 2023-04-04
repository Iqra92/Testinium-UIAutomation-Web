package WebAutomationBase.base;

import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {
  public static final String MOB_HEADER = "chrome-modheader/modheader.crx";

  public static final String ADD_HEADERS_CLOUDFLARE = "https://webdriver.modheader.com/add?cf-access-client-id=ecc777a18e7a511f683db6fde508c6f1.access&cf-access-client-secret=58956fd1b52c3b436ac2ac8bea82af5586b76d878b465f7390aa7a2a30ca8503";
  protected static WebDriver driver;

  protected static WebDriverWait webDriverWait;

  private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

  public static String browserName = "chrome";

  DesiredCapabilities capabilities = new DesiredCapabilities();


  @BeforeScenario

  public void setUp() throws Exception {

     String baseUrl = "https://dev-gambling.ligabet.com/";
    String selectPlatform = "win";
    String selectBrowser = "chrome";
    System.getProperty("./src/test/resources/chrome-modheader/modheader.crx");

    //WebDriverManager.chromedriver().setup();  https://webtest.etasimacilik.com/

    if (StringUtils.isEmpty(System.getenv("key"))) {
      if ("win".equalsIgnoreCase(selectPlatform)) {
        if ("chrome".equalsIgnoreCase(selectBrowser)) {

          Path currentRelativePath = Paths.get(MOB_HEADER);
          ChromeOptions options = new ChromeOptions();
          options.addExtensions(new File(currentRelativePath.toAbsolutePath().toString()));
          capabilities = DesiredCapabilities.chrome();
          capabilities.setCapability("autoGrantPermissions", false);
          capabilities.setCapability("dismissAlert", true);


          Map<String, Object> prefs = new HashMap<String, Object>();
          prefs.put("profile.default_content_setting_values.notifications", 2);
          prefs.put("credentials_enable_service", false);
          prefs.put("profile.password_manager_enabled", false);
          options.setExperimentalOption("prefs", prefs);
          options.addArguments("--start-fullscreen");
          options.addArguments("disable-popup-blocking");
          System.setProperty("webdriver.chrome.driver", "web_driver/chromedriver.exe");
          driver = new ChromeDriver(options);
          driver.get(ADD_HEADERS_CLOUDFLARE);
          new WebDriverWait(driver, 5).until(ExpectedConditions.titleIs("Done"));
          driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
          Thread.sleep(4000);

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