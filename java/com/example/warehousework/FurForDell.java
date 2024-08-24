package com.example.warehousework;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FurForDell extends AppCompatActivity {
    private TextView tvHightD, tvNameD, tvLengthD, tvWeightD, tvCityD,tvAdressD, tvDataD, tvCommitD,
            tvEoD,tvIdD, tvFromD;
    private ImageView tvPhotoD;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fur_for_dell);
        init();
        getIntentMain();
    }
    //функция инициализации
    private void init(){
        tvNameD = findViewById(R.id.tvNameD);
        tvLengthD = findViewById(R.id.tvLengthD);
        tvWeightD = findViewById(R.id.tvWeightD);
        tvHightD = findViewById(R.id.tvHightD);
        tvCityD = findViewById(R.id.tvCityD);
        tvFromD = findViewById(R.id.tvFromD);
        tvAdressD = findViewById(R.id.tvAdressD);
        tvDataD = findViewById(R.id.tvDataD);
        tvCommitD = findViewById(R.id.tvCommitD);
        tvPhotoD = findViewById(R.id.tvPhotoD);
        tvEoD = findViewById(R.id.tvEoD);
        tvIdD = findViewById(R.id.tvIdD);
        mDataBase = FirebaseDatabase.getInstance().getReference(Furniture_KEY);
    }
    //функция получения информации с предыдущего листа
    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            Picasso.get().load(i.getStringExtra(Constant.FUR_PHOTO)).into(tvPhotoD);
            tvIdD.setText(i.getStringExtra("fur_idD"));
            tvNameD.setText(i.getStringExtra("fur_name"));
            tvLengthD.setText(i.getStringExtra("fur_length"));
            tvWeightD.setText(i.getStringExtra("fur_width"));
            tvHightD.setText(i.getStringExtra("fur_height"));
            tvCityD.setText(i.getStringExtra("fur_city"));
            tvFromD.setText(i.getStringExtra("fur_number"));
            tvAdressD.setText(i.getStringExtra("fur_adress"));
            tvDataD.setText(i.getStringExtra("fur_date"));
            tvCommitD.setText(i.getStringExtra("fur_commit"));
            tvEoD.setText(i.getStringExtra("fur_eoD"));
        }
    }
    //функция смена статуса в базе данных
    public void toGoDell (View v) {
        String idf = tvIdD.getText().toString();
        String namef = tvNameD.getText().toString();
        String eof = tvEoD.getText().toString();
        String status = "утиль";
        mDataBase.child(idf).child("status").setValue(status);
        Intent iback = new Intent(this, ReportForDell.class);
        Toast.makeText(this, namef+" c EO " + eof + " утилизирована!", Toast.LENGTH_SHORT).show();
        startActivity(iback);
    }
// функция перехода на предыдущие экран
    public void toGoReportFromFurForDelll (View v) {
        Intent goReportFromFurForDelll = new Intent(this, ReportForDell.class);
        startActivity(goReportFromFurForDelll);
    }
}
