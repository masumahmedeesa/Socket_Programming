package eesha.socketclient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "messages.db";
    private static final String TABLE_NAME = "messages_details";
    private static final String ID = "id";
    private static final String NAME = "Name";
    private static final String MESSAGE = "Message";
    private static final int VERSION_NUMBER = 1;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(30),"+MESSAGE+" VARCHAR(1000));";
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{

            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is called : ", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(context, "Exception : ", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            Toast.makeText(context, "onUpgrade is called : ", Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(sqLiteDatabase);

        } catch (Exception e) {
            Toast.makeText(context, "Exception : ", Toast.LENGTH_LONG).show();
        }
    }

    public long saveData(String name, String message){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, String.valueOf(name));
        contentValues.put(MESSAGE, String.valueOf(message));

        long rowNumber = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowNumber;
    }

    public Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }
}
