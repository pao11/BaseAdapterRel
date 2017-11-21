package com.pao11.adapter.recyclerview.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.TypedValue;
import android.view.View;

/**
 * RecyclerView的自定义分割线
 * @author pao11
 * 创建日期：2017年11月4日
 */
public class ListItemDecoration extends ItemDecoration {

	/*
	 * RecyclerView的布局方向，默认先赋值 为纵向布局 RecyclerView 布局可横向，也可纵向 横向和纵向对应的分割想画法不一样
	 */
	private int mOrientation = LinearLayoutManager.VERTICAL;

	/**
	 * item之间分割线的size，默认为1
	 */
	private int mItemSize = 1;

	/**
	 * 绘制item分割线的画笔，和设置其属性 来绘制个性分割线
	 */
	private Paint mPaint;

	/**
	 * 构造方法传入布局方向，不可不传
	 * @param context
	 */
	public ListItemDecoration(Context context) {
		init(context, LinearLayoutManager.VERTICAL, Color.BLUE, 1);
	}
	/**
      * 构造方法传入布局方向，不可不传
      * @param context
      * @param orientation
      */
     public ListItemDecoration(Context context, int orientation) {
    	 init(context, orientation, Color.BLUE, 1);
     }
     
     /**
      * 
      * @param context
      * @param orientation	分割线方向
      * @param itemColor	分割线颜色：Color.BLUE或者0xff0000ff
      * @param itemSize		分割线高度
      */
     public ListItemDecoration(Context context, int orientation, int itemColor, int itemSize) {
    	 init(context, orientation, itemColor, itemSize);
     }
     
     private void init(Context context, int orientation, int itemColor, int itemSize) {
    	 this.mOrientation = orientation;
    	 if(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL){
    		 throw new IllegalArgumentException("请传入正确的参数") ;
    	 }
    	 mItemSize = (int) TypedValue.applyDimension(itemSize, TypedValue.COMPLEX_UNIT_DIP,context.getResources().getDisplayMetrics());
    	 mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
    	 mPaint.setColor(itemColor);
    	 /*设置填充*/
    	 mPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		if (mOrientation == LinearLayoutManager.VERTICAL) {
			drawVertical(c, parent);
		} else {
			drawHorizontal(c, parent);
		}
	}

	/**
	 * 绘制纵向 item 分割线
	 * 
	 * @param canvas
	 * @param parent
	 */
	private void drawVertical(Canvas canvas, RecyclerView parent) {
		final int left = parent.getPaddingLeft();
		final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
		final int childSize = parent.getChildCount();
		for (int i = 0; i < childSize; i++) {
			View child = parent.getChildAt(i);
			RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
			int top = child.getBottom() + layoutParams.bottomMargin;
			int bottom = top;
			if (i < childSize - 1) {
				bottom = top + mItemSize;
			}
			canvas.drawRect(left, top, right, bottom, mPaint);
		}
	}

	/**
	 * 绘制横向 item 分割线
	 * 
	 * @param canvas
	 * @param parent
	 */
	private void drawHorizontal(Canvas canvas, RecyclerView parent) {
		final int top = parent.getPaddingTop();
		final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
		final int childSize = parent.getChildCount();
		for (int i = 0; i < childSize; i++) {
			final View child = parent.getChildAt(i);
			RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
			final int left = child.getRight() + layoutParams.rightMargin;
			final int right = left + mItemSize;
			canvas.drawRect(left, top, right, bottom, mPaint);
		}
	}

	/**
	 * 设置item分割线的size
	 * 
	 * @param outRect
	 * @param view
	 * @param parent
	 * @param state
	 */
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
		if (mOrientation == LinearLayoutManager.VERTICAL) {
			int childLayoutPosition = parent.getChildLayoutPosition(view);
			int childCount = parent.getAdapter().getItemCount();//总的数量
			System.out.println("===============" + childCount + "::::" + childLayoutPosition);
			//去除底部
			if (childLayoutPosition >= (childCount - 1)){
				outRect.set(0, 0, 0, 0);
			} else {
				outRect.set(0, 0, 0, mItemSize);
			}
		} else {
			outRect.set(0, 0, mItemSize, 0);
		}
	}
}
