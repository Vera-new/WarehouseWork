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

public class FurForGiven extends AppCompatActivity {
    private TextView tvName, tvLength, tvWidth, tvHight, tvCity, tvFrom, tvAdress, tvData,
            tvCommit, tvEo, tvId, tvWhere, tvStat;
    private ImageView tvPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fur_for_given);
        init();
        getIntentMain();
    }
//функция инициализации
    private void init(){
        tvName = findViewById(R.id.tvNameG);
        tvLength = findViewById(R.id.tvLengthG);
        tvWidth = findViewById(R.id.tvWeightG);
        tvHight = findViewById(R.id.tvHightG);
        tvCity = findViewById(R.id.tvCityG);
        tvFrom = findViewById(R.id.tvFromG);
        tvAdress = findViewById(R.id.tvAdressG);
        tvData = findViewById(R.id.tvDataG);
        tvCommit = findViewById(R.id.tvCommitG);
        tvPhoto = findViewById(R.id.tvPhotoG);
        tvEo = findViewById(R.id.tvEoG);
        tvId = findViewById(R.id.tvIdG);
        tvWhere = findViewById(R.id.tvWhereG);
        tvStat = findViewById(R.id.tvStatG);
    }
//получение информации с предыдущего экрана
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
    // функция перехода на предыдущий экран
    public void toGoBack (View v) {
        Intent b = new Intent(this, ReportGivenAvay.class);
        startActivity(b);
    }
}