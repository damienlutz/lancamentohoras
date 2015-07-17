package com.example.damien.lancamentodehoras.ws;

/**
 * Created by damien on 17/07/15.
 */
public class LancarHoraWS {
    private DownloadJsonAsyncTask naoSei = new DownloadJsonAsyncTask();

    public void lancarHora(){
        naoSei.execute("https://api.twitter.com/1/trends/23424768.json");
    }
}
