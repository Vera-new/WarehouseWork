package com.example.warehousework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;
// класс, в котором указаны поля, которые содержит мебель
// здесь определены 2 конструктора
public class Furniture {
    public String id, name, length, width, height, city, numberV, adressV, date, commit, photo_id,
            status, eo, invent, where, dataOut;

    public Furniture() {

    }

    public Furniture(String id, String name, String length, String width, String height, String city, String numberV, String adressV, String date, String commit, String photo_id, String status, String eo, String invent, String where, String dataOut) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        this.city = city;
        this.numberV = numberV;
        this.adressV = adressV;
        this.date = date;
        this.commit = commit;
        this.photo_id = photo_id;
        this.status = status;
        this.eo = eo;
        this.invent = invent;
        this.where = where;
        this.dataOut = dataOut;
    }
}
