package Util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class APIUtil {

    public static CloseableHttpClient httpclient;
    public static HttpGet gettest;
    /**
     *This method gets the Response from the Rest API using HttpClient for sending the Get Request and Store Response in a Variable
     * Which then i use to check the status code and other
     * @param url
     * @param heardermap
     * @throws IOException
     */
    public static CloseableHttpResponse getAPIData(String url, HashMap<String, String> heardermap)throws IOException {

    httpclient = HttpClients.createDefault();
    gettest = new HttpGet(url);
    for(Map.Entry<String, String> entry: heardermap.entrySet()){
        gettest.addHeader(entry.getKey(), entry.getValue());
    }
    CloseableHttpResponse respone= httpclient.execute(gettest);
    return  respone;
    }
}
