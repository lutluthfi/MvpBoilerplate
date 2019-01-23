package com.lutluthfi.mvpboilerplate.base

import android.support.v7.widget.RecyclerView
import android.view.View

import java.util.ArrayList

abstract class PlateBaseRecyclerAdapter<T, VH : PlateBaseRecyclerAdapter.PlateBaseViewHolder<*>> :
        RecyclerView.Adapter<VH>() {

    private var listener: OnItemClickListener<T>? = null
    var pageIndex = 1
        private set
    private var items: MutableList<T> = ArrayList()

    fun setOnClickListener(listener: OnItemClickListener<T>) {
        this.listener = listener
    }

    fun setItems(items: MutableList<T>?) {
        items?.let {
            this.items = it
            notifyDataSetChanged()
        }
    }

    fun setItems(item: T, index: Int) {
        if (this.items.size > index) {
            this.items[index] = item
            notifyItemChanged(index)
        }
    }

    fun getItem(position: Int): T? {
        return if (this.items.size > position) this.items[position]
        else null
    }

    fun removeItem(index: Int) {
        if (this.items.size > index) {
            this.items.removeAt(index)
            notifyItemRemoved(index)
            notifyItemChanged(index, this.items.size)
        }
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    fun addItems(items: List<T>, pageIndex: Int) {
        this.pageIndex = pageIndex
        this.items.addAll(items)
        notifyItemInserted(this.items.size)
    }

    fun getItems(): List<T>? {
        return items
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener { _ ->
            listener?.run {
                onItemClickListener(holder.itemView, items[position], position)
            }
        }
        holder.itemView.setOnLongClickListener { _ ->
            listener != null && listener!!.onLongItemClickListener(holder.itemView, items[position], position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    abstract class PlateBaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val item: T? = null

        protected abstract fun onBind()
    }

    interface OnItemClickListener<T> {
        fun onItemClickListener(view: View, item: T, position: Int)

        fun onLongItemClickListener(view: View, item: T, position: Int): Boolean
    }
}
