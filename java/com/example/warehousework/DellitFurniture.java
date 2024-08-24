package com.example.warehousework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
// код страницы по вкладке "Выдато мебель"
//отражает всю мебель, которая имеется на складе в статусе Целое

public class DellitFurniture extends AppCompatActivity  {
    private ListView listView;  // чтобы найти лист отражения данных
    private ArrayAdapter <String> adapter;
    private List<String> listData; //для передачи в adapter
    private List<Furniture> listTemp;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    private EditText searchEO;
    private Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dellit_furniture);
        init();
        getDataFromDB();
        setonClickItem();
    }
// функция инициализации
    private void init () {
        listView = findViewById(R.id.listView1);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
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
                   if (furniture.status.equals("Целое"))
                   {
                       listData.add(furniture.name);
                       listTemp.add(furniture);
                   }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        mDataBase.addValueEventListener(vListener);
    }
// при нажатии на позицию из списка данные по позиции передаются на экран Full_information.class
    private void setonClickItem() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Furniture furniture = listTemp.get(position);
                // передача на экран полной информации
                Intent i = new Intent(DellitFurniture.this, Full_information.class);
                i.putExtra("fur_name", furniture.name);
                i.putExtra("fur_length", furniture.length);
                i.putExtra("fur_width", furniture.width);
                i.putExtra("fur_height", furniture.height);
                i.putExtra("fur_city", furniture.city);
                i.putExtra("fur_number", furniture.numberV);
                i.putExtra("fur_adress", furniture.adressV);
                i.putExtra("fur_date", furniture.date);
                i.putExtra("fur_commit", furniture.commit);
                i.putExtra("fur_photo", furniture.photo_id);
                i.putExtra("fur_status", furniture.status);
                i.putExtra("fur_eo", furniture.eo);
                i.putExtra("fur_id", furniture.id);
                i.putExtra("fur_dataOut", furniture.dataOut);
                startActivity(i);
            }
        });
    }
    public void toGoSklad1(View sk) {
        Intent skl = new Intent(this, List_Meny1.class);
        startActivity(skl);
    }

    public void toGoReport1(View re) {
        Intent rep = new Intent(this, List_Meny2.class);
        startActivity(rep);
    }
}


