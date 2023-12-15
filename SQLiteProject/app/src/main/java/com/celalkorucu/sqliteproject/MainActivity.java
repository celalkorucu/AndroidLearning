package com.celalkorucu.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            SQLiteDatabase database = MainActivity.this.openOrCreateDatabase("Musicians" , MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY , name VARCHAR , age INTEGER)");

            //database.execSQL("INSERT INTO musicians (name , age) VALUES ('James' , 50)");
            //database.execSQL("INSERT INTO musicians (name,age) VALUES ('Lars',60)");
            //database.execSQL("INSERT INTO musicians (name,age) VALUES ('Celal',20)");
            //database.execSQL("INSERT INTO musicians (name,age) VALUES ('İsmail',43)");

            database.execSQL("UPDATE musicians SET name  = 'İsmail Korucu' WHERE name='İsmail'");
            Cursor cursor = database.rawQuery("SELECT *FROM musicians WHERE age > 42" , null);

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");



           while(cursor.moveToNext()){

               System.out.println("ID : "+cursor.getInt(idIx));
               System.out.println("Name : "+cursor.getString(nameIx));
               System.out.println("Age : "+cursor.getInt(ageIx));

            }

            cursor.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}