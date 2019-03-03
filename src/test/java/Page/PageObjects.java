package Page;

import Base.BaseClass;
import Util.DBUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.testng.Reporter;

import java.util.List;


public class PageObjects extends BaseClass {
WebDriver driver;
DBUtil dbConnection;
@FindBy(id="Button1")
static WebElement Button1;

@FindBy(id="Button2")
static WebElement Button2;


@FindBy(id="Button3")
static WebElement Button3;

public PageObjects(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);
}

    /**
     * This method check if Json response contains any data matching the getText of any button
     * if found Click on the button
     *
     */

public void buttonClick(String buttonData, List<String> data){
try {
     if (buttonData == Button1.getText()) {
        Button1.click();
        List<String> firstTwoRecords = data.subList(0, 2);
        dbConnection.InsertDocument(firstTwoRecords);

    } else if (buttonData == Button2.getText()) {
        Button2.click();
        List<String> nextTwoRecords = data.subList(2, 4);
        dbConnection.InsertDocument(nextTwoRecords);

    } else if (buttonData == Button2.getText()) {
        Button3.click();
        List<String> lastTwoRecords = data.subList(4, 6);
        dbConnection.InsertDocument(lastTwoRecords);
    }
}catch (Exception e){
    Reporter.log("The Json Response doesn't have any text related to Buttons available");
    }
  }
}
