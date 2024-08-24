package com.example.warehousework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
// код страницы по вкладке "Присвоить ЕО и инвентарный"
//отражает всю мебель, которая имеется на складе не внесённая в программу
public class AddEO extends AppCompatActivity {
    private ListView listView1;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Furniture> listTemp;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_eo);
        init();
        getDataFromDB();
        setonClickItem();
    }
// функция инициализации
    private void init () {
        listView1 = findViewById(R.id.listView1);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView1.setAdapter(adapter);
        mDataBase = FirebaseDatabase.getInstance().getReference(Furniture_KEY);
    }
// функция получения данных из БД
    private void getDataFromDB () {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(listData.size() > 0)listData.clear();
                if(listTemp.size() > 0)listTemp.clear();
                for (DataSnapshot ds: snapshot.getChildren()) {
                    Furniture furniture = ds.getValue(Furniture.class);
                    assert furniture != null;
                    if (furniture.eo.equals("null")) {    //
                        listData.add(furniture.name);
                        listTemp.add(furniture);}
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        mDataBase.addValueEventListener(vListener);
    }
// функция передачи информации (по выбранной мебели) на следующий экран
    private void setonClickItem() {
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Furniture furniture = listTemp.get(position);
                // передача на экран полной информации
                Intent ad = new Intent(AddEO.this, FullInfaForAdd.class);
                ad.putExtra("fur_name", furniture.name);
                ad.putExtra("fur_id", furniture.id);
                ad.putExtra("fur_length", furniture.length);
                ad.putExtra("fur_width", furniture.width);
                ad.putExtra("fur_height", furniture.height);
                ad.putExtra("fur_city", furniture.city);
                ad.putExtra("fur_number", furniture.numberV);
                ad.putExtra("fur_adress", furniture.adressV);
                ad.putExtra("fur_date", furniture.date);
                ad.putExtra("fur_commit", furniture.commit);
                ad.putExtra("fur_photo", furniture.photo_id);
                ad.putExtra("fur_status", furniture.status);
                startActivity(ad);
            }
        });
    }
    public void toGoBackFromAddEO(View v) {
        Intent b= new Intent(this, List_Meny1.class);
        startActivity(b);
    }
}

