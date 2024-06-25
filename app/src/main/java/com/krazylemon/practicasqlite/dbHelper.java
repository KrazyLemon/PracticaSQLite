package com.krazylemon.practicasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dbHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Contacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Contactos";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOMBRE = "contacto_nombre";
    private static final String COLUMN_CELULAR = "contacto_celular";
    private static final String COLUMN_EMAIL = "contacto_email";

    dbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOMBRE + " TEXT, " +
                        COLUMN_CELULAR  + " TEXT, " +
                        COLUMN_EMAIL + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addContacto(String nombre, String celular, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_CELULAR, celular);
        cv.put(COLUMN_EMAIL, email);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context,"Algo salío mal",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Contacto Creado",Toast.LENGTH_SHORT).show();
        }
    }

    void updateContacto(String row_id, String nombre, String celular, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOMBRE, nombre);
        cv.put(COLUMN_CELULAR, celular);
        cv.put(COLUMN_EMAIL, email);

        long result =  db.update(TABLE_NAME, cv,"_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Algo salío mal",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Contacto Actualizado",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteContacto(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Algo salío mal",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Contacto eliminado",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query =  "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
}
