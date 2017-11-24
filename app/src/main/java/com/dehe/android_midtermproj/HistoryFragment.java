package com.dehe.android_midtermproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/11/6.
 */

public class HistoryFragment extends Fragment {
    private NewListView mListView;
    private ItemAdapter itemAdapter;
    private List<Info> detail;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(container.getContext()).inflate(R.layout.history, null);
        detail=new ArrayList<Info>() ;
        detail.add(new Info("张飞","蜀","阉人"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        itemAdapter = new ItemAdapter(container.getContext(), detail);
        mListView=(NewListView)view.findViewById(R.id.historyItem);
        mListView.setAdapter(itemAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(container.getContext(),DetailActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
