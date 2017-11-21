package com.pao11.adapter.recyclerview.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

/**
 * GridView的分割线
 * @author pao11
 * 创建日期：2017年11月4日
 */
public class GridItemDecoration extends ItemDecoration {

	//gridView的行列间距
	private int space;
	//gridView的列数
	private int spanCount;
	//gridView的头部数量
	private int headerCount;
	//gridView的底部数量
	private int footerCount;
	//是否为上下左右都有分割线，默认是中间有，边上没有（左下有，最左边没有）
	private int mOrientation = MIDDLESPACE;
	
	public static final int GLEFT 		= 1 << 0;	//最左边的分割线
	public static final int GRIGHT 		= 1 << 1;	//最右边的分割线
	public static final int GTOP 		= 1 << 2;	//最上边的分割线
	public static final int GBOTTOM 	= 1 << 3;	//最下边的分割线
	public static final int GMIDDLEV 	= 1 << 4;	//中间纵向分割线
	public static final int GMIDDLEH 	= 1 << 5;	//中间横向分割线
	
	public static final int FULLSPACE = GLEFT | GRIGHT | GTOP | GBOTTOM | GMIDDLEV | GMIDDLEH;	//各个方向都有分割线
	public static final int MIDDLESPACE = GMIDDLEV | GMIDDLEH;		//中间有分割线，最上边、最左边、最右边没有分割线
	public static final int MIDDLEBOTTOMSPACE = GBOTTOM | GMIDDLEV | GMIDDLEH;	//中间有分隔线，最上边、最左边、最右边没有分割线
	public static final int MIDDLETOPSPACE = GTOP | GMIDDLEV | GMIDDLEH;	//中间有分隔线，最左边、最右边、最下边没有分割线

	/**
	 * 初始化方法
	 * 
	 * @param space			行列间距
	 * @param spanCount		列数
	 * @param headerCount	添加的头部总数
	 * @param footerCount	添加的尾部总数
	 */
    public GridItemDecoration(int space, int spanCount, int headerCount, int footerCount) {
        this.space = space;
        this.spanCount = spanCount;
        this.headerCount = headerCount;
        this.footerCount = footerCount;
    }
    
    /**
     * 
     * @param space			行列间距
	 * @param spanCount		列数
	 * @param headerCount	添加的头部总数
	 * @param footerCount	添加的尾部总数
     */
    public GridItemDecoration(int space, int spanCount, int headerCount, int footerCount, int orientation) {
    	this.space = space;
    	this.spanCount = spanCount;
    	this.headerCount = headerCount;
    	this.footerCount = footerCount;
    	this.mOrientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    	
    	int childLayoutPosition = parent.getChildLayoutPosition(view);
    	int childCount = parent.getAdapter().getItemCount();//总的数量
    	
    	//去除头部
    	if (childLayoutPosition < headerCount) return;
    	//去除底部
    	if (childLayoutPosition >= (childCount - footerCount - 1))return;
    	
    	switch (mOrientation) {
		case GridItemDecoration.FULLSPACE://每个方向都有分割线
			if ((childLayoutPosition - headerCount + 1) % spanCount == 0) {
				outRect.set(space, space, space, 0);
			} else {
				outRect.set(space, space, 0, 0);				
			}
			break;
			
		case GridItemDecoration.MIDDLEBOTTOMSPACE:
		case GridItemDecoration.MIDDLESPACE://只有中间有分割线，top、最左边、最右边没有分割线，
			//不是第一个的格子都设一个左边和底部的间距
			//由于每行都只有spanCount个，所以第一个都是spanCount的倍数，把左边距设为0
			if ((childLayoutPosition - headerCount) % spanCount == 0) {
				outRect.set(0, 0, 0, space);
			} else {
				outRect.set(space, 0, 0, space);
			}
			break;
		case GridItemDecoration.MIDDLETOPSPACE:
			if ((childLayoutPosition - headerCount) % spanCount == 0) {
				outRect.set(0, space, 0, 0);
			} else {
				outRect.set(space, space, 0, 0);
			}
			break;

		default:
			break;
		}
    }
}
