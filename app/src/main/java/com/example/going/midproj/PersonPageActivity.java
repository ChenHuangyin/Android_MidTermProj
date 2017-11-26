package com.example.going.midproj;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.IOException;

public class PersonPageActivity extends AppCompatActivity {
    private SanGuoDatabase sanGuoDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_page);
        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        sanGuoDatabase = new SanGuoDatabase(this , "sanguo.db", null, 1);
        try {
            Bitmap bitmap = sanGuoDatabase.find_img_by_name(this, name);
            ImageView imageView = (ImageView)findViewById(R.id.app_bar_image);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(name);

        TextView textView = (TextView) findViewById(R.id.p_abstract);
        String sp = sanGuoDatabase.find_by_name(name, "abstract");
        textView.setText(sp);
        TextView history = (TextView) findViewById(R.id.history);
        String sh = sanGuoDatabase.find_by_name(name, "history");
        history.setText(sh + "\n");
        final String id = sanGuoDatabase.find_by_name(name, "id");
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.personfab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PersonPageActivity.this, ModifyActivity.class);
                intent1.putExtra("id", id);
                startActivityForResult(intent1, 1);
            }
        });
        Button button = (Button) findViewById(R.id.person_delete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = sanGuoDatabase.person_delete(id);
                if (temp == 1) {
                    Toast.makeText(v.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(v.getContext(), "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String id = data.getStringExtra("id");
        sanGuoDatabase = new SanGuoDatabase(this , "sanguo.db", null, 1);
        String name = sanGuoDatabase.find_by_id(id, "name");
        String p_abstract = sanGuoDatabase.find_by_id(id, "abstract");
        String history = sanGuoDatabase.find_by_id(id, "history");
        try {
            Bitmap bitmap = sanGuoDatabase.find_img_by_name(this, name);
            ImageView imageView = findViewById(R.id.app_bar_image);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(name);

        TextView textView =  findViewById(R.id.p_abstract);
        String sp = sanGuoDatabase.find_by_name(name, "abstract");
        textView.setText(sp);
        TextView historyview = findViewById(R.id.history);
        String sh = sanGuoDatabase.find_by_name(name, "history");
        historyview.setText(sh + "\n");
    }
}
