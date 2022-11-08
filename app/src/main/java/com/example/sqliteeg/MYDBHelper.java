package com.example.sqliteeg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MYDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactDB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE = "contact";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";

    public MYDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE THE TABLE
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT NOT NULL, " + KEY_PHONE_NO + " TEXT NOT NULL" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public void addContact(String Name, String Phone_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, Name);
        cv.put(KEY_PHONE_NO, Phone_no);
        db.insert(DATABASE_TABLE, null, cv);
    }

    public ArrayList<ContactModel> fetchData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
        ArrayList<ContactModel> arrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.id = cursor.getInt(0);
            contactModel.Name = cursor.getString(1);
            contactModel.Description = cursor.getString(2);
            arrayList.add(contactModel);
        }
        return arrayList;
    }

    public void updateContact(ContactModel contactModel) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_PHONE_NO,contactModel.Description);

        database.update(DATABASE_TABLE,cv,KEY_ID+"="+contactModel.id,null);
    }
    public void deleteContact(int id){
        SQLiteDatabase database = this.getWritableDatabase();


        database.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{String.valueOf(id)});
    }
}
