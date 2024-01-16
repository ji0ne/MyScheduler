package kr.ac.uc.alertdialog;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnNew, btnMy;
    TextView tvNewCode, tvMyCode;
    EditText et;
    AlertDialog ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNew = findViewById(R.id.btnNew);
        btnMy = findViewById(R.id.btnMy);
        tvNewCode = findViewById(R.id.tvNewCode);
        tvMyCode = findViewById(R.id.tvMyCode);

        et = new EditText( this );

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        btnNew.setOnClickListener(view -> {
            builder.setCancelable(false);
            builder.setTitle("새 캘린더");
            builder.setMessage("새로 생성할 캘린더의 코드를 등록해 주세요.");
            builder.setView(et);
            builder.setPositiveButton("등록", (dialog, which) -> {
                if(et.getText().toString().length() == 5) {
                    Toast.makeText(MainActivity.this, "새 코드가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    tvNewCode.setText(et.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "형식에 맞지 않는 코드입니다.", Toast.LENGTH_SHORT).show();
                }
            });
            ad = builder.create();

            //AlertDailog 표시
            ad.show();

            ad.setOnDismissListener(dialogInterface -> {
                if (et != null) {
                    ViewParent parent = et.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(et);
                    }
                }
                et.getText().clear();
            });
        });

        btnMy.setOnClickListener(view -> {
            builder.setCancelable(false);
            builder.setTitle("캘린더 열기");
            builder.setMessage("열람할 캘린더의 코드를 입력해 주세요.");
            builder.setView(et);
            builder.setPositiveButton("입력", (dialog, which) -> {
                if(et.getText().toString().equals(tvNewCode.getText().toString())) {
                    Toast.makeText(MainActivity.this, "[" + et.getText().toString() + "] 입니다.", Toast.LENGTH_SHORT).show();
                    tvMyCode.setText(et.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "목록에 없는 코드입니다.", Toast.LENGTH_SHORT).show();
                }
            });
            ad = builder.create();

            //AlertDailog 표시
            ad.show();

            ad.setOnDismissListener(dialogInterface -> {
                if (et != null) {
                    ViewParent parent = et.getParent();
                    if (parent instanceof ViewGroup) {
                        ((ViewGroup) parent).removeView(et);
                    }
                }
                et.getText().clear();
            });
        });
    }
}