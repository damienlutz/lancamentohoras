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
        //TODO Linha com erro abaixo
        // jsonTask = new DownloadJsonAsyncTask(contextActivity);
    }
    public void lancarHora(){
        jsonTask.execute(http://private-7a4a1-ilegratimesheet.apiary-mock.com/service/activity);
    }
}
