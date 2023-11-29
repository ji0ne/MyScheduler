package kr.ac.uc.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    myDBHelper myDBHelper;
    EditText etStuNum, etStuName, etNumResult, etNameResult;
    Button btnInit, btnInsert, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etStuNum = findViewById(R.id.etStuNum);
        etStuName = findViewById(R.id.etStuName);
        etNumResult = findViewById(R.id.etNumResult);
        etNameResult = findViewById(R.id.etNameResult);
        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        myDBHelper = new myDBHelper(this);

        btnInit.setOnClickListener(v -> {
            sqlDB = myDBHelper.getWritableDatabase();
            myDBHelper.onUpgrade(sqlDB,1,2);
            sqlDB.close();
        });
        btnInsert.setOnClickListener(v -> {
            String sql = "INSERT INTO studentTbl VALUES (" + Integer.parseInt(etStuNum.getText().toString()) + ", '"+etStuName.getText().toString() +"');";
            sqlDB = myDBHelper.getWritableDatabase();
            sqlDB.execSQL(sql);
            sqlDB.close();
            Toast.makeText(getApplicationContext(),"입력됨",Toast.LENGTH_SHORT).show();
            etStuNum.setText("");
            etStuName.setText("");
        });
        btnSelect.setOnClickListener(v -> {
            sqlDB = myDBHelper.getReadableDatabase();
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT * FROM studentTbl;",null);
            String strNum = "학번"+"\r\n"+"\r\n";
            String strName = "이름"+"\r\n"+"\r\n";
            while (cursor.moveToNext()){
                strNum += cursor.getString(0) + "\r\n";
                strName += cursor.getString(1) + "\r\n";
            }
            etNumResult.setText(strNum);
            etNameResult.setText(strName);
            cursor.close();
            sqlDB.close();

        });



    } //onCreate End..

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "studentTbl", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE studentTbl ( stuNum INTEGER PRIMARY KEY,stuName VARCHAR(20));");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS studentTbl");
            onCreate(db);
        }
    } //myDBHelper End.



}// Main End..