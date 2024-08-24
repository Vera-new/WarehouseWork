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

public class Sale extends AppCompatActivity implements View.OnClickListener{

    private TextView nameS, eoS, idS;
    private EditText organizationSale, dateSale;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    private String id;
    Button sale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        init();
        getIntentMain();
    }
    // связываю переменную с полем на экране
    // функция инициализации
    private void init(){
        nameS = findViewById(R.id.nameS);
        eoS = findViewById(R.id.eoS);
        idS = findViewById(R.id.idS);
        organizationSale = findViewById(R.id.organizationSale);
        dateSale = findViewById(R.id.dateSale);
        mDataBase = FirebaseDatabase.getInstance().getReference(Furniture_KEY);
    }
    // получаю из текстовых полей на экране значения и записываю их в БД
    private void getIntentMain() {
        Intent iS = getIntent();
        if (iS != null) {
            nameS.setText(iS.getStringExtra("nameS"));
            eoS.setText(iS.getStringExtra("eoS"));
            idS.setText(iS.getStringExtra("idS"));
        }
    }
    @Override
    public void onClick(View v) {
        // считываем днные введёные пользователем в поля на экране
        String organizationS = organizationSale.getText().toString();
        String dateS = dateSale.getText().toString();
        String status = "продано";
        String idf = idS.getText().toString();

        if (!TextUtils.isEmpty(organizationS) && !TextUtils.isEmpty(dateS )) // проверка полей на пустоту
        {
            mDataBase.child(idf).child("where").setValue(organizationS);
            mDataBase.child(idf).child("status").setValue(status);
            mDataBase.child(idf).child("dataOut").setValue(dateS);

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
    public void toGoBackFromSale(View v4) {
        Intent goBackFromSale = new Intent(this, Full_information.class);
        startActivity(goBackFromSale);
    }
}
