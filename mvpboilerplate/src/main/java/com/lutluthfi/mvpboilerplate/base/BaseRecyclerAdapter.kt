package com.lutluthfi.mvpboilerplate.base

import android.support.v7.widget.RecyclerView
import android.view.View

import java.util.ArrayList

abstract class BaseRecyclerAdapter<T, VH : BaseRecyclerAdapter.PlateBaseViewHolder<*>> :
        RecyclerView.Adapter<VH>() {

    var mItems: MutableList<T> = ArrayList()
        private set

    protected var mPageIndex = 0
        private set

    protected var mListener: OnItemClickListener<T>? = null
        private set

    fun setOnClickListener(listener: OnItemClickListener<T>) {
        mListener = listener
    }

    fun setItems(items: MutableList<T>?) {
        items?.let {
            this.mItems = it
            notifyDataSetChanged()
        }
    }

    fun setItems(item: T, index: Int) {
        if (mItems.size > index) {
            mItems[index] = item
            notifyItemChanged(index)
        }
    }

    fun getItem(position: Int): T? {
        return if (mItems.size > position) mItems[position]
        else null
    }

    fun removeItem(index: Int) {
        if (mItems.size > index) {
            mItems.removeAt(index)
            notifyItemRemoved(index)
            notifyItemChanged(index, mItems.size)
        }
    }

    fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }

    fun addItems(items: List<T>, pageIndex: Int) {
        mPageIndex = pageIndex
        mItems.addAll(items)
        notifyItemInserted(mItems.size)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            mListener?.run {
                onItemClickListener(holder.itemView, mItems[position], position)
            }
        }
        holder.itemView.setOnLongClickListener {
            mListener != null && mListener!!.onLongItemClickListener(holder.itemView, mItems[position], position)
        }
    }

    override fun getItemCount(): Int = mItems.size

    abstract class PlateBaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        protected var mItem: T? = null

        protected abstract fun onBind()
    }

    interface OnItemClickListener<T> {
        fun onItemClickListener(view: View, item: T, position: Int)

        fun onLongItemClickListener(view: View, item: T, position: Int): Boolean
    }
}
