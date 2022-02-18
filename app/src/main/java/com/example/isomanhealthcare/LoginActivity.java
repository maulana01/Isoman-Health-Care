package com.example.isomanhealthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity{
    EditText etEmail, etPassword;
    Button login;
    String email, password;
    LocalStorage localStorage;
    SharedPreferences sharedPreferences;
    Boolean session, nameWelcome;
    Context context;

    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    public static final String identity_shared_preferences = "identity_shared_preferences";
    public static final String identity_status = "identity_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        localStorage = new LocalStorage(LoginActivity.this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);

        if (session) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            finish();
            startActivity(intent);
        }

        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);
        tvRegister.setText(Html.fromHtml("Belum punya akun? "+"<u><b>Daftar disini</b></u>"));

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    public void loginBtn(View view) { checkLogin(); }

    private void checkLogin() {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            alertFail("Email and Password is required");
        }
        else {
            sendLogin();
        }
    }

    private void sendLogin() {
        JSONObject params = new JSONObject();
        try {
            params.put("email", email);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data = params.toString();
        String url = getString(R.string.api_server)+"/login";

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(LoginActivity.this, url);
                http.setMethod("post");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            try {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean(session_status, true);
                                editor.commit();

                                JSONObject response = new JSONObject(http.getResponse());
                                String token = response.getString("token");
                                localStorage.setToken(token);

//                                if (name != "") {
//                                    SharedPreferences.Editor editor2 = identitySharedPreferences.edit();
//                                    editor2.putBoolean(identity_status, true);
//                                    editor2.commit();
//                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    SharedPreferences.Editor editor2 = identitySharedPreferences.edit();
//                                    editor2.putBoolean(identity_status, false);
//                                    editor2.commit();
//                                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }

                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        SharedPreferences fillIdentityFrm = getSharedPreferences("fillIdentityFrm", MODE_PRIVATE);
                                        boolean isFirstTime = fillIdentityFrm.getBoolean("firstTime", true);

                                        if(isFirstTime) {
                                            SharedPreferences.Editor editor = fillIdentityFrm.edit();
                                            editor.putBoolean("firstTime", false);
                                            editor.commit();
                                            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                                            finish();
                                        } else {
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            finish();
                                        }
                                    }
                                }, 2000L);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (code == 422) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail(msg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (code == 401) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("message");
                                alertFail(msg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle("Failed")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { dialog.dismiss(); }
                }).show();
    }
}
