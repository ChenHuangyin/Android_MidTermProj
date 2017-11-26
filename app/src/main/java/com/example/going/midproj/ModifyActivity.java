package com.example.going.midproj;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {
    private  SanGuoDatabase sanGuoDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        final Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        Toolbar toolbar = (Toolbar) findViewById(R.id.modifytoolbar);
        toolbar.setTitle("修改信息");
        setSupportActionBar(toolbar);

        sanGuoDatabase = new SanGuoDatabase(this , "sanguo.db", null, 1);
        String name = sanGuoDatabase.find_by_id(id, "name");
        String force = sanGuoDatabase.find_by_id(id, "force");
        String p_abstract = sanGuoDatabase.find_by_id(id, "abstract");
        String history = sanGuoDatabase.find_by_id(id, "history");
        final EditText editText = (EditText) findViewById(R.id.modifyname);
        final EditText editText1 = (EditText) findViewById(R.id.modifyforce);
        final EditText editText2 = (EditText) findViewById(R.id.modifyabstract);
        final EditText editText3 = (EditText) findViewById(R.id.modifyhistory);
        editText2.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText2.setGravity(Gravity.TOP);
        editText3.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        editText3.setGravity(Gravity.TOP);
        editText.setText(name);
        editText1.setText(force);
        editText2.setText(p_abstract);
        editText3.setText(history);
        editText2.setSingleLine(false);
        editText3.setSingleLine(false);
        Button button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanGuoDatabase = new SanGuoDatabase(ModifyActivity.this , "sanguo.db", null, 1);
                sanGuoDatabase.update(id, editText.getText().toString(), editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString());
                Toast.makeText(v.getContext(), "保存成功", Toast.LENGTH_SHORT);
                Intent intent1 = new Intent();
                intent.putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }

}
