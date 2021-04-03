package com.gigs.maher1957.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.gigs.maher1957.API.RestClient;
import com.gigs.maher1957.API.TinyDB;
import com.gigs.maher1957.EmailLoginRegister.EmailLoginActivity;
import com.gigs.maher1957.Fragments.CartFragment;
import com.gigs.maher1957.Fragments.HomeFragment;
import com.gigs.maher1957.Fragments.NotificationFragment;
import com.gigs.maher1957.Fragments.UserFragment;
import com.gigs.maher1957.Models.CartBaru.Data;
import com.gigs.maher1957.Models.CartBaru.DataItemCart;
import com.gigs.maher1957.Models.CartBaru.ResponseGetCartBaru;
import com.gigs.maher1957.Models.Favorite.DataGetFav;
import com.gigs.maher1957.Models.Favorite.ResponseGetFav;
import com.gigs.maher1957.Models.Notifikasi.DataListOrder;
import com.gigs.maher1957.Models.Notifikasi.ResponseListOrder;
import com.gigs.maher1957.R;
import com.gigs.maher1957.databinding.ActivityHomePageBinding;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class HomePageActivity extends AppCompatActivity {

    ActivityHomePageBinding binding;
    BottomNavigationView bottomNavigation;
    BadgeDrawable badgeCart, badgeOrder;
    private String token;
    private TinyDB tinyDB;
    private Data dataCart;
    private DataListOrder dataListOrder;
    private boolean mKeyboardVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tinyDB = new TinyDB(HomePageActivity.this);
        token = tinyDB.getString("token");

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigation);
        bottomNavigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_AUTO);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();

        badgeCart = bottomNavigation.getOrCreateBadge(R.id.cart); // previously showBadge
//        badgeOrder = bottomNavigation.getOrCreateBadge(R.id.notification); // previously showBadge

        RestClient.getService().getCartBaru("Bearer " + token).enqueue(new Callback<ResponseGetCartBaru>() {
            @Override
            public void onResponse(Call<ResponseGetCartBaru> call, Response<ResponseGetCartBaru> response) {
                if(response.isSuccessful() && response.body() != null){
                    dataCart = response.body().getData();
                    setBadgeCart(dataCart.getCountCart());

                }
            }

            @Override
            public void onFailure(Call<ResponseGetCartBaru> call, Throwable t) {
                Log.d("asd","fail");
            }
        });

    }

    public void setBadgeCart(int countCart) {
        if (countCart == 0) {
            badgeCart.setVisible(false);
        } else {
            badgeCart.setVisible(true);
            badgeCart.setNumber(countCart);badgeCart.setNumber(countCart);
            badgeCart.setMaxCharacterCount(2);
            badgeCart.setBackgroundColor(getResources().getColor(R.color.merah));
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigation = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.cart:
                    if (token.equals("")){
                        badgeCart.setVisible(false);
                        Intent intent = new Intent(HomePageActivity.this, EmailLoginActivity.class);
                        startActivity(intent);
                        Animatoo.animateCard(HomePageActivity.this);
                        Toast.makeText(HomePageActivity.this, "Please to login", Toast.LENGTH_SHORT).show();
                        finish();
                    } else{
                        selectedFragment = new CartFragment();
                        break;
                    }

                case R.id.notification:
                    if (token.equals("")){
                        Intent intent = new Intent(HomePageActivity.this, EmailLoginActivity.class);
                        startActivity(intent);
                        Animatoo.animateCard(HomePageActivity.this);
                        Toast.makeText(HomePageActivity.this, "Please to login", Toast.LENGTH_SHORT).show();
                        finish();
                    } else{
                        selectedFragment = new NotificationFragment();
                        break;
                    }

                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.user:
                    if (token.equals("")){
                        Intent intent = new Intent(HomePageActivity.this, EmailLoginActivity.class);
                        startActivity(intent);
                        Animatoo.animateCard(HomePageActivity.this);
                        Toast.makeText(HomePageActivity.this, "Please to login", Toast.LENGTH_SHORT).show();
                        finish();
                    } else{
                        selectedFragment = new UserFragment();
                        break;
                    }
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFragment).commit();
            return true;
        }

    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
        binding = null;
    }

    private final ViewTreeObserver.OnGlobalLayoutListener mLayoutKeyboardVisibilityListener =
            () -> {
                final Rect rectangle = new Rect();
                final View contentView = getContentView();
                contentView.getWindowVisibleDisplayFrame(rectangle);
                int screenHeight = contentView.getRootView().getHeight();

                // r.bottom is the position above soft keypad or device button.
                // If keypad is shown, the rectangle.bottom is smaller than that before.
                int keypadHeight = screenHeight - rectangle.bottom;
                // 0.15 ratio is perhaps enough to determine keypad height.
                boolean isKeyboardNowVisible = keypadHeight > screenHeight * 0.15;

                if (mKeyboardVisible != isKeyboardNowVisible) {
                    if (isKeyboardNowVisible) {
                        onKeyboardShown();
                    } else {
                        onKeyboardHidden();
                    }
                }

                mKeyboardVisible = isKeyboardNowVisible;
            };

    private void onKeyboardShown() {
        bottomNavigation.setVisibility(View.INVISIBLE);
    }

    private void onKeyboardHidden() {
        bottomNavigation.setVisibility(View.VISIBLE);
    }
    private View getContentView() {
        return findViewById(Window.ID_ANDROID_CONTENT);
    }

}
