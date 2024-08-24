package com.example.warehousework;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Moved extends AppCompatActivity implements View.OnClickListener{
    private TextView tvName, tvEo, tvId, where, stat;
    private EditText tvNumberV, tvAdress, tvData;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    private String id;
    Button move;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moved);
        init();
        getIntentMain();
    }
    // связываю переменную с полем на экране
    // функция инициализации
    private void init(){
        tvName = findViewById(R.id.name);
        tvEo = findViewById(R.id.eo);
        tvNumberV = findViewById(R.id.numberVSPMoved);
        tvAdress = findViewById(R.id.adressVSPMoved);
        tvData = findViewById(R.id.dateMoved);
        tvId = findViewById(R.id.id_furn);
        where = findViewById(R.id.where);
        stat = findViewById(R.id.stat);
        mDataBase = FirebaseDatabase.getInstance().getReference(Furniture_KEY);
    }

    // берём данные из БД и присваиваем их полям формы
    private void getIntentMain() {
        Intent i2 = getIntent();
        if (i2 != null) {
            tvName.setText(i2.getStringExtra("namef"));
            tvEo.setText(i2.getStringExtra("eof"));
            tvId.setText(i2.getStringExtra("idf"));
            where.setText(i2.getStringExtra("wheref"));
            stat.setText(i2.getStringExtra("statf"));
        }
    }

    // получаю из текстовых полей на экране значения и записываю их в БД
    @Override
    public void onClick(View v) {
        String numberfur = tvNumberV.getText().toString();
        String adressfur = tvAdress.getText().toString();
        String dateMovefur = tvData.getText().toString();
        String status = "перемещено";
        String idf = tvId.getText().toString();

        if (!TextUtils.isEmpty(numberfur) && !TextUtils.isEmpty(adressfur) && !TextUtils.isEmpty(dateMovefur)) // проверка полей на пустоту
        {
            mDataBase.child(idf).child("where").setValue(numberfur+ " / "+adressfur);
            mDataBase.child(idf).child("status").setValue(status);
            mDataBase.child(idf).child("dataOut").setValue(dateMovefur);

            Toast.makeText(this, "Данные внесены!", Toast.LENGTH_SHORT).show();
            // после ввода данных окно закрывается, чтоб случайно не нажать повторно
            Intent in = new Intent(this, DellitFurniture.class);  // this - иду с текущего экрана, далее куда иду
            startActivity(in);
        }
        else
        {
            Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
        }
    }

    public void toGoBackFromMoved(View v4) {
        Intent goBackFromMoved = new Intent(this, Full_information.class);
        startActivity(goBackFromMoved);
    }
}
