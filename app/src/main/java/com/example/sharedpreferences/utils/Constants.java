package com.example.sharedpreferences.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constants {
    public static final String userid = "DrMilindRane";
    public static final String password = "dr$milind@123";
    public static final String senderId = "DRRANE";


//    public static final String PAYTM_MERCHANT_ID = "TttiBs35113757886022"; //YOUR TEST MERCHANT ID pooja pathology work
    public static final String PAYTM_MERCHANT_ID = "pLJiuP95348234743625"; //YOUR TEST MERCHANT ID prathamesh
//public static final String PAYTM_MERCHANT_ID = "tEVkDa76169879946068"; //YOUR TEST MERCHANT ID nilesh sir
    public static final String UTL_PAYTM_CALLBACK = "https://pguat.paytm.com/paytmchecksum/paytmCallback.jsp";
//    public static final String UTL_PAYTM_CALLBACK = "https://securegw.paytm.in/theia/paytmCallback?";
    public static final String url_base = "http://www.your-domain/foldername/";


    public static boolean  isOnline(Context context) {
        ConnectivityManager conn=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =conn.getActiveNetworkInfo();
        return  (networkInfo!=null && networkInfo.isConnected());
    }

}
