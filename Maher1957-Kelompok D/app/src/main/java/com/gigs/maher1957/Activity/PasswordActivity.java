package com.gigs.maher1957.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Models.Order.Order;
import com.gigs.maher1957.Models.Password.ResponseChangePassword;
import com.gigs.maher1957.Models.Profile.ResponseDetailsProfile;
import com.gigs.maher1957.Models.Profile.ResponsePostProfile;
import com.gigs.maher1957.Models.Profile.Success;
import com.gigs.maher1957.Models.Profile.UserProfile;
import com.gigs.maher1957.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnBack;
    private Button btnSavePass;
    private TinyDB tinyDB;
    private EditText oldPassword, newPassword, newPassword2;
    private CheckBox showPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        init();
    }

    private void init() {
        tinyDB = new TinyDB(PasswordActivity.this);
        String token = tinyDB.getString("token");

        oldPassword = (EditText) findViewById(R.id.passwordOld);
        newPassword = (EditText) findViewById(R.id.passwordNew);
        newPassword2 = (EditText) findViewById(R.id.passwordNew2);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnSavePass = (Button) findViewById(R.id.btnSavePass);
        showPass = (CheckBox) findViewById(R.id.checkBox);

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showPass.isChecked()){
                    oldPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    oldPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnBack.setOnClickListener(this);
        btnSavePass.setOnClickListener(this);

        oldPassword.setError(null);
        newPassword.setError(null);
        newPassword2.setError(null);

        String old_Password = oldPassword.getText().toString().trim();
        String new_Password = newPassword.getText().toString().trim();
        String new_Password2 = newPassword2.getText().toString().trim();

        if(TextUtils.isEmpty(old_Password)) {
            oldPassword.setError("Old Password required");
            oldPassword.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(new_Password))
        {
            newPassword.setError("New Password required");
            newPassword.requestFocus();
            return;
        }
        else if(newPassword.length() <6 )
        {
            newPassword.setError("Password Should be atleast 6 character long");
            newPassword.requestFocus();
            return;
        }
        if(!new_Password2.equals(new_Password))
        {
            newPassword2.setError("Passwords must be the same with new password");
            newPassword2.requestFocus();
        }
         else{
            RestClient.getService().changePassword("Bearer "+ token,old_Password,new_Password,new_Password2).enqueue(new Callback<ResponseChangePassword>() {
                @Override
                public void onResponse(Call<ResponseChangePassword> call, Response<ResponseChangePassword> response) {
                    if(response.isSuccessful() && response.body() != null){
                        String r = response.body().getMessage();
                        Toast.makeText(PasswordActivity.this, ""+r, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseChangePassword> call, Throwable t) {
                    Log.d("asd","fail");
                }
            });
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSavePass:
                init();
                break;

            case R.id.btnBack:
                Intent intent = new Intent(PasswordActivity.this, HomePageActivity.class);
                startActivity(intent);
                Animatoo.animateCard(PasswordActivity.this);
                break;
        }
    }
}
