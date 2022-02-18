package com.example.isomanhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class FinishFrmIdentityActivity extends AppCompatActivity {
    Button mulaiObrolan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_finish_frm_identity);

        mulaiObrolan = findViewById(R.id.btnMulaiObrolan);
    }

    public void mulaiObrolan(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }
}