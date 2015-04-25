package com.ps.citizen3.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ps.citizen3.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {
    private static final int HEADER_ROW = 0;
    private static final int ITEM_ROW = 1;

    private List<String> tabs;

    public DrawerAdapter(List<String> tabs) {
        this.tabs = tabs;
    }

    @Override
    public DrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == ITEM_ROW) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_item_row, viewGroup, false);
            return new ViewHolder(v, viewType);
        } else if (viewType == HEADER_ROW) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_header, viewGroup, false);
            return new ViewHolder(v, viewType);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(DrawerAdapter.ViewHolder viewHolder, int i) {
        if (viewHolder.viewType == ITEM_ROW) {
            String tabText = tabs.get(i-1);
            viewHolder.textView.setText(tabText);
            switch (i-1) {
                case 0:
                    viewHolder.imageView.setImageResource(R.mipmap.ic_account_multiple_grey);
                    break;
                case 1:
                    viewHolder.imageView.setImageResource(R.mipmap.ic_bank_grey);
                    break;
                case 2:
                    viewHolder.imageView.setImageResource(R.mipmap.ic_book_grey);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return tabs.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER_ROW;
        return ITEM_ROW;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected int viewType;

        protected TextView textView;
        protected ImageView imageView;

        public ViewHolder(View v, int viewType) {
            super(v);
            this.viewType = viewType;

            if (viewType == ITEM_ROW) {
                textView = (TextView)v.findViewById(R.id.drawer_row_text);
                imageView = (ImageView)v.findViewById(R.id.drawer_row_icon);
            } else if (viewType == HEADER_ROW) {

            }
        }
    }
}
