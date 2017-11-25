package com.dehe.android_midtermproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class SearchListAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<String> name;
    private MyFilter mFilter;
    private ArrayList<String> mOriginalValues;
    private final Object mLock = new Object();

    public SearchListAdapter(Context context, List<String> name){
        this.context=context;
        this.name=name;
    }
    @Override
    public int getCount(){
        if(name!=null){
            return name.size();
        }
        else
            return 0;
    }
    @Override
    public Object getItem(int i){
        if(name==null)
            return null;
        else
            return name.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View sView;
        SearchListAdapter.ViewHolder holder;
        if (view == null) {
            sView = LayoutInflater.from(context).inflate(R.layout.list_view_item, null);
            holder = new SearchListAdapter.ViewHolder();
            holder.name = (TextView) sView.findViewById(R.id.iconName);
            holder.head=(ImageView)sView.findViewById(R.id.head);
            sView.setTag(holder);
        } else {
            sView = view;
            holder = (SearchListAdapter.ViewHolder) sView.getTag();
        }
        holder.name.setText(name.get(position));
        holder.head.setImageResource(R.mipmap.shu);
        return sView;
    }

    private class ViewHolder {
        public TextView name;
        public ImageView head;
        public ViewHolder(){}
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new MyFilter();
        }
        return mFilter;
    }

    class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (mOriginalValues == null) {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<String>(name);
                }
            }
            if (prefix == null || prefix.length() == 0) {
                synchronized (mLock) {
                    ArrayList<String> list = new ArrayList<String>(
                            mOriginalValues);
                    results.values = list;
                    results.count = list.size();
                }
            } else {
                String prefixString = prefix.toString().toLowerCase();

                final ArrayList<String> values = mOriginalValues;

                final int count = values.size();

                final ArrayList<String> newValues = new ArrayList<String>(
                        count);

                for (String value : values) {
                    String title = value.toLowerCase();

                    if (title.indexOf(prefixString) != -1) {
                        newValues.add(value);
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            name = (ArrayList<String>) results.values;

            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}
