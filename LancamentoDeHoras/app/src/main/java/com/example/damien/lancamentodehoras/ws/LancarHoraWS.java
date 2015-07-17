package com.example.damien.lancamentodehoras.ws;

/**
 * Created by damien on 17/07/15.
 */
public class LancarHoraWS {

    public void lancarHora(){

        new DownloadJsonAsyncTask()
                .execute("https://api.twitter.com/1/trends/23424768.json");
    }
}
