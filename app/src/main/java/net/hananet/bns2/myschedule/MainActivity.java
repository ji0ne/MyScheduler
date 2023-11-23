package net.hananet.bns2.myschedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPty , btnCsw , btnPjw , btnLyj ,btnKdh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setBtnInstance();
        //각자 레이아웃으로 작업

        btnPty.setOnClickListener(v ->
        {
            Intent intentPty = new Intent(this,ActivityPty.class);
            startActivity(intentPty);
        });

        btnKdh.setOnClickListener(v ->
        {
            Intent intentKdh = new Intent(this , ActivityKdh.class);
            startActivity(intentKdh);
        });

        btnCsw.setOnClickListener(v ->
        {
            Intent intentCsw = new Intent(this,ActivityCsw.class);
            startActivity(intentCsw);
        });

        btnLyj.setOnClickListener(v ->
        {
            Intent intentLyj = new Intent(this,ActivityLyj.class);
            startActivity(intentLyj);
        });

        btnPjw.setOnClickListener(v ->
        {
            Intent intentPjw = new Intent(this,ActivityPjw.class);
            startActivity(intentPjw);
        });


    } //end of Oncreate

    void setBtnInstance()
    {
        btnPty = findViewById(R.id.btnPty);
        btnKdh = findViewById(R.id.btnKdh);
        btnCsw = findViewById(R.id.btnCsw);
        btnLyj = findViewById(R.id.btnLyj);
        btnPjw = findViewById(R.id.btnPjw);
    }


}