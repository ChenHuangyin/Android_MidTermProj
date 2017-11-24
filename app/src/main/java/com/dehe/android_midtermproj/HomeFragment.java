package com.dehe.android_midtermproj;

/**
 * Created by lenovo on 2017/11/6.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomeFragment extends Fragment {
    private NewListView mListView;
    private ItemAdapter itemAdapter;
    private List<Info> detail;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.mainpage, null);
        detail=new ArrayList<Info>() ;
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        itemAdapter = new ItemAdapter(container.getContext(), detail);
        mListView=(NewListView)view.findViewById(R.id.mainpageItem);
        mListView.setAdapter(itemAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(container.getContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
        ImageButton shu=(ImageButton) view.findViewById(R.id.shu);
        shu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent in=new Intent(container.getContext(),BelongActivity.class);
                in.putExtra("country",1);
                startActivity(in);
            }
        });
        ImageButton wu=(ImageButton) view.findViewById(R.id.wu);
        wu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent in=new Intent(container.getContext(),BelongActivity.class);
                in.putExtra("country",2);
                startActivity(in);
            }
        });
        ImageButton wei=(ImageButton) view.findViewById(R.id.wei);
        wei.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent in=new Intent(container.getContext(),BelongActivity.class);
                in.putExtra("country",3);
                startActivity(in);
            }
        });
        ImageButton qun=(ImageButton) view.findViewById(R.id.qun);
        qun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent in=new Intent(container.getContext(),BelongActivity.class);
                in.putExtra("country",4);
                startActivity(in);
            }
        });
        return view;
    }
}
