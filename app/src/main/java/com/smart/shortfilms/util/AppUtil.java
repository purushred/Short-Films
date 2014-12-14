package com.smart.shortfilms.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by Purushotham on 04-11-2014.
 */
public class AppUtil {

    public static final String APP_ENDPOINT_URL = "http://uplay-reddy.rhcloud.com/shortfilms/";
    public static final String YOUTUBE_DATA_API_KEY = "AIzaSyC4lB1FaGaWXi8QION_09UvlJJWzACwhSY";

    /**
     * To check if the internet connection is active.
     * @param cm
     * @return
     */
    public static boolean isNetworkOnline(ConnectivityManager cm) {
        boolean status=false;
        try{
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()== NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            Log.e("Network Error", e.getMessage());
            return false;
        }
        return status;
    }
}
