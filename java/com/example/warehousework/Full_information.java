package com.example.warehousework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
// этот экран появляется, при нажатии на строку с позицией мебели. Выдаётся полная информация о позиции
// также на этом экране можно произвести выдачу мебели (переход по кнопкам переместить, отдать, продать)


public class Full_information extends AppCompatActivity {
    //объявляем поля, которые имеются на странице (текстомые и фото мебели)
    private TextView tvName, tvLength, tvWidth, tvHight, tvCity, tvFrom, tvAdress, tvData,
            tvCommit, tvEo, tvId, tvWhere, tvStat;
    private ImageView tvPhoto;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_information);
        init();
        getIntentMain();
    }
// функция инициализации. Связываем переменную и поле (место) на экране телефона
    private void init(){
        tvName = findViewById(R.id.tvName);
        tvLength = findViewById(R.id.tvLength);
        tvWidth = findViewById(R.id.tvWeight);
        tvHight = findViewById(R.id.tvHight);
        tvCity = findViewById(R.id.tvCity);
        tvFrom = findViewById(R.id.tvFrom);
        tvAdress = findViewById(R.id.tvAdress);
        tvData = findViewById(R.id.tvData);
        tvCommit = findViewById(R.id.tvCommit);
        tvPhoto = findViewById(R.id.tvPhoto);
        tvEo = findViewById(R.id.tvEo);
        tvId = findViewById(R.id.tvId);
        tvWhere = findViewById(R.id.tvWhere);
        tvStat = findViewById(R.id.tvStat);
    }
// получение информации с предыдущего экрана
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
// переход по кнопке "Отдать"
    public void toGoGiven (View v) {
        String nameG = tvName.getText().toString();
        String eoG = tvEo.getText().toString();
        String idG = tvId.getText().toString();
        Intent iG = new Intent(this,Given.class);
        iG.putExtra("nameG", nameG);
        iG.putExtra("idG", idG);
        iG.putExtra("eoG", eoG);
        startActivity(iG);
    }
    //переход по кнопке "переместить"
    public void toGoMoved (View v) {
        String namef = tvName.getText().toString();
        String eof = tvEo.getText().toString();
        String idf = tvId.getText().toString();
        String wheref = tvWhere.getText().toString();
        String statf = tvStat.getText().toString();
        Intent i2 = new Intent(this, Moved.class); // передача на экран перемещения
        i2.putExtra("namef", namef);
        i2.putExtra("idf", idf);
        i2.putExtra("eof", eof);
        i2.putExtra("wheref", wheref);
        i2.putExtra("statf", statf);
        startActivity(i2);
    }
    // переход по кнопке "Продать"
    public void toGoSale (View v) {
        String nameS = tvName.getText().toString();
        String eoS = tvEo.getText().toString();
        String idS = tvId.getText().toString();
        Intent iS = new Intent(this, Sale.class);
        iS.putExtra("nameS", nameS);
        iS.putExtra("idS", idS);
        iS.putExtra("eoS", eoS);
        startActivity(iS);
    }
// переход по кнопке "Назад"
    public void toGoBack(View v) {
        Intent bask= new Intent(this, DellitFurniture.class);
        startActivity(bask);
    }
}

