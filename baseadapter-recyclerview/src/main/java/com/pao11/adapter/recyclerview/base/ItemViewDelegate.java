package com.pao11.adapter.recyclerview.base;


/**
 * Created by pao11 on 17/11/10.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
