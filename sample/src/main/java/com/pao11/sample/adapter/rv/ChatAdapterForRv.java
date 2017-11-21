package com.pao11.sample.adapter.rv;

import android.content.Context;

import com.pao11.adapter.recyclerview.MultiItemTypeAdapter;
import com.pao11.sample.bean.ChatMessage;

import java.util.List;

/**
 * Created by pao11 on 17/11/10.
 */
public class ChatAdapterForRv extends MultiItemTypeAdapter<ChatMessage>
{
    public ChatAdapterForRv(Context context, List<ChatMessage> datas)
    {
        super(context, datas);

        addItemViewDelegate(new MsgSendItemDelagate());
        addItemViewDelegate(new MsgComingItemDelagate());
    }
}
