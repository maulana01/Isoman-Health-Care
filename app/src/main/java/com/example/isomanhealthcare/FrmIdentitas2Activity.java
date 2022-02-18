package com.example.isomanhealthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class FrmIdentitas2Activity extends AppCompatActivity {
    Button sendUpdateIdentity;
    TextView tglMulaiIsolasi, tvTglIsolasiSqlFormat;
    Spinner spinnerGolDarah, spinnerStatusVaksin;
    EditText etBeratBadan;
    String mulaiIsolasi, golDarah, beratBadan, statusVaksin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_frm_identitas2);

//        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        sendUpdateIdentity = findViewById(R.id.btnUpdateIdentitas);
        tglMulaiIsolasi = (TextView) findViewById(R.id.tglMulaiIsolasi);
        tvTglIsolasiSqlFormat = (TextView) findViewById(R.id.tvTglIsolasiSqlFormat);
        etBeratBadan = (EditText) findViewById(R.id.etBeratBadan);
        spinnerGolDarah = (Spinner) findViewById(R.id.spinnerGolDarah);
        spinnerStatusVaksin = (Spinner) findViewById(R.id.spinnerStatusVaksin);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tglMulaiIsolasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FrmIdentitas2Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = day+" "+getMonthFormat(month)+" "+year;
                                String dateSql = year+"-"+month+"-"+day;
                                tglMulaiIsolasi.setText(date);
                                tvTglIsolasiSqlFormat.setText(dateSql);
                            }
                        }, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        ArrayAdapter<String> golongan_darahAdapter = new ArrayAdapter<String>(FrmIdentitas2Activity.this,
                R.layout.spinner_item, getResources().getStringArray(R.array.golongan_darah));
        golongan_darahAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGolDarah.setAdapter(golongan_darahAdapter);

        spinnerGolDarah.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerGolDarahValue = (String) spinnerGolDarah.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> status_vaksinAdapter = new ArrayAdapter<String>(FrmIdentitas2Activity.this,
                R.layout.spinner_item, getResources().getStringArray(R.array.status_vaksin));
        status_vaksinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusVaksin.setAdapter(status_vaksinAdapter);

        spinnerStatusVaksin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerStatusVaksinValue = (String) spinnerStatusVaksin.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "Januari";
        if(month == 2)
            return "Februari";
        if(month == 3)
            return "Maret";
        if(month == 4)
            return "April";
        if(month == 5)
            return "Mei";
        if(month == 6)
            return "Juni";
        if(month == 7)
            return "Juli";
        if(month == 8)
            return "Agustus";
        if(month == 9)
            return "September";
        if(month == 10)
            return "Oktober";
        if(month == 11)
            return "November";
        if(month == 12)
            return "Desember";

        return "Januari";
    }

    public void updateIdentity(View view) {checkIdentity();}

    private void checkIdentity() {
        mulaiIsolasi = tvTglIsolasiSqlFormat.getText().toString();
        golDarah = spinnerGolDarah.getSelectedItem().toString();
        beratBadan = etBeratBadan.getText().toString();
        statusVaksin = spinnerStatusVaksin.getSelectedItem().toString();

        if (mulaiIsolasi.isEmpty() || golDarah.isEmpty() || beratBadan.isEmpty() || statusVaksin.isEmpty()) {
            alertFail("All fields is required.");
        }
        else {
            sendIdentity();
        }
    }

    private void sendIdentity() {

        JSONObject params = new JSONObject();
        Intent intent = getIntent();
        String nameS = intent.getStringExtra("name");
        String jkS = intent.getStringExtra("jenis_kelamin");
        String tglLahirS = intent.getStringExtra("tgl_lahir");
        String alamatS = intent.getStringExtra("alamat");
        try {
            params.put("name", nameS);
            params.put("jenis_kelamin", jkS);
            params.put("tgl_lahir", tglLahirS);
            params.put("alamat", alamatS);
            params.put("mulai_isolasi", mulaiIsolasi);
            params.put("golongan_darah", golDarah);
            params.put("berat_badan", beratBadan);
            params.put("status_vaksin", statusVaksin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data = params.toString();
        String url = getString(R.string.api_server)+"/update";

        new Thread(new Runnable() {
            @Override
            public void run() {
//                getUser();
                Http http = new Http(FrmIdentitas2Activity.this, url);
                http.setMethod("post");
                http.setToken(true);
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 201 || code == 200) {
                            alertSuccess("Pengisian Identitas Berhasil");
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
                            Toast.makeText(FrmIdentitas2Activity.this, "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void alertSuccess(String s) {
        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle("Success")
                .setMessage(s)
                .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FrmIdentitas2Activity.this, FinishFrmIdentityActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                }).show();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this, R.style.AlertDialogTheme)
                .setTitle("Failed")
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}