package com.example.isomanhealthcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button logout;
    FloatingActionButton mulaiBot;
    SharedPreferences sharedPreferences;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.btnLogout);
        mulaiBot = (FloatingActionButton) findViewById(R.id.fab);

        sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frameLayoutId);
        navView.setItemIconTintList(null);

        setFragment(new PanduanFragment());

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.panduan:
                        setFragment(new PanduanFragment());
                        return true;

                    case R.id.akun:
                        setFragment(new AkunFragment());
                        return true;

                    default:
                        return false;
                }
            }
        });

        mulaiBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatbotActivity.class);
                startActivity(intent);
            }
        });


    }

    public void logoutBtn(View view) { logout(); }

    private void getUser() {
        String url = getString(R.string.api_server)+"/user";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(MainActivity.this, url);
                http.setToken(true);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String email = response.getString("email");
                                String created_at = response.getString("created_at");
//                                tvEmail.setText(email);
//                                tvCreated.setText(created_at);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void logout() {
        String url = getString(R.string.api_server)+"/logout";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(MainActivity.this, url);
                http.setMethod("post");
                http.setToken(true);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(LoginActivity.session_status, false);
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayoutId, fragment);
        fragmentTransaction.commit();
    }
}