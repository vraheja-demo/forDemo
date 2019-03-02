package Page;

import Base.BaseClass;
import Util.APIUtil;
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

/*public static void initElemnts(WebDriver driver, Object obj){



}*/

public PageObjects(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);

}

    /**
     * This method check if Jason response contains any data matching the getText of any button
     * if found Click on the button
     *
     */

public void buttonClick(String buttonData, List<String> data){
try {
    if (buttonData == Button1.getText()) {
        Button1.click();
        dbConnection.InsertDoscument(data);

    } else if (buttonData == Button2.getText()) {
        Button2.click();

    } else if (buttonData == Button2.getText()) {
        Button3.click();
    }
}catch (Exception e){
    Reporter.log("The Jason Response doesn't have any text related to Buttons available");
}



}

}
