package Script;

import Base.BaseClass;
import Page.PageObjects;
import Util.APIUtil;
import Util.PropertiesUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;



public class VerifyData extends BaseClass {

    WebDriver driver;
    PropertiesUtil pr;
    String homePage, restAPIURL;
    CloseableHttpResponse jsonResponse;
    int statuscode;
    PageObjects obj;
    APIUtil getapiUtil;
    JSONObject jsontest;
    List<String> jsonData;


    @BeforeTest
    public void Setup() throws IOException {
        driver = BaseClass.getDriver();
        PropertiesUtil.getInstance().load(new File("config.properties"));


    }

    @BeforeMethod
    public void Initialize(){
        homePage = pr.getValue("baseurl");
        restAPIURL = pr.getValue("endPointURL");
        driver.navigate().to(homePage);
        obj=new PageObjects(driver);
    }

    /**
     *This Test case will get data from Rest API and as per response Button on Web will be clicked
     *
     */
    @Test
    public  void getJsonData() throws IOException {
        getapiUtil = new APIUtil();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("Accept", "application/json");
        jsonResponse = APIUtil.getAPIData(restAPIURL, map1);
        statuscode = jsonResponse.getStatusLine().getStatusCode();
        String completeResponse= EntityUtils.toString(jsonResponse.getEntity(), "UTF-8");
        jsontest = new JSONObject(completeResponse);
        if(statuscode !=200){
            throw new RuntimeException("HttpResponseCode: " + statuscode);
        }
        else {
            String Button1 = jsontest.getString("buttonName");
            jsonData.add(jsontest.getString("CustomerName"));
            jsonData.add(jsontest.getString("email"));
            jsonData.add(jsontest.getString("FlightFrom"));
            jsonData.add(jsontest.getString("FlightTo"));
            jsonData.add(jsontest.getString("address"));
            jsonData.add(jsontest.getString("Details"));
            jsonData.add(jsontest.getString(""));
            obj.buttonClick(Button1, jsonData);
        }
    }

   

    @AfterMethod
    public void commonStepsAfterMethod(){
       driver.quit();
     }
}


