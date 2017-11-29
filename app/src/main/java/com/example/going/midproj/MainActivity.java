package com.example.going.midproj;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.provider.ContactsContract.Directory.PACKAGE_NAME;

public class MainActivity extends AppCompatActivity {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "sanguo.db"; //保存的数据库文件名
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + "com.example.going.midproj" + "/databases";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            File file = new File(DB_PATH + "/" + DB_NAME);
            if (!(file.exists())) {
                //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = this.getResources().openRawResource(
                        R.raw.sanguo); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(DB_PATH + "/" + DB_NAME);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
                Toast.makeText(this, "123",Toast.LENGTH_SHORT).show();
            } else {
                file.delete();
                InputStream is = this.getResources().openRawResource(
                        R.raw.sanguo); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(DB_PATH + "/" + DB_NAME);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonPageActivity.class);
                intent.putExtra("name", "关羽");
                startActivity(intent);
            }
        });
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
                startActivity(intent);
            }
        });
        final EditText textView = (EditText) findViewById(R.id.editText);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PersonPageActivity.class);
                intent.putExtra("name", textView.getText().toString());
                startActivity(intent);
            }
        });
    }
}
