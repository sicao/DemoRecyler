package com.example.administrator.demorecyler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private RecyclerView recycler;
    private TestAdapter adapter;
    private PullToRefreshRecyclerView refresh;

    private int pager=0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    List<String> list=new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        list.add(String.format("new 第%03d条数据",i));
                    }
                    adapter.clear();
                    adapter.addAll(list);
                   // MainActivity.this.list.addAll(list);
                    adapter.notifyDataSetChanged();
                    //关闭下拉刷新---刷新完成
                    refresh.onRefreshComplete();
                break;
                case 1:
                    //加载更多
                    List<String> list1=new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        list1.add(String.format("第%03d条数据",i+pager*100));
                    }
                    //MainActivity.this.list.addAll(list1);
                    adapter.addAll(list1);
                    adapter.notifyDataSetChanged();
                    refresh.onRefreshComplete();

                    break;

            }
        }
    };
    //private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = ((RecyclerView) findViewById(R.id.recycler));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        //manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //manager.setReverseLayout(true);
        //有三列
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        // gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 3;
                }
                if (position == 5) {
                    return 2;
                }

                return 1;
            }
        });
        recycler.setLayoutManager(manager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("第%03d条数据", i));
        }
        adapter = new TestAdapter(this, list);
        recycler.setAdapter(adapter);
        refresh = ((PullToRefreshRecyclerView) findViewById(R.id.refersh));
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                adapter.clear();
                pager=0;
                handler.sendEmptyMessageDelayed(0, 1000);

            }

            //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pager++;
                handler.sendEmptyMessageDelayed(1,1000);
            }
        });
        MyItemAnimator animator=new MyItemAnimator();
        animator.setRemoveDuration(500);
        animator.setAddDuration(500);
        recycler.setItemAnimator(animator);

    }
}
