package Script;

import Base.BaseClass;
import Page.PageObjects;
import Util.APIUtil;
import Util.DBUtil;
import Util.PropertiesUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.soap.Detail;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class VerifyData extends BaseClass {

    WebDriver driver;
    PropertiesUtil pr;
    String homePage, restAPIURL, APIcustomername, APIEmail, APIFlightFrom, APIFlightto, APIaddress, APIDetails;
    CloseableHttpResponse jsonResponse;
    int statuscode;
    PageObjects obj;
    APIUtil getapiUtil;
    JSONObject jsontest;
    List<String> jsonData;
    DBUtil dbData;

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
            APIcustomername = String.valueOf(jsonData.add(jsontest.getString("CustomerName")));
            APIEmail= String.valueOf(jsonData.add(jsontest.getString("email")));
            APIFlightFrom = String.valueOf(jsonData.add(jsontest.getString("FlightFrom")));
            APIFlightto =String.valueOf(jsonData.add(jsontest.getString("FlightTo")));
            APIaddress = String.valueOf(jsonData.add(jsontest.getString("address")));
            APIDetails = String.valueOf(jsonData.add(jsontest.getString("Details")));
            obj.buttonClick(Button1, jsonData);
        }
    }

    /**
     * This Test case will compare the data from API and MongoDB
     *
     */
    @Test
    public void compareData(){
        List<String> list = new ArrayList<String>();
        list = DBUtil.retrieveDocument();
        String CustomerName = list.get(0);
        String Email = list.get(1);
        String FlightFrom = list.get(2);
        String FlightTo = list.get(3);
        String address = list.get(4);
        String Details = list.get(5);
        Assert.assertEquals(CustomerName, APIcustomername);
        Assert.assertEquals(Email, APIEmail);
        Assert.assertEquals(FlightFrom, APIFlightFrom);
        Assert.assertEquals(FlightTo, APIFlightto);
        Assert.assertEquals(Details,APIDetails);
    }

    @AfterMethod
    public void commonStepsAfterMethod(){
       driver.quit();
     }
}


