package com.example.warehousework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//Содержит меню склада (то, что может сделать кладовщик)
//Содержит код перехода по кнопке "Внести мебель" (toGoInputFurniture)
//Содержит код перехода по кнопке "Выдать мебель" (toGoDeliveryFurniture)
// Содержит код перехода по кнопке  "Добавить ЕО" (toGoAddEO)
// содержит код возврата на главный экран "На главный экран" (toGoMainActivityFromListMeny1)

public class List_Meny1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meny1);
    }
// обработчик события нажатия на кнопку "Внести мебель". Переход по кнопке
    public void toGoInputFurniture(View v) {
        Intent goInputFurniture = new Intent(this, InputFurniture.class);  // this - иду с текущего экрана, далее куда иду
        startActivity(goInputFurniture);
    }
// обработчик события нажатия на кнопку "Выдать мебель". Переход по кнопке
    public void toGoDellitFurniture(View v2) {
        Intent gGoDellitFurniture = new Intent(this, DellitFurniture.class);
        startActivity(gGoDellitFurniture);
    }
// обработчик события нажатия на кнопку "Добавить ЕО". Переход по кнопке
    public void toGoAddEO(View v3) {
        Intent goAddEO = new Intent(this, AddEO.class);
        startActivity(goAddEO);
    }
// обработчик события нажатия на кнопку "На главный экран". Возврат на главный экран
    public void toGoMainActivityFromListMeny1(View v4) {
        Intent goMainActivityFromListMeny1 = new Intent(this, MainActivity.class);
        startActivity(goMainActivityFromListMeny1);
    }
}