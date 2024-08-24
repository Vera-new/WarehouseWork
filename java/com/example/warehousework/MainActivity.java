package com.example.warehousework;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//Главная страница приложения
//Содержит код перехода по кнопке "Склад" (toGoMenySrlad)
// Нужно сделать переход по кнопке "Отчёт" (toGoMenyReport)

public class MainActivity extends AppCompatActivity {
    private Button exit;


// создание и отражение страницы
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
// обработчик события нажатия на кнопку "склад". Переход в меню склада
    public void toGoMenySclad (View v)
    {
        Intent menySclad = new Intent(this, List_Meny1.class);
        startActivity(menySclad);
    }
// обработчик события нажатия на кнопку "отчёт". Переход в меню отчётов
    public void toGoMenyReport (View v) {
        Intent menyReport = new Intent(this, List_Meny2.class);
        startActivity(menyReport);
    }

// обработчик события нажатия на кнопку "Выход". Выход из приложения
    public void toGoExit (View v) {
       exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() { // устанавливаем прослушиватель нажатия на кнопку
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });

    }
}
