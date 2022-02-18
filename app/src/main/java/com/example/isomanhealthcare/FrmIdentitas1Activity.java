package com.example.isomanhealthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.util.Calendar;

public class FrmIdentitas1Activity extends AppCompatActivity {
    Button frmIdentitas2;
    TextView tvTglLahirDatePicker, tvTglLahirSqlFormat;
    EditText etName, etAlamat;
    Spinner spinnerJK;
    String name, jk, tglLahir, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_frm_identitas1);

        frmIdentitas2 = findViewById(R.id.btnFrmIdentitas2);
        etName = findViewById(R.id.etName);
        spinnerJK = (Spinner) findViewById(R.id.spinnerJK);
        tvTglLahirDatePicker = (TextView) findViewById(R.id.tglLahirDatePicker);
        tvTglLahirSqlFormat = (TextView) findViewById(R.id.tvTglLahirSqlFormat);
        etAlamat = findViewById(R.id.etAlamat);

        ArrayAdapter<String> jenis_kelaminAdapter = new ArrayAdapter<String>(FrmIdentitas1Activity.this,
                R.layout.spinner_item, getResources().getStringArray(R.array.jenis_kelamin));
        jenis_kelaminAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJK.setAdapter(jenis_kelaminAdapter);

        spinnerJK.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerJkValue = (String) spinnerJK.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvTglLahirDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FrmIdentitas1Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month = month + 1;
                                String date = day+" "+getMonthFormat(month)+" "+year;
                                String dateSql = year+"-"+month+"-"+day;
                                tvTglLahirDatePicker.setText(date);
                                tvTglLahirSqlFormat.setText(dateSql);
                            }
                        }, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
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

    public void isiFrmIdentitas2(View view) {checkFrmIdentitas1();}

    private void checkFrmIdentitas1() {
        name = etName.getText().toString();
        jk = spinnerJK.getSelectedItem().toString();
        tglLahir = tvTglLahirSqlFormat.getText().toString();
        alamat = etAlamat.getText().toString();

        if (name.isEmpty() || jk.isEmpty() || tglLahir.isEmpty() || alamat.isEmpty()) {
            alertFail("All fields is required.");
        } else {
            Intent intent =new Intent(FrmIdentitas1Activity.this, FrmIdentitas2Activity.class);
            intent.putExtra("name", name);
            intent.putExtra("jenis_kelamin", jk);
            intent.putExtra("tgl_lahir", tglLahir);
            intent.putExtra("alamat", alamat);
            startActivity(intent);
        }
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