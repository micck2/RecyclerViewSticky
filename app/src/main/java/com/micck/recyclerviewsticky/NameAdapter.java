package com.micck.recyclerviewsticky;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lilin on 2018/3/27 10:26
 */

public class NameAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Name> mList = new ArrayList<>();

    public NameAdapter(List<Name> list) {
        mList.clear();
        mList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new ContentVH(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.adapter_item_content,parent,false));
        }
        return new TitleVH(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.adapter_item_title,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Name name = mList.get(position);

        if (holder instanceof ContentVH) {
            ((ContentVH) holder).bindData(name);
        } else {
            ((TitleVH) holder).bindData(name);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).itemType;
    }

    private static class TitleVH extends RecyclerView.ViewHolder {
        TextView tv_title;

        public TitleVH(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            itemView.setTag(true);
        }

        public void bindData(Name name) {
            tv_title.setText(name.name);
        }
    }

    private static class ContentVH extends RecyclerView.ViewHolder {
        TextView tv_content;

        public ContentVH(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            itemView.setTag(false);
        }

        public void bindData(Name name) {
            tv_content.setText(name.name);
        }
    }
}
