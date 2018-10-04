package com.example.julian.sqlitedata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = openOrCreateDatabase("mySqlite.db", MODE_PRIVATE, null);
        if (myDB.isOpen()) {
            Log.i("Mensaje", "Base de Datos abierta");
        }
    }

    public void safe(View view) {
        EditText barCode = findViewById(R.id.id_bar_code);
        EditText productName = findViewById(R.id.insert_product_name);
        if (myDB.isOpen()) {
            myDB.execSQL(
                    "CREATE TABLE IF NOT EXISTS product (id_bar_code VARCHAR(20), name_product VARCHAR(200))"
            );
            Log.i("Mensaje", "Tabla creada o existente");
            /*ContentValues row1 = new ContentValues();
            row1.put("id_bar_code", barCode.getText().toString());
            row1.put("name_product", productName.getText().toString());
            myDB.insert("product", null, row1);*/
            Cursor myCursor =
                    myDB.rawQuery("select * from product", null);
            while(myCursor.moveToNext()) {
                String barCodeT = myCursor.getString(0);
                Log.i("Mensaje", barCodeT);
                String productNameT = myCursor.getString(1);
                Log.i("Mensaje", productNameT);
            }
            myCursor.close();
        }
        myDB.close();
    }
}
