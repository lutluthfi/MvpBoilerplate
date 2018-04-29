package com.arsldev.lutluthfi.mvpboilerplate.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class PlateBaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private int mCurrentPosition;

    public PlateBaseViewHolder(View itemView) {
        super(itemView);
    }

    public void onBind(int mCurrentPosition){
        this.mCurrentPosition = mCurrentPosition;
    }

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}
