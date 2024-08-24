package com.example.warehousework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
//Содержит меню отчётов (информация, какая мебель куда ушла и информацию, какая мебель подлежит утилизации)
//Содержит код перехода по кнопке "Для утилизации" (toGoReportForDell)
//Содержит код перехода по кнопке "Отдано на благотворительность" (toGoReportGivenAway)
//Содержит код перехода по кнопке "Продано" (toGoReportSales)
//Содержит код перехода по кнопке "Перемещено" (toGoReportMoved)
//Содержит код перехода по кнопке "На главный экран" (toGoMainActivity)

public class List_Meny2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meny2);
    }

// обработчик события нажатия на кнопку "Для утилизации". Переход по кнопке
    public void toGoReportForDell(View v5) {
        Intent goReportForDell = new Intent(this, ReportForDell.class);
        startActivity(goReportForDell);
    }

//обработчик события нажатия на кнопку "Отдано на благотворительность". Переход по кнопке
    public void toGoReportGivenAway(View v6) {
        Intent goReportGivenAway= new Intent(this, ReportGivenAvay.class);
        startActivity(goReportGivenAway);
    }

// обработчик события нажатия на кнопку "Продано". Переход по кнопке
    public void toGoReportSales(View v7) {
        Intent goReportSales = new Intent(this, ReportSales.class);
        startActivity(goReportSales);
    }

// обработчик события нажатия на кнопку "Перемещено". Переход по кнопке
    public void toGoReportMoved(View v8) {
        Intent goReportMoved = new Intent(this, ReportMoved.class);
        startActivity(goReportMoved );
    }

 // обработчик события нажатия на кнопку "На главный экран". Переход по кнопке
    public void toGoMainActivityFromList2(View v5) {
        Intent goMainActivityFromList2 = new Intent(this, MainActivity.class);
        startActivity(goMainActivityFromList2);
    }
}