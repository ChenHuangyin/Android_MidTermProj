package com.example.going.midproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by chshux on 2017/11/11.
 */

public class SanGuoDatabase extends SQLiteOpenHelper {
    private static final String CREATE_PERSON = "create table if not exists person (id integer primary key autoincrement," +
            "name text," +
            "force text," +
            "abstract text," +
            "history text" +
            ")";
    private static final String FIND_PERSON = "select * from person where id=?";
    private Context mContext;
    public SanGuoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PERSON);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(String name, String force, String person_abstract, String history) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("force", force);
        values.put("abstract", person_abstract);
        values.put("history", history);
        values.put("collect", "false");
        values.put("visited", "false");
        long res = db.insert("person", null, values);
        return res;
    }

    public void update(String id, String name, String force, String person_abstract, String history) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("force", force);
        values.put("abstract", person_abstract);
        values.put("history", history);
        db.update("person", values, "id=?", new String[]{String.valueOf(id)} );
    }

    public String find_by_name(String name, String attr) {
        SQLiteDatabase db = this.getWritableDatabase();
        String res = "查无此人";
        Cursor cursor = db.query("person", null, "name=?", new String[]{name}, null, null, null);
        if(cursor.moveToFirst()) {
            res = cursor.getString(cursor.getColumnIndex(attr));
        }
        cursor.close();
        return res;
    }

    public String find_by_id(String id, String attr) {
        SQLiteDatabase db = this.getWritableDatabase();
        String res = "查无此人";
        Cursor cursor = db.query("person", null, "id=?", new String[]{id}, null, null, null);
        if(cursor.moveToFirst()) {
            res = cursor.getString(cursor.getColumnIndex(attr));
        }
        cursor.close();
        return res;
    }

    public ArrayList<String> find_by_force(String force, String attr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> res = new ArrayList<String>();
        Cursor cursor = db.query("person", null, "force=?", new String[]{force}, null, null, null);
        cursor.moveToFirst();
        do {
            res.add(cursor.getString(cursor.getColumnIndex(attr)));
        } while (cursor.moveToNext());
        cursor.close();
        return res;
    }

    public ArrayList<String> fuzzy_find_by_name(String name, String attr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> res = new ArrayList<String>();
        Cursor cursor = db.query("person", null, "name like ?", new String[]{"%" + name + "%"}, null, null, null);
        cursor.moveToFirst();
        do {
            res.add(cursor.getString(cursor.getColumnIndex(attr)));
        } while (cursor.moveToNext());
        cursor.close();
        return res;
    }

    public void set_visited(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("visited", "true");
        db.update("person", values, "id=?", new String[]{id} );
    }

    public ArrayList<String> all() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> res = new ArrayList<String>();
        Cursor cursor = db.query("person", null, null, null, null, null, null);
        cursor.moveToFirst();
        do {
            res.add(cursor.getString(cursor.getColumnIndex("name")));
        } while (cursor.moveToNext());
        cursor.close();
        return res;
    }

    public ArrayList<String> all_collect() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> res = new ArrayList<String>();
        Cursor cursor = db.query("person", null, "collect=true", null, null, null, null);
        cursor.moveToFirst();
        do {
            res.add(cursor.getString(cursor.getColumnIndex("id")));
        } while (cursor.moveToNext());
        cursor.close();
        return res;
    }

    public ArrayList<String> all_visited() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> res = new ArrayList<String>();
        Cursor cursor = db.query("person", null, "visited=true", null, null, null, null);
        cursor.moveToFirst();
        do {
            res.add(cursor.getString(cursor.getColumnIndex("id")));
        } while (cursor.moveToNext());
        cursor.close();
        return res;
    }

    public void set_collect(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("collect", "true");
        db.update("person", values, "id=?", new String[]{id} );
    }

    public void remove_collect(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("collect", "false");
        db.update("person", values, "id=?", new String[]{id} );
    }

    public int person_delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete("person", "id=?", new String[]{id});
        return rows;
    }

    public Bitmap find_img_by_name(Context context, String name) throws IOException {
        InputStream is = context.getAssets().open(name + ".jpg");
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }
}
