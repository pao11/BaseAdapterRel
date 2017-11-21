package com.pao11.sample.adapter.lv;

import android.content.Context;

import com.pao11.adapter.listview.MultiItemTypeAdapter;
import com.pao11.sample.bean.ChatMessage;

import java.util.List;

/**
 * Created by pao11 on 17/11/10.
 */
public class ChatAdapter extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapter(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }

}
