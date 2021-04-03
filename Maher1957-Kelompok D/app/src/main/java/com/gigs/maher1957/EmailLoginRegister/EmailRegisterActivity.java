package com.gigs.maher1957.EmailLoginRegister;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Activity.MainActivity;
import com.gigs.maher1957.Activity.PembayaranActivity;
import com.gigs.maher1957.Activity.ProfileActivity;
import com.gigs.maher1957.Activity.VerifyActivity;
import com.gigs.maher1957.Models.LoginRegister.Register.ResponseRegister;
import com.gigs.maher1957.Models.LoginRegister.Register.Success;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.R;
import com.gigs.maher1957.API.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, email, password,c_password;
    private Button regBtn;
    private ImageView btnBack;
    private CheckBox showPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_register);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    private void init() {

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        c_password = (EditText) findViewById(R.id.password2nd);
        regBtn = (Button) findViewById(R.id.regBtn);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        showPass = (CheckBox) findViewById(R.id.checkBox);

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showPass.isChecked()){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    c_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    c_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        findViewById(R.id.regBtn).setOnClickListener(this);
        findViewById(R.id.btnBack).setOnClickListener(this);
    }

    private void Registration() {

        name.setError(null);
        email.setError(null);
        password.setError(null);
        c_password.setError(null);

        String user_name = name.getText().toString().trim();
        String user_email= email.getText().toString().trim();
        String user_password = password.getText().toString().trim();
        String user_c_password = c_password.getText().toString().trim();

        if(TextUtils.isEmpty(user_name)) {
            name.setError("Name is required");
            name.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(user_email))
        {
            email.setError("Email is required");
            name.requestFocus();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(user_email).matches())
        {
            email.setError("Email has been used");
            email.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(user_password))
        {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        else if(password.length() <6 )
        {
            password.setError("Password Should be atleast 6 character long");
            password.requestFocus();
            return;
        }
        if(!user_password.equals(user_c_password))
        {
            c_password.setError("Passwords must be the same");
            c_password.requestFocus();

        }
        else
        {
            ProgressDialog dialog = new ProgressDialog(this);
            dialog.setTitle("Registering...");
            dialog.setMessage("Please wait we are adding your account");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

//            BodyRegister bodyRegister =  new BodyRegister();
//            bodyRegister.setName(user_name);
//            bodyRegister.setEmail(user_email);
//            bodyRegister.setPassword(user_password);
//            bodyRegister.setCPassword(user_c_password);

            RestClient.getService().postRegister(user_name,user_email,user_password,user_c_password).enqueue(new Callback<ResponseRegister>() {
                @Override
                public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                    if(response.isSuccessful() && response.body() !=null){
                        dialog.dismiss();
                        String success = response.body().getSuccess().getMessage();

                        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(EmailRegisterActivity.this);
                        alertDialog2.setMessage("Send Verification to Email");
                        alertDialog2.setIcon(R.drawable.ic_arrow_left_24);
                        alertDialog2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                             Intent intent = new Intent (EmailRegisterActivity.this, VerifyActivity.class);
                             startActivity(intent);
                             Toast.makeText(EmailRegisterActivity.this, ""+success, Toast.LENGTH_SHORT).show();
                             finish();
                            }
                        });
                        alertDialog2.show();
//                        Animatoo.animateSwipeLeft(EmailRegisterActivity.this);
                    }
                    else {
                        dialog.dismiss();
                        Toast.makeText(EmailRegisterActivity.this, "Please check your email and password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegister> call, Throwable t) {
                    Log.d("asd","fail");
                    Toast.makeText(EmailRegisterActivity.this, "Registration Fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void goToLogin(View view) {
        Intent intent = new Intent(EmailRegisterActivity.this, EmailLoginActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(EmailRegisterActivity.this);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regBtn:
                Registration();
                break;
            case R.id.btnBack:
                Intent intent = new Intent(EmailRegisterActivity.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateCard(EmailRegisterActivity.this);
                finish();
        }
    }
}
