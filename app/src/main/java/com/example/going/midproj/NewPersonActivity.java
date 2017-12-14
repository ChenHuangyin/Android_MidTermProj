package com.example.going.midproj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NewPersonActivity extends AppCompatActivity {
    private  SanGuoDatabase sanGuoDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Toolbar toolbar = (Toolbar) findViewById(R.id.modifytoolbar);
        toolbar.setTitle("新增人物");
        setSupportActionBar(toolbar);
        sanGuoDatabase = new SanGuoDatabase(this , "sanguo.db", null, 1);
        ArrayList<String> a = sanGuoDatabase.all_visited();
        final EditText editText = (EditText) findViewById(R.id.modifyname);
        final EditText editText1 = (EditText) findViewById(R.id.modifyforce);
        final EditText editText2 = (EditText) findViewById(R.id.modifyabstract);
        final EditText editText3 = (EditText) findViewById(R.id.modifyhistory);
        editText2.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText2.setGravity(Gravity.TOP);
        editText3.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText3.setGravity(Gravity.TOP);
        editText2.setSingleLine(false);
        editText3.setSingleLine(false);
        Button button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanGuoDatabase = new SanGuoDatabase(NewPersonActivity.this , "sanguo.db", null, 1);
                long res = sanGuoDatabase.insert(editText.getText().toString(), editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
                if(res == 1) {
                    Toast.makeText(v.getContext(), "保存成功", Toast.LENGTH_SHORT);
                } else {
                    Toast.makeText(v.getContext(), "保存失败", Toast.LENGTH_SHORT);
                }

                finish();
            }
        });
    }
}
