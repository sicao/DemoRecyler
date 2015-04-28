package com.example.administrator.demorecyler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2015/4/16.
 */
//不要导错包
public class TestAdapter extends RecyclerView.Adapter <TestAdapter.ViewHolder>{
    private Context context;
    private List<String> list;

    public TestAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
     /*
    * 创建ViewHold
    * parent:adapter从属的RecyclerView
    * viewType:item的类型
    * */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }
//
    /*
    * 绑定队对象：
    * */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
      private TextView text;
        //这里必须这么写
        public ViewHolder(View itemView) {
            super(itemView);
            text= ((TextView) itemView.findViewById(R.id.text));
        }
    }
    public void  clear()
    {
        int size = list.size();
        list.clear();
        notifyItemRangeChanged(0,size);

    }
    public void addAll(List<String> strings)
    {
        int size = list.size();
        list.addAll(strings);
        notifyItemRangeInserted(size,strings.size());

    }
}
