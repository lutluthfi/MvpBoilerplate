package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class PlateBaseRecyclerAdapter<T, VH extends PlateBaseRecyclerAdapter.PlateBaseViewHolder> extends RecyclerView.Adapter<VH> {
    public interface OnItemClickListener<T> {
        void onItemClickListener(View view, T item, int position);

        boolean onLongItemClickListener(View view, T item, int position);
    }

    private static OnItemClickListener sListener;
    private int mPageIndex = 1;
    private List<T> mItems;

    public PlateBaseRecyclerAdapter() {
        mItems = new ArrayList<>();
    }

    public void setOnClickListener(OnItemClickListener listener) {
        sListener = listener;
    }

    public void setItems(List<T> mItems) {
        if (mItems != null) this.mItems = mItems;
        notifyDataSetChanged();
    }

    public void setItems(T item, int index) {
        if (this.mItems != null && this.mItems.size() > index) {
            this.mItems.set(index, item);
            notifyItemChanged(index);
        }
    }

    public T getItem(int position) {
        if (this.mItems != null && this.mItems.size() > position) return this.mItems.get(position);
        else return null;
    }

    public void removeItem(int index) {
        if (this.mItems != null && this.mItems.size() > index) {
            this.mItems.remove(index);
            notifyItemRemoved(index);
            notifyItemChanged(index, this.mItems.size());
        }
    }

    public void clear() {
        if (this.mItems != null) {
            this.mItems.clear();
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> mItems, int mPageIndex) {
        this.mPageIndex = mPageIndex;
        this.mItems.addAll(mItems);
        notifyItemInserted(this.mItems.size());
    }

    public int getPageIndex() {
        return mPageIndex;
    }

    public List<T> getItems() {
        return mItems;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ((PlateBaseViewHolder) holder).mItem = mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static abstract class PlateBaseViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private T mItem;

        public PlateBaseViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        protected abstract void onBind();

        @SuppressWarnings("unchecked")
        @Override
        public void onClick(View v) {
            if (sListener != null) {
                sListener.onItemClickListener(this.itemView, mItem, getAdapterPosition());
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean onLongClick(View view) {
            return sListener != null && sListener.onLongItemClickListener(this.itemView, mItem, getAdapterPosition());
        }
    }
}
