package com.example.midterm_proj.StudioTool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.midterm_proj.R;
import com.example.midterm_proj.ui.main.ChangeBitmapHandler;

public class CropTool extends StudioTool {

    private CropHandler mCropHander;

    public interface CropHandler {
        void handleCrop();
        Bitmap getBitmap();
    }

    public CropTool (StudioToolManager toolManager, CropHandler cropHandler) {
        super(toolManager.mInflater, toolManager.mToolOptionsView, "Crop", AppCompatResources.getDrawable(toolManager.mContext, R.mipmap.crop));
        mChangeBitmapHandler = toolManager.mChangeBitmapHandler;
        mToolOptions = (LinearLayout) mInflater.inflate(R.layout.crop_tool_options, null);
        mCropHander = cropHandler;
    }

    public void updateBitmap () {
//        Do sth, then
        mCropHander.handleCrop();
        mChangeBitmapHandler.changeBitmap(mCropHander.getBitmap(), false);
    }
}
