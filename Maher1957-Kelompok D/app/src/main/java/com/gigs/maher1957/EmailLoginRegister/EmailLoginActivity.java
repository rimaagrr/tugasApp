package com.gigs.maher1957.EmailLoginRegister;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.Activity.HomePageActivity;
import com.gigs.maher1957.Activity.MainActivity;
import com.gigs.maher1957.Activity.ProfileActivity;
import com.gigs.maher1957.Models.LoginRegister.ForgotPass.ResponseForgetPass;
import com.gigs.maher1957.Models.LoginRegister.Login.User;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.Models.Profile.UserProfile;
import com.gigs.maher1957.R;
import com.gigs.maher1957.Models.LoginRegister.Login.BodyLogin;
import com.gigs.maher1957.Models.LoginRegister.Login.ResponseLogin;
import com.gigs.maher1957.API.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    private Button logBtn,send;
    private ImageView btnBack;
    private TextView forgotPass,register,forgot, signIn,garis;
    private CheckBox showPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        init();
    }

    private void init() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        logBtn = (Button) findViewById(R.id.logBtn);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        forgotPass = (TextView) findViewById(R.id.forgotPass);
        register = (TextView) findViewById(R.id.register);
        signIn = (TextView) findViewById(R.id.signIn);
        showPass = (CheckBox) findViewById(R.id.checkBox);
        garis = (TextView) findViewById(R.id.garis);

        forgot = (TextView) findViewById(R.id.forgot);
        forgot.setVisibility(View.GONE);

        send = (Button) findViewById(R.id.sendToEmail);
        send.setVisibility(View.GONE);

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showPass.isChecked()){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        logBtn.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        forgotPass.setOnClickListener(this);
        register.setOnClickListener(this);
        send.setOnClickListener(this);

    }

    private void Login() {
        email.setError(null);
        password.setError(null);

        String user_email= email.getText().toString().trim();
        String user_password = password.getText().toString().trim();

        if(TextUtils.isEmpty(user_email))
        {
            email.setError("Email is required");
            email.requestFocus();
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
        }
        else {
//            if (email.getText() != null && password.getText() != null) {
//                if (email.getText().toString().equals(user_email) &&
//                        password.getText().toString().equals(user_password)) {
//                    // Not null and OK, launch the activity
//                    startActivity(new Intent("com.DRMS.NewMenu"));
//                } else {
//                    // Username or password false, display and an error
//                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
//
//                    dlgAlert.setMessage("wrong password or email");
//                    dlgAlert.setTitle("Error Message...");
//                    dlgAlert.setPositiveButton("OK", null);
//                    dlgAlert.setCancelable(true);
//                    dlgAlert.create().show();
//
//                    dlgAlert.setPositiveButton("Ok",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            });
//                }
//            } else {
                ProgressDialog dialog = new ProgressDialog(EmailLoginActivity.this);
                dialog.setTitle("Checking...");
                dialog.setMessage("Please wait we are checking your account");
                dialog.show();
                dialog.setCanceledOnTouchOutside(false);

                BodyLogin bodyLogin = new BodyLogin();
                bodyLogin.setEmail(user_email);
                bodyLogin.setPassword(user_password);

                RestClient.getService().postLogin(bodyLogin).enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if(response.isSuccessful() && response.body() !=null){
                            TinyDB tinyDB = new TinyDB(EmailLoginActivity.this);
                            assert response.body() != null;
                            tinyDB.putString("token", response.body().getAccessToken());
                            tinyDB.putObject("user_id", response.body().getUser());

                            dialog.dismiss();
                            Intent intent = new Intent(EmailLoginActivity.this, HomePageActivity.class);
                            startActivity(intent);
                            Animatoo.animateSwipeLeft(EmailLoginActivity.this);
                            finish();
                        } else{
                            dialog.dismiss();
                            Toast.makeText(EmailLoginActivity.this, "Please check your email and password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        Toast.makeText(EmailLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logBtn:
                Login();
                break;
            case R.id.btnBack:
                Intent intent = new Intent(EmailLoginActivity.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateCard(EmailLoginActivity.this);
                break;
            case R.id.register:
                Intent intent2 = new Intent(EmailLoginActivity.this, EmailRegisterActivity.class);
                startActivity(intent2);
                Animatoo.animateSlideRight(EmailLoginActivity.this);
                break;
            case R.id.forgotPass:
                password.setVisibility(View.INVISIBLE);
                register.setVisibility(View.INVISIBLE);
                garis.setVisibility(View.INVISIBLE);
                forgotPass.setVisibility(View.INVISIBLE);
                showPass.setVisibility(View.INVISIBLE);
                signIn.setVisibility(View.GONE);
                logBtn.setVisibility(View.GONE);
                send.setVisibility(View.VISIBLE);
                forgot.setVisibility(View.VISIBLE);
                break;
            case R.id.sendToEmail:
                String emailString = email.getText().toString().trim();
                if(TextUtils.isEmpty(emailString)) {
                    email.setError("Email is required");
                    email.requestFocus();
//                    return;
                } else {
                    RestClient.getService().forgotPass(emailString).enqueue(new Callback<ResponseForgetPass>() {
                        @Override
                        public void onResponse(Call<ResponseForgetPass> call, Response<ResponseForgetPass> response) {
                            if(response.isSuccessful() && response.body() !=null){
                                Toast.makeText(EmailLoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                Intent intent3 = new Intent(EmailLoginActivity.this, EmailLoginActivity.class);
                                startActivity(intent3);
                                Animatoo.animateZoom(EmailLoginActivity.this);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseForgetPass> call, Throwable t) {
                            Log.d("asd","fail");
                        }
                    });
                }
        }
    }
}
