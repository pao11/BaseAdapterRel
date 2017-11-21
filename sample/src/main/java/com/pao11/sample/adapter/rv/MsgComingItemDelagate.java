package com.pao11.sample.adapter.rv;

import com.pao11.adapter.recyclerview.base.ItemViewDelegate;
import com.pao11.adapter.recyclerview.base.ViewHolder;
import com.pao11.sample.R;
import com.pao11.sample.bean.ChatMessage;

/**
 * Created by pao11 on 17/11/10.
 */
public class MsgComingItemDelagate implements ItemViewDelegate<ChatMessage>
{

    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.main_chat_from_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position)
    {
        return item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage, int position)
    {
        holder.setText(R.id.chat_from_content, chatMessage.getContent());
        holder.setText(R.id.chat_from_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
    }
}
