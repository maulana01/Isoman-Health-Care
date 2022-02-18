package com.example.isomanhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {
    Button mulaiIsiFrmIdentitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        mulaiIsiFrmIdentitas = findViewById(R.id.btnMulaiIsiIdentitas);
    }

    public void mulaiIsiFrmIdentitas(View view) {
        startActivity(new Intent(this, FrmIdentitas1Activity.class));
//        finish();
    }
}