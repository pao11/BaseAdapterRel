package com.pao11.adapter.listview.base;

import com.pao11.adapter.listview.ViewHolder;


/**
 * Created by pao11 on 17/11/10.
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
