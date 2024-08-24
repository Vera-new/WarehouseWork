package com.example.warehousework;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Given extends AppCompatActivity implements View.OnClickListener{
    private TextView nameG, eoG, idG;
    private EditText organizationGiven, dateGiven;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    private DatabaseReference mDataBaseG;
    private String OrganizationG_KEY = "Organization_Given";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_given);
        init();
        getIntentMain();
    }
    // связываю переменную с полем на экране
    // функция инициализации
    private void init(){
        nameG = findViewById(R.id.nameG);
        eoG = findViewById(R.id.eoG);
        idG = findViewById(R.id.idG);
        organizationGiven = findViewById(R.id.organizationGiven);
        dateGiven = findViewById(R.id.dateGiven);
        mDataBase = FirebaseDatabase.getInstance().getReference(Furniture_KEY);
        mDataBaseG = FirebaseDatabase.getInstance().getReference(OrganizationG_KEY);
    }
    // получаю из текстовых полей на экране значения и записываю их в БД
    private void getIntentMain() {
        Intent iS = getIntent();
        if (iS != null) {
            nameG.setText(iS.getStringExtra("nameG"));
            eoG.setText(iS.getStringExtra("eoG"));
            idG.setText(iS.getStringExtra("idG"));
        }
    }

    @Override
    public void onClick(View v) {
        // считываем днные введёные пользователем в поля на экране
        String organizationG = organizationGiven.getText().toString();

        String dateG = dateGiven.getText().toString();
        String status = "отдано";
        String idf = idG.getText().toString();
        if (!TextUtils.isEmpty(organizationG) && !TextUtils.isEmpty(dateG)) // проверка полей на пустоту
        {
            mDataBase.child(idf).child("where").setValue(organizationG);
            mDataBase.child(idf).child("status").setValue(status);
            mDataBase.child(idf).child("dataOut").setValue(dateG);
            mDataBaseG.child(mDataBaseG.push().getKey()).child("org").setValue(organizationG);

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

    public void toGoBackFromGiven(View v4) {
        Intent goBackFromGiven = new Intent(this, Full_information.class);
        startActivity(goBackFromGiven);
    }
}


