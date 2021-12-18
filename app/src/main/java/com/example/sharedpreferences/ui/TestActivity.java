package com.example.sharedpreferences.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sharedpreferences.BaseApp.BaseActivity;
import com.example.sharedpreferences.R;
import com.example.sharedpreferences.di.component.ApplicationComponent;
import com.example.sharedpreferences.utils.BaseApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.tv_email)
    TextView tv_email;

    @BindView(R.id.btn_logout)
    Button btn_logout;

    String name,mobile,email;

    @Inject
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public static String MyPREFERENCES ="SharePrefe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ButterKnife.bind(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        editor = sharedpreferences.edit();
        name = sharedpreferences.getString("USERNAME", "");
        mobile=sharedpreferences.getString("MOBILENO","");
        email=sharedpreferences.getString("EMAILID","");

        tv_name.setText(name);
        tv_mobile.setText(mobile);
        tv_email.setText(email);

    }

    @Override
    protected void injectDependencies(BaseApp baseApp, ApplicationComponent component) {
        component.inject(this);
    }

    @OnClick(R.id.btn_logout)
    public void btn_logout(){
LogOut();
    }

    private void LogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_logout, null);
        builder.setView(dialogView);
        builder.setCancelable(false);

        final AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();

        TextView btn_no = dialogView.findViewById(R.id.btn_no);
        TextView btn_yes = dialogView.findViewById(R.id.btn_yes);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                editor.clear();
                editor.apply();
                startActivity(new Intent(TestActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}