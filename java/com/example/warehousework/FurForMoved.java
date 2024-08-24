package com.example.warehousework;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class FurForMoved extends AppCompatActivity {
    private TextView tvName, tvLength, tvWidth, tvHight, tvCity, tvFrom, tvAdress, tvData,
            tvCommit, tvEo, tvId, tvWhere, tvStat;
    private ImageView tvPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fur_for_moved);
        init();
        getIntentMain();
    }
    // функция инициализации
    private void init(){
        tvName = findViewById(R.id.tvNameM);
        tvLength = findViewById(R.id.tvLengthM);
        tvWidth = findViewById(R.id.tvWeightM);
        tvHight = findViewById(R.id.tvHightM);
        tvCity = findViewById(R.id.tvCityM);
        tvFrom = findViewById(R.id.tvFromM);
        tvAdress = findViewById(R.id.tvAdressM);
        tvData = findViewById(R.id.tvDataM);
        tvCommit = findViewById(R.id.tvCommitM);
        tvPhoto = findViewById(R.id.tvPhotoM);
        tvEo = findViewById(R.id.tvEoM);
        tvId = findViewById(R.id.tvIdM);
        tvWhere = findViewById(R.id.tvWhereM);
        tvStat = findViewById(R.id.tvStatM);
    }
//функция получения информации с предыдущего экрана
    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {
            Picasso.get().load(i.getStringExtra(Constant.FUR_PHOTO)).into(tvPhoto);
            tvId.setText(i.getStringExtra("fur_id"));
            tvName.setText(i.getStringExtra("fur_name"));
            tvLength.setText(i.getStringExtra("fur_length"));
            tvWidth.setText(i.getStringExtra("fur_width"));
            tvHight.setText(i.getStringExtra("fur_height"));
            tvCity.setText(i.getStringExtra("fur_city"));
            tvFrom.setText(i.getStringExtra("fur_number"));
            tvAdress.setText(i.getStringExtra("fur_adress"));
            tvData.setText(i.getStringExtra("fur_date"));
            tvCommit.setText(i.getStringExtra("fur_commit"));
            tvEo.setText(i.getStringExtra("fur_eo"));
            tvWhere.setText(i.getStringExtra("fur_where"));
            tvStat.setText(i.getStringExtra("fur_stat"));
        }
    }
    //функция перехода на предыдущий экран
    public void toGoBack (View v) {
        Intent b = new Intent(this, ReportMoved.class);
        startActivity(b);
    }
}