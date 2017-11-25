package com.dehe.android_midtermproj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by going on 2017/11/5.
 */

public class StoryActivity extends AppCompatActivity {
    private String hero_name;
    private String story_name;
    private int story_id;
    private int img_id;

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        int position = (int) intent.getExtras().get("position");
        if(position == 0){
            hero_name = "赵云";
            story_name = "单骑救主";
            story_id = R.string.story2;
            //img_id = ;
        }
        else if(position == 1){
            hero_name = "曹操";
            story_name = "官渡之战";
            story_id = R.string.story3;
            //img_id = ;
        }
        else{
            hero_name = "诸葛亮";
            story_name = "草船借箭";
            story_id = R.string.story1;
            //img_id = ;
        }

        //返回按键
        ImageButton backButton = (ImageButton)findViewById(R.id.back_story);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //人物收藏
        final ImageButton heroCollect = (ImageButton) findViewById(R.id.collectHero_story);
        heroCollect.setTag("0");
        heroCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(heroCollect.getTag()=="0"){
                    heroCollect.setImageResource(R.mipmap.full_star);
                    heroCollect.setTag("1");
                    //将当前人物添加到人物收藏的表中

                    String msg = hero_name+"已添加到收藏夹";
                    Toast.makeText(StoryActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
                else{
                    heroCollect.setImageResource(R.mipmap.empty_star);
                    heroCollect.setTag("0");
                    //将当前人物从人物收藏的表中删除

                }
            }
        });

        //故事标题及内容、主要人物头像及名字
        TextView title = (TextView) findViewById(R.id.title_story);
        title.setText(story_name);
        TextView content = (TextView) findViewById(R.id.text_story);
        content.setText(story_id);
        ImageView heroIamge = (ImageView) findViewById(R.id.heroImage_story);
        heroIamge.setImageResource(R.mipmap.zhugeliang);
        TextView heroName = (TextView) findViewById(R.id.heroName_story);
        heroName.setText(hero_name);

    }

}
