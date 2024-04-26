package test.toDo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "TaskList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "My_Tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESC = "description";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_DAY = "day";

    DB(@Nullable Context context){
        super(context,  DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase TaskDB) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, " + COLUMN_DESC + " TEXT, " + COLUMN_YEAR + " INTEGER, " + COLUMN_MONTH
                + " INTEGER, " + COLUMN_DAY + " INTEGER);";

        TaskDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase TaskDB, int i, int i1) {
        TaskDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(TaskDB);
    }

    void addTask(String title, String description, int year, int month, int day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESC, description);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_MONTH, month);
        cv.put(COLUMN_DAY, day);
        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "ERROR: DIDNT ADD", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "ADDED", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_YEAR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateTaskData(String row_id, String title, String description, String year, String month, String day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_DESC, description);
        cv.put(COLUMN_YEAR, year);
        cv.put(COLUMN_MONTH, month);
        cv.put(COLUMN_DAY, day);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if (result==-1) {
            Toast.makeText(context, "didn't update", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "UPDATE SUCCESSFUL", Toast.LENGTH_SHORT).show();
    }

    void deleteTask(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if (result==-1)
        {
            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "SUCCESSFULLY DELETED", Toast.LENGTH_SHORT).show();
    }
    /*
    public Cursor Sort(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sortQuery = COLUMN_ID + COLUMN_TITLE + COLUMN_DESC + COLUMN_YEAR + " ASC, " + COLUMN_MONTH + " ASC, " + COLUMN_DAY + " ASC";
        String[] Test = {COLUMN_ID, COLUMN_TITLE, COLUMN_DESC, COLUMN_YEAR, COLUMN_MONTH, COLUMN_DAY};
        return db.query(TABLE_NAME, Test, null, null, null, null, sortQuery);
    }

     <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/SortButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="18dp"
        android:layout_marginBottom="15dp"
        android:clickable="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:srcCompat="@android:drawable/stat_notify_sync" />

    */

}
