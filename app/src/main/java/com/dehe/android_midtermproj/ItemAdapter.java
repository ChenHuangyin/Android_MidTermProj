package com.dehe.android_midtermproj;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lenovo on 2017/11/7.
 */

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Info> detail;
    public ItemAdapter(Context context, List<Info> detail){
        this.context=context;
        this.detail=detail;
    }
    @Override
    public int getCount(){
        if(detail!=null){
            return detail.size();
        }
        else
            return 0;
    }
    @Override
    public Object getItem(int i){
        if(detail==null)
            return null;
        else
            return detail.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder holder;
        if (view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.demo, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.force = (TextView) convertView.findViewById(R.id.force);
            holder.From = (TextView) convertView.findViewById(R.id.from);
            convertView.setTag(holder);
        } else {
            convertView = view;
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(detail.get(position).getName());
        holder.force.setText(detail.get(position).getForce());
        holder.From.setText(detail.get(position).getFrom());
        return convertView;
    }

    private class ViewHolder {
        public TextView name;
        public TextView force;
        public TextView From;
    }
}
