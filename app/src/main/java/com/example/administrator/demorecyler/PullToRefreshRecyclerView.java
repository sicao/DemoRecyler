package com.example.administrator.demorecyler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Administrator on 2015/4/16.
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView>{
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    //四个抽象方法----》刷新的流向
    /*
    *
    * */
    @Override
    public Orientation getPullToRefreshScrollDirection() {
        //返回垂直流向
        return Orientation.VERTICAL;
    }
/*
* 创建一个包裹的view
* */
    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView=new RecyclerView(context,attrs);
        //设置一个Id
        recyclerView.setId(R.id.recycler);
        return recyclerView;
    }

    /**
     * 是否滑动到底部
     * @return
     */
    @Override
    protected boolean isReadyForPullEnd() {
        RecyclerView recyclerView = getRefreshableView();
        View view=recyclerView.getChildAt(recyclerView.getChildCount()-1);
        int count = recyclerView.getAdapter().getItemCount();
        int position = recyclerView.getChildPosition(view);
        return position==count-1&&view.getBottom()==recyclerView.getHeight();
    }
    /**
     * 是否滑动到顶部
     *
     * @return
     */
    @Override
    protected boolean isReadyForPullStart() {
        RecyclerView recyclerView=getRefreshableView();
        View view=recyclerView.getChildAt(0);
        int position = recyclerView.getChildPosition(view);

        return position==0&&view.getTop()==0;
    }
}
