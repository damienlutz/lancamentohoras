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
public class DownloadJsonAsyncTask extends AsyncTask<String, Void, List<Trend>> {
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
    protected List<Trend> doInBackground(String... params) {
        String urlString = params[0];
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(urlString);
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                String json = toString(instream);
                instream.close();
                List<Trend> trends = getTrends(json);
                return trends;
            }
        } catch (Exception e) {
            Log.e("DEVMEDIA", "Falha ao acessar Web service", e);
        }
        return null;
    }

    private List<Trend> getTrends(String jsonString) {
        List<Trend> trends = new ArrayList<Trend>();
        try {
            JSONArray trendLists = new JSONArray(jsonString);
            JSONObject trendList = trendLists.getJSONObject(0);
            JSONArray trendsArray = trendList.getJSONArray("trends");
            JSONObject trend;
            for (int i = 0; i < trendsArray.length(); i++) {
                trend = new JSONObject(trendsArray.getString(i));
                Log.i("DEVMEDIA", "nome=" + trend.getString("name"));
                Trend objetoTrend = new Trend();
                objetoTrend.name = trend.getString("name");
                objetoTrend.url = trend.getString("url");
                trends.add(objetoTrend);
            }
        } catch (JSONException e) {
            Log.e("DEVMEDIA", "Erro no parsing do JSON", e);
        }
        return trends;
    }

    @Override
    protected void onPostExecute(List<Trend> result) {
        super.onPostExecute(result);
        dialog.dismiss();
        if (result.size() > 0) {
            ArrayAdapter<Trend> adapter = new ArrayAdapter<Trend>(contextActivity, android.R.layout.simple_list_item_1, result);
            //setListAdapter(adapter);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(contextActivity).setTitle("Atenção").setMessage("Não foi possivel acessar essas informções...").setPositiveButton("OK", null);
            builder.create().show();
        }
    }

    private String toString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }
}