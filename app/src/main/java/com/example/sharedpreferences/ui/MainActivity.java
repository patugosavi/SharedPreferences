package com.example.sharedpreferences.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharedpreferences.BaseApp.BaseActivity;
import com.example.sharedpreferences.NetworkService.ApiInterface;
import com.example.sharedpreferences.R;
import com.example.sharedpreferences.di.component.ApplicationComponent;
import com.example.sharedpreferences.utils.BaseApp;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView{
    @BindView(R.id.ed_username)
    EditText ed_username;
    @BindView(R.id.ed_password) EditText ed_password;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.rememberme)
    CheckBox rememberme;


    @BindView(R.id.chk_showpassword) CheckBox chk_showpassword;

    @Inject
    ApiInterface apiInterface;

    @Inject
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String MyPREFERENCES="SharePrefe";

    MainPresenter presenter;
    String username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter= new MainPresenter(this,apiInterface);
        sharedPreferences=getSharedPreferences(MyPREFERENCES,MODE_PRIVATE);
        editor=sharedPreferences.edit();

        SessionManager sessionManager=new SessionManager(MainActivity.this,SessionManager.SESSION_REMEMBERME);
        if(sessionManager.checkRememberMe()){
            HashMap<String,String> rememeberMedatails=sessionManager.getRememberMeDetailsFromSession();
            ed_username.setText(rememeberMedatails.get(SessionManager.KEY_SESSIONEMAIL));
            ed_password.setText(rememeberMedatails.get(SessionManager.KEY_SESSIONPASSWORD));
        }

        chk_showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    ed_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    ed_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }

    @Override
    protected void injectDependencies(BaseApp baseApp, ApplicationComponent component) {
        component.inject(this);
    }


    @OnClick(R.id.btn_login)
    public void btn_login(){
        validation();
    }

    private void validation() {
        username = ed_username.getText().toString().trim();
        password = ed_password.getText().toString().trim();

        if (ed_username.getText().toString().length() == 0) {
            ed_username.setError("Please Fill Username !!");
            ed_username.requestFocus();
        } else if (ed_password.getText().toString().length() == 0) {
            ed_password.setError("Please Fill Password !!");
            ed_password.requestFocus();
        } else {
            presenter.doLogin(username, password);
        }
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onSuccess(String a) {

    }

    @Override
    public void onFailure(String onError) {

    }

    @Override
    public void DoLoginOnSuccess(MainModelResponse mainModelResponse) {
        MainModel mainModel=mainModelResponse.getMainModel();
        int status=mainModelResponse.getStatus();
        if(status==200){
            editor = sharedPreferences.edit();
            editor.putString("MOBILENO", mainModel.getMobileNo());
            editor.putInt("ID",mainModel.getPk());
            editor.putString("USERNAME",mainModel.getUsername());
            editor.putString("EMAILID",mainModel.getEmail());
            editor.putInt("GENDER",mainModel.getGender());

            editor.apply();
            editor.commit();
            if (rememberme.isChecked()) {
                SessionManager sessionManager = new SessionManager(MainActivity.this, SessionManager.SESSION_REMEMBERME);
                sessionManager.createRememberMeSession(username, password);
            }
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            Intent intent=new Intent(MainActivity.this,TestActivity.class);
            Pair[] pairs=new Pair[1];

            pairs[0]=new Pair<View,String>(findViewById(R.id.btn_login),"transition_mainactivity");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs );
                startActivity(intent,options.toBundle());
            }else {
                startActivity(intent);
            }
        }
        else {
            showToast("Error");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}