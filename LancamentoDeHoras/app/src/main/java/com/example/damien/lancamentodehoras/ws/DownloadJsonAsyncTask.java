package com.example.damien.lancamentodehoras.ws;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.damien.lancamentodehoras.home.HomeActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 17/07/15.
 */
public class DownloadJsonAsyncTask extends AsyncTask<String, Void, String> {
    ProgressDialog dialog;

    Context contextActivity;

    public  DownloadJsonAsyncTask ( Context contextActivity){
        this.contextActivity = contextActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(contextActivity, "Aguarde", "Baixando JSON, Por Favor Aguarde...");
    }
 @Override
    protected String doInBackground(String... uri) {
        InputStream inputStream = null;
    String result = "";
    try {

        // 1. create HttpClient
        HttpClient httpclient = getNewHttpClient();

        // 2. make POST request to the given URL
        HttpPost httpPost = new HttpPost(uri[0]);

        String json = "";

        // 3. build jsonObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("user", 1);
        jsonObject.accumulate("student_id", 1);
        jsonObject.accumulate("user_email", "test@test.com");
        jsonObject.accumulate("from", "Fri Oct 10 12:38:00 2014 GMT+0200");
        jsonObject.accumulate("to", "Sat Oct 11 12:38:00 2014 GMT+0200");

        // 4. convert JSONObject to JSON to String
        json = jsonObject.toString();

        // ** Alternative way to convert Person object to JSON string usin Jackson Lib
        // ObjectMapper mapper = new ObjectMapper();
        // json = mapper.writeValueAsString(person);

        // 5. set json to StringEntity
        StringEntity se = new StringEntity(json);

        // 6. set httpPost Entity
        httpPost.setEntity(se);

        // 7. Set some headers to inform server about the type of the content
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        // 8. Execute POST request to the given URL
        HttpResponse httpResponse = httpclient.execute(httpPost);

        // 9. receive response as inputStream
        inputStream = httpResponse.getEntity().getContent();

        // 10. convert inputstream to string
        if(inputStream != null)
            result = convertInputStreamToString(inputStream);
        else
            result = "Did not work!";

    } catch (Exception e) {
        Log.d("InputStream", e.getLocalizedMessage());
    }

    // 11. return result
    return result;
    }
    
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
        Log.d(TAG,result);
    }
    
    public static HttpClient getNewHttpClient() {

     HttpParams params = new BasicHttpParams();
     HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
     HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
     HttpProtocolParams.setUseExpectContinue(params, true);

     SchemeRegistry schReg = new SchemeRegistry();
     schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
     schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
     ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

     DefaultHttpClient http = new DefaultHttpClient(conMgr, params);
     /*
     UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("name", "pass");
     AuthScope authScope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT);
     http.getCredentialsProvider().setCredentials(authScope, credentials);*/

     return http;
    }
}
