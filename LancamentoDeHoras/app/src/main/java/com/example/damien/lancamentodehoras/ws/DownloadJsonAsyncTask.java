package com.example.damien.lancamentodehoras.ws;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.damien.lancamentodehoras.home.HomeActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by damien on 17/07/15.
 */
@SuppressWarnings("deprecation")
public class DownloadJsonAsyncTask extends AsyncTask<String, Void, String> {
    private static final String TAG = "WS";
    ProgressDialog dialog;

    Context contextActivity;

    public DownloadJsonAsyncTask(Context contextActivity) {
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
            
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
               Toast.makeText(contextActivity, "Received!", Toast.LENGTH_LONG).show();
            }

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if (inputStream != null)
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
        Toast.makeText(contextActivity, "Received!", Toast.LENGTH_LONG).show();
        Log.d(TAG, result);
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
    
private static String convertInputStreamToString(InputStream inputStream) throws IOException{
    BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
    String line = "";
    String result = "";
    while((line = bufferedReader.readLine()) != null)
        result += line;

    inputStream.close();
    return result;

}
}
