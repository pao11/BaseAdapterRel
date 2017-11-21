package com.pao11.sample.adapter.lv;

import com.pao11.adapter.listview.ViewHolder;
import com.pao11.adapter.listview.base.ItemViewDelegate;
import com.pao11.sample.R;
import com.pao11.sample.bean.ChatMessage;

/**
 * Created by pao11 on 17/11/10.
 */
public class MsgSendItemDelagate implements ItemViewDelegate<ChatMessage>
{

    @Override
    public int getItemViewLayoutId()
    {
        return R.layout.main_chat_send_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position)
    {
        return !item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage, int position)
    {
        holder.setText(R.id.chat_send_content, chatMessage.getContent());
        holder.setText(R.id.chat_send_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon());
    }
}
