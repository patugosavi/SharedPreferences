package com.example.sharedpreferences.utils;

public class SmsOperations {
    private static String filterMessage(String message) {
        message = message.replace("&", "&amp;");
        message = message.replace("#", ";hash");
        message = message.replace("+", "plus;");
        message = message.replace(",", "comma;");
        return message;
    }

    public static String getSmsUrl(String mobileNo, String message) {
        String msg = filterMessage(message);
        String url = "http://www.smsjust.com/blank/sms/user/urlsms.php?username=" + Constants.userid + "&pass=" + Constants.password;
        url = url + "&senderId=" + Constants.senderId + "&dest_mobileno=" + mobileNo + "&message=" + msg + "&response=Y";
        return url;
    }
}
