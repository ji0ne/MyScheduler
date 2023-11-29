package com.example.swt_cal_sql_ex;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
// activity_main.xml에서 추가한 디자인 부분
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    // activity_main.xml에서 추가한 디자인들을 public 선언 후 각 디자인들의 변수를 선언
    public String readSchedule = null;
    public String str = null; // editText에 들어갈 문자열을 null로 선언
    public CalendarView calendarView;
    public Button save_Btn, correction_Btn, del_Btn; // activity_main에서와 id값이 달라도 아래에 선언시에 고쳐주면 됨
    public TextView titletextView, scheduleTextView; // 제목 text, 일정을 보여주는 text
    public EditText editText;
    public ListView listView;

    DB_Cal dbcal;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml에서 추가한 디자인들을 변수에 각 디자인들의 id를 선언
        titletextView = findViewById(R.id.titletextView); // 캘린더의 타이틀
        editText = findViewById(R.id.editText); // text 입력 구간
        save_Btn = findViewById(R.id.saveBtn); // 저장 버튼
        correction_Btn = findViewById(R.id.correctionBtn); // 수정 버튼
        del_Btn = findViewById(R.id.delBtn); // 삭제 버튼
        listView = findViewById(R.id.listView); //sql 리스트 보이기

        dbcal = new DB_Cal(MainActivity.this, "newdb.db", null, 1);
        db = dbcal.getWritableDatabase();
        dbcal.onCreate(db);

        String sql = "select *"
                + "from mytable;";
        Cursor c = db.rawQuery(sql, null);
        String[] strs = new int[] {R.id.listview_txt};
        SimpleCursorAdapter adapter = null;
        adapter = new SimpleCursorAdapter(listView.getContext(), R.layout.listView, c, strs, ints,0);

        listView.setAdapter(adapter);

    }
}