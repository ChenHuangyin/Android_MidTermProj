package com.dehe.android_midtermproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/23.
 */

public class BelongActivity extends AppCompatActivity {
    private List<Info> detail;
    private ItemAdapter itemAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.belonging);
        Intent intent=getIntent();
        Bundle bud=intent.getExtras();
        int which=bud.getInt("country");
        ImageView country=(ImageView)findViewById(R.id.country);

        TextView intro=(TextView)findViewById(R.id.intro);
        if(which==1){
            country.setImageResource(R.mipmap.shu);
            intro.setText(R.string.shu_intro);
        }
        else if(which==2){
            country.setImageResource(R.mipmap.wu);
            intro.setText(R.string.wu_intro);
        }
        else if(which==3){
            country.setImageResource(R.mipmap.wei);
            intro.setText(R.string.wei_intro);
        }
        else{
            country.setImageResource(R.mipmap.qun);
            intro.setText(R.string.qun_intro);
        }
        ListView mListView=(ListView)findViewById(R.id.belong);
        detail=new ArrayList<Info>() ;
        detail.add(new Info("张飞","蜀","阉人"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        itemAdapter = new ItemAdapter(this, detail);
        mListView.setAdapter(itemAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(BelongActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}
