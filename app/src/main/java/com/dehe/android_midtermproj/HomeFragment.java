package com.dehe.android_midtermproj;

/**
 * Created by lenovo on 2017/11/6.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.dehe.android_midtermproj.R.id.listView;

public class HomeFragment extends Fragment {
    private NewListView mListView;
    private ItemAdapter itemAdapter;
    private List<Info> detail;
    private ConvenientBanner convenientBanner;
    private ArrayList<Integer> imgs;

    private SearchView mSearchView;
    private ListView searchListView;
    private List<String> mStrs;
    private ImageView More;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=LayoutInflater.from(container.getContext()).inflate(R.layout.mainpage, null);

        convenientBanner = (ConvenientBanner)view.findViewById(R.id.pic);
        imgs = new ArrayList<Integer>();
        imgs.add(R.mipmap.first);
        imgs.add(R.mipmap.second);
        imgs.add(R.mipmap.thrid);
        imgs.add(R.mipmap.laugh);
        //开始自动翻页
        convenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, imgs)
                //设置指示器是否可见
                .setPointViewVisible(true)
                //设置自动切换（同时设置了切换时间间隔）
                .startTurning(2000)
                //设置两个点图片作为翻页指示器
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向（左、中、右）
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置点击监听事件
                .setOnItemClickListener(new OnItemClickListener(){
                    public void onItemClick(int position) {
                        Intent intent;
                        if(position < 3){
                            intent = new Intent(view.getContext(), StoryActivity.class);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        }
                        else{
                            intent = new Intent(view.getContext(), JokeActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                //设置手动影响
                .setManualPageable(true);

        detail=new ArrayList<Info>() ;
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        detail.add(new Info("赵云","蜀","常山"));
        detail.add(new Info("吕布","群","不知道"));
        mStrs=new ArrayList<String>();
        mStrs.add("aaa");
        mStrs.add("bbb");
        mStrs.add("ccc");
        mStrs.add("airsaid");
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


        mSearchView = (SearchView) view.findViewById(R.id.search).findViewById(R.id.searchView);
        searchListView = (ListView) view.findViewById(R.id.search).findViewById(R.id.listView);

        SearchListAdapter S_Adapter = new SearchListAdapter(container.getContext(),mStrs);
        searchListView.setAdapter(S_Adapter);
        searchListView.setTextFilterEnabled(true);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                ListAdapter adapter = searchListView.getAdapter();
                Filter filter = ((Filterable) adapter).getFilter();
                if (!TextUtils.isEmpty(newText)){
                    searchListView.setVisibility(View.VISIBLE);
                    filter.filter(newText);
                }else{
                    filter.filter(null);
                    searchListView.setVisibility(View.GONE);
                }
                return false;
            }
        });

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in=new Intent(container.getContext(),DetailActivity.class);
                startActivity(in);
            }
        });
        /*
        shu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent in=new Intent(container.getContext(),BelongActivity.class);
                in.putExtra("country",1);
                startActivity(in);
            }
        });*/
        return view;
    }
    //图片的点击事件
   /* @Override
    public void onItemClick(int position) {
        Intent intent;
        if(position < 3){
            intent = new Intent(this, StoryActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
        else{
            intent = new Intent(this, JokeActivity.class);
            startActivity(intent);
        }
    }*/
    //为了方便改写，来实现复杂布局的切换
   private class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            //你可以通过layout文件来创建，不一定是Image，任何控件都可以进行翻页
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
}
