package com.example.warehousework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

// при переходе на Склад - Выдать мебель - на какую-либо строку, то выдаст полную информацию по строке
public class FullInfaForAdd extends AppCompatActivity implements View.OnClickListener {
    private TextView tvName, tvLength, tvWidth, tvHight, tvCity, tvFrom, tvAdress, tvData, tvCommit, tvId, tvStatus;
    private EditText inputEo, inputInvent;
    private ImageView tvPhoto;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    private String id;
    Button addEo;
    private Uri filePath; //указывает, откуда будет выбрано изображение
    static final int GALLERY_REQUEST = 1;
    private StorageReference mStorageRef;  // для подключения Storage. Здесь хранятся сами фотографии
    private Uri uploadUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_infa_for_add);
        init();
        getIntentMain();
    }

    // функция инициализации
    private void init() {  // связываю переменную с полем на экране
        tvId = findViewById(R.id.id);
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
        inputEo = findViewById(R.id.inputEo);
        mDataBase = FirebaseDatabase.getInstance().getReference(Furniture_KEY);
        tvStatus = findViewById(R.id.tvStatus);
        inputInvent = findViewById(R.id.input_invent);
        mStorageRef = FirebaseStorage.getInstance().getReference("photoF");
    }

    private void getIntentMain() {
        Intent ad = getIntent();
        if (ad != null) {
            Picasso.get().load(ad.getStringExtra(Constant.FUR_PHOTO)).into(tvPhoto);
            tvId.setText(ad.getStringExtra("fur_id"));
            tvName.setText(ad.getStringExtra("fur_name"));
            tvLength.setText(ad.getStringExtra("fur_length"));
            tvWidth.setText(ad.getStringExtra("fur_width"));
            tvHight.setText(ad.getStringExtra("fur_height"));
            tvCity.setText(ad.getStringExtra("fur_city"));
            tvFrom.setText(ad.getStringExtra("fur_number"));
            tvAdress.setText(ad.getStringExtra("fur_adress"));
            tvData.setText(ad.getStringExtra("fur_date"));
            tvCommit.setText(ad.getStringExtra("fur_commit"));
        }
    }

    // получаю из текстовых полей на экране значения и записываю их в БД
    @Override
    public void onClick(View v) {
        String idfur = tvId.getText().toString();
        String eofur = inputEo.getText().toString();
        String inventfur = inputInvent.getText().toString();

        if (!TextUtils.isEmpty(eofur) && !TextUtils.isEmpty(inventfur)) // проверка полей на пустоту
        {
            mDataBase.child(idfur).child("eo").setValue(eofur);
            mDataBase.child(idfur).child("invent").setValue(inventfur);
            Toast.makeText(this, "Данные внесены!", Toast.LENGTH_SHORT).show();
            // после ввода данных окно закрывается, чтоб случайно не нажать повторно
            Intent in = new Intent(this, AddEO.class);  // this - иду с текущего экрана, далее куда иду
            startActivity(in);
        }
        else
        {
            Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
        }
    }

    public void toGoBack2(View view) {
        Intent bask= new Intent(this, AddEO.class);
        startActivity(bask);
    }
}
