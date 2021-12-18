package com.example.sharedpreferences.NetworkService;

//import com.example.drapp.ui.bookappointment.BookAppointmentSaveModelResponse;
//import com.example.drapp.ui.bookappointment.ImageResponse;
import com.example.sharedpreferences.ui.MainModelResponse;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

//    String ApiBaseUrl = "DoctorConsult/api/";

    String ApiBaseUrl = "accounts/";
   // String ApiBaseUrl="https://noteappication.000webhostapp.com/";

    @POST(ApiBaseUrl + "login")
    @FormUrlEncoded
    Observable<MainModelResponse> getLogin(@Field("username")String username,
                                           @Field("password") String password);


}
