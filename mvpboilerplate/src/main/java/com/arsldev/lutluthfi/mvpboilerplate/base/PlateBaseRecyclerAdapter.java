package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class PlateBaseRecyclerAdapter<T, VH extends PlateBaseRecyclerAdapter.PlateBaseViewHolder> extends RecyclerView.Adapter<VH>{
    public interface OnItemClickListener<T> {
        void onItemClickListener(View view, T item, int position);
    }

    private int mPageIndex = 1;
    private List<T> mItems;
    private OnItemClickListener mListener;

    public PlateBaseRecyclerAdapter() {
        mItems = new ArrayList<>();
    }

    protected void setOnClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    protected void setItems(List<T> mItems) {
        if (mItems != null) this.mItems = mItems;
        notifyDataSetChanged();
    }

    protected void setItems(T item, int index) {
        if (this.mItems != null && this.mItems.size() > index) {
            this.mItems.set(index, item);
            notifyItemChanged(index);
        }
    }

    protected T getItem(int position) {
        if (this.mItems != null && this.mItems.size() > position) return this.mItems.get(position);
        else return null;
    }

    protected void removeItem(int index) {
        if (this.mItems != null && this.mItems.size() > index) {
            this.mItems.remove(index);
            notifyItemRemoved(index);
            notifyItemChanged(index, this.mItems.size());
        }
    }

    protected void clear() {
        if (this.mItems != null) {
            this.mItems.clear();
            notifyDataSetChanged();
        }
    }

    protected void addItems(List<T> mItems, int mPageIndex) {
        this.mPageIndex = mPageIndex;
        this.mItems.addAll(mItems);
        notifyItemInserted(this.mItems.size());
    }

    protected int getPageIndex() {
        return mPageIndex;
    }

    protected List<T> getItems() {
        return mItems;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public abstract class PlateBaseViewHolder<I> extends RecyclerView.ViewHolder implements View.OnClickListener{

        private I mItem;
        private int mCurrentPosition;

        public PlateBaseViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        protected void onBind(int mCurrentPosition){
            this.mCurrentPosition = mCurrentPosition;
        }

        protected int getCurrentPosition() {
            return mCurrentPosition;
        }

        @SuppressWarnings("unchecked") @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClickListener(this.itemView, this.mItem, getAdapterPosition());
                notifyDataSetChanged();
            }
        }
    }
}
