package com.example.damien.lancamentodehoras.ws;


import android.content.Context;

/**
 * Created by damien on 17/07/15.
 */
public class LancarHoraWS {
    private DownloadJsonAsyncTask jsonTask;

    Context contextActivity;

    public  LancarHoraWS ( Context contextActivity){
        this.contextActivity = contextActivity;
        jsonTask = new DownloadJsonAsyncTask(contextActivity);
    }
    public void lancarHora(){
        jsonTask.execute("https://api.twitter.com/1/trends/23424768.json");
    }
}
