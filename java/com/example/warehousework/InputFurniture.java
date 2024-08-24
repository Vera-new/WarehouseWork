package com.example.warehousework;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
// один из главных функционалов кладовщика - внесение мебели на склад
// Содержит поля, которые необходимо заполнить
// Загружает фото в базу данных
// Даёт возможность сразу отсортировать мебель (целое или утилизация)
// после внесения данных окно закрывается (переходила на другой экран)

public class InputFurniture extends AppCompatActivity implements View.OnClickListener
{
// создаю переменные для обращения к полям на странице
    Button inputDate, inputPhoto;
    private EditText namefurniture, lengthFurniture, widthFurniture, heightFurniture, numberVSP;
    private EditText adressVSP, date, commit;
    private ImageView photoF;
    private DatabaseReference mDataBase;
    private String Furniture_KEY = "Furniture";
    private Uri filePath; //указывает, откуда будет выбрано изображение
    static final int GALLERY_REQUEST = 1;
    private String [] statusFur = {"Целое", "Утилизировать"};
    private Spinner statusFurniture;
    private Spinner cityFurniture;

   private StorageReference mStorageRef;  // для подключения Storage. Здесь хранятся сами фотографии
   private Uri uploadUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_furniture);
        init();
    }
// функция инициализации (связываю созданные переменными с полями на экране телефона)
    private void init () {
        mStorageRef = FirebaseStorage.getInstance().getReference("photoF");
        namefurniture = findViewById(R.id.namefurniture);
        lengthFurniture = findViewById(R.id.lengthFurniture);
        widthFurniture = findViewById(R.id.widthFurniture);
        heightFurniture = findViewById(R.id.heightFurniture);
        numberVSP = findViewById(R.id.numberVSP);
        adressVSP = findViewById(R.id.adressVSP);
        date = findViewById(R.id.date);
        commit = findViewById(R.id.commit);
        mDataBase = FirebaseDatabase.getInstance().getReference(Furniture_KEY);
        inputPhoto = findViewById(R.id.inputPhoto);
        photoF = findViewById(R.id.photoF);
        statusFurniture = findViewById(R.id.status);
        cityFurniture = findViewById(R.id.city);
        inputDate = findViewById(R.id.inputDate);

        //работа со выпадающим списком (для выбора "целое" или "утилизировать")
        ArrayAdapter<String>statusFurAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,statusFur);
        statusFurAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusFurniture.setAdapter(statusFurAdapter);
    }
// функция сохранения данных в базу данных
    private void saveFurniture ()
    {
        String idf = mDataBase.push().getKey();
        String namefur = namefurniture.getText().toString();
        String lengthf = lengthFurniture.getText().toString();
        String widthf = widthFurniture.getText().toString();
        String heightf = heightFurniture.getText().toString();
        String numberV = numberVSP.getText().toString();
        String adressV = adressVSP.getText().toString();
        String dateIn = date.getText().toString();
        String commitf = commit.getText().toString();
        String statusf = statusFurniture.getSelectedItem().toString();
        String cityf = cityFurniture.getSelectedItem().toString();

// создаём позицию мебели из полей через конструктор
        Furniture newFurniture = new Furniture(idf, namefur, lengthf, widthf, heightf, cityf, numberV,
                adressV, dateIn, commitf, uploadUri.toString(), statusf, "null", "null",
                "склад", "null"); //

// проверка перед передачей в БД, что все необходимые поля заполнены.
        if (!TextUtils.isEmpty(namefur) && !TextUtils.isEmpty(lengthf) && !TextUtils.isEmpty(widthf)
                && !TextUtils.isEmpty(heightf) && !TextUtils.isEmpty(cityf) && !TextUtils.isEmpty(numberV)
                && !TextUtils.isEmpty(adressV) && !TextUtils.isEmpty(dateIn))
        {
            if(idf != null)
            {
                // после ввода данных окно закрывается, чтоб случайно не нажать повторно
                Intent in = new Intent(this, List_Meny1.class);  // this - иду с текущего экрана, далее куда иду
                startActivity(in);
                mDataBase.child(idf).setValue(newFurniture);
                Toast.makeText(this, "Данные внесены!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Необходимо заполнить все поля", Toast.LENGTH_SHORT).show();
        }
    }
    // переопределение метода onClick. Содержит функцию загрузки фото
    @Override
    public void onClick(View view) {
        uploadImage();
    }

    // загрузка фото из галереи
    public void onClickImportPhoto (View view)
    {
        getPhoto();
        inputDate.setVisibility(Button.VISIBLE);
    }
 // если фото загрузилсь без ошибок, то получаем ссылку на фото
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && data != null && data.getData() != null)
        {
            if (resultCode == RESULT_OK)
            {
                photoF.setImageURI(data.getData());
            }
        }
    }
// загрузка фото в базу данных
    private void uploadImage ()
    {
        Bitmap bitmap = ((BitmapDrawable) photoF.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte [] byteArray = baos.toByteArray();
        final StorageReference mRef = mStorageRef.child(System.currentTimeMillis()+"furniture");
        UploadTask up = mRef.putBytes(byteArray);
        Task<Uri> task = up.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return mRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task)
            {
                uploadUri = task.getResult();
                saveFurniture();
            }
        });
    }
    private void getPhoto ()
    {
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser, 1);
    }
}