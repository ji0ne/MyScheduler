package net.hananet.bns2.myschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPjw extends AppCompatActivity {

    EditText et_pjw_insertText;
    Button btn_pjw_input,btn_pjw_read;
    TextView tv_pjw_readDb;

    SQLiteDatabase sqLiteDatabase;
    FeedReaderDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pjw);
        setInstance(); // 인스턴스화 메서드 호출

        //데이터베이스 초기화
        dbHelper = new FeedReaderDbHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        //데이터 입력
        btn_pjw_input.setOnClickListener(v -> {
            String strInputText = et_pjw_insertText.getText().toString().trim();

            if(!strInputText.isEmpty()) //editText가 비어있지 않다면 데이터 입력 메서드 동작
            {
                InsertData(strInputText);
            }

        });

    } // end of OnCreate

    //데이터 삽입 메서드
    private void InsertData(String strInputText)
    {
        ContentValues values = new ContentValues(); //DB에 추가할 데이터를 담기 위한 객체
        values.put(FeedReaderContract.COLUMN_NAME_TITLE, strInputText);
        //FeedReaderContract.COLUMN_NAME_TITLE를 통해 DB열에 입력한 텍스트 저장
        long newRowId = sqLiteDatabase.insert(FeedReaderContract.TABLE_NAME, null, values);
        //ContentValues에 담긴 데이터를 추가 , (추가할 테이블,삽입데이터)

        if(newRowId != -1) //newRowId : 삽입된 행의 id 반환 , 성공 시 해당 행 id반환 , 실패 시 -1 반환
        { //삽입 성공 시 토스트 메시지
            Toast.makeText(this , "데이터가 삽입되었습니다 . ",Toast.LENGTH_SHORT).show();
        }
    }

    public final class FeedReaderContract { //SQLite 데이터베이스와 관련된 정보를 정의하는 클래스
        private FeedReaderContract() {} //생성자
        //외부에서 이 클래스를 인스턴스화하거나 상속하여 사용하는 것을 방지
        //클래스의 목적에 맞게 상수들을 정의하고 접근하는 용도로만 사용될 수 있도록 함

        //데이터베이스 테이블 이름과 각 열의 이름을 정의하는 상수 선언부
        public static final String TABLE_NAME = "timeTable";
        public static final String COLUMN_NAME_TITLE = "schedule";
        public static final String COLUMN_NAME_SUBTITLE = "schedule_subtitle";
    }

    //데이터베이스 도우미 클래스
    public class FeedReaderDbHelper extends SQLiteOpenHelper
    {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "FeedReader.db";
        
        //데이터베이스 테이블 생성 쿼리
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedReaderContract.TABLE_NAME + " (" +
                        FeedReaderContract.COLUMN_NAME_TITLE + " TEXT," +
                        FeedReaderContract.COLUMN_NAME_SUBTITLE + " TEXT)";

        public FeedReaderDbHelper(ActivityPjw context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) { //테이블 생성
            sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //DB버전 업그레이드 시 여기서 코드 작성
        }

    }

    void setInstance()
    {
        et_pjw_insertText = findViewById(R.id.et_pjw_insertText);
        btn_pjw_input = findViewById(R.id.btn_pjw_input);
        btn_pjw_read = findViewById(R.id.btn_pjw_read);
        tv_pjw_readDb = findViewById(R.id.tv_pjw_readDb);
    }


}