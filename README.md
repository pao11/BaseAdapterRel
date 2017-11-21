[![](https://jitpack.io/v/pao11/BaseAdapterRel.svg)](https://jitpack.io/#pao11/BaseAdapterRel)
[![Travis](https://img.shields.io/badge/Gradle-2.3.1-brightgreen.svg)]()

# base-adapter
Android 万能的Adapter for ListView,RecyclerView,GridView等，支持多种Item类型的情况。


## 引入

### ForRecyclerView

```
compile 'com.github.pao11:BaseAdapterRel:v1.0.0'
```


## 使用

##（1）简单的数据绑定(ListView与其使用方式一致)

首先看我们最常用的单种Item的书写方式：

```
mRecyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.item_list, mDatas)
{
    @Override
    public void convert(ViewHolder holder, String s)
    {
        holder.setText(R.id.id_item_list_title, s);
    }
});
```
在convert方法中完成数据、事件绑定即可。


只需要简单的将Adapter继承CommonAdapter，复写convert方法即可。省去了自己编写ViewHolder等大量的重复的代码。

* 可以通过holder.getView(id)拿到任何控件。
* ViewHolder中封装了大量的常用的方法，比如holder.setText(id,text)，holder.setOnClickListener(id,listener)等，可以支持使用。


##（2）多种ItemViewType(ListView与其使用方式一致)

```
MultiItemTypeAdapter adapter = new MultiItemTypeAdapter(this,mDatas);
adapter.addItemViewDelegate(new MsgSendItemDelagate());
adapter.addItemViewDelegate(new MsgComingItemDelagate());
```

每种Item类型对应一个ItemViewDelegete，例如：

```
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
```

##(3) 添加HeaderView、FooterView

```
mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(mAdapter);

TextView t1 = new TextView(this);
t1.setText("Header 1");
TextView t2 = new TextView(this);
t2.setText("Header 2");
mHeaderAndFooterWrapper.addHeaderView(t1);
mHeaderAndFooterWrapper.addHeaderView(t2);

mRecyclerView.setAdapter(mHeaderAndFooterWrapper);
mHeaderAndFooterWrapper.notifyDataSetChanged();
```

##(4) 添加LoadMore

```
mLoadMoreWrapper = new LoadMoreWrapper(mOriginAdapter);
mLoadMoreWrapper.setLoadMoreView(R.layout.default_loading);
mLoadMoreWrapper.setOnLoadMoreListener(new LoadMoreWrapper.OnLoadMoreListener()
{
    @Override
    public void onLoadMoreRequested()
    {
    }
});

mRecyclerView.setAdapter(mLoadMoreWrapper);

```
直接将原本的adapter传入，初始化一个LoadMoreWrapper对象，然后调用相关API即可。

##(5)添加EmptyView

```
mEmptyWrapper = new EmptyWrapper(mAdapter);
mEmptyWrapper.setEmptyView(R.layout.empty_view);

mRecyclerView.setAdapter(mEmptyWrapper );

```

直接将原本的adapter传入，初始化一个EmptyWrapper对象，然后调用相关API即可。


支持链式添加多种功能，示例代码：

```
mAdapter = new EmptyViewWrapper(
	new LoadMoreWrapper(
	new HeaderAndFooterWrapper(mOriginAdapter)));
```
##更新记录

 **v1.0.0**　`2017.11.21`　发布第一个版本--SDK VERSION 23.2.0

## License

```
Copyright 2017 pao11

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

