package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {


private static WebDriver driver;

public static WebDriver getDriver() {
    System.setProperty("webdriver.chrome.driver", "C:\\exe\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    return driver;

}
}
