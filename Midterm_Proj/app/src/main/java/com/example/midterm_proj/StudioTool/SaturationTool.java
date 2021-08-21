package com.example.midterm_proj.StudioTool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.midterm_proj.R;
import com.example.midterm_proj.ui.main.ChangeBitmapHandler;
import com.google.android.material.slider.RangeSlider;

import org.jetbrains.annotations.NotNull;

public class SaturationTool extends StudioTool {

    private SaturationHandler mSaturationHander;
    private int value = 0;

    public interface SaturationHandler {
        void saturationFilter(int value);
        Bitmap getBitmap();
    }
    TextView debug;
    public SaturationTool (StudioToolManager toolManager, SaturationHandler SaturationHandler) {
        super(toolManager.mInflater, toolManager.mToolOptionsView, "Saturation", AppCompatResources.getDrawable(toolManager.mContext, R.mipmap.saturation));
        mChangeBitmapHandler = toolManager.mChangeBitmapHandler;
        mToolOptions = (LinearLayout) mInflater.inflate(R.layout.saturation_tool_options, null);
        mSaturationHander = SaturationHandler;
        debug = mToolOptions.findViewById(R.id.saturationValueDebug);
        initializeToolOptionsUI();
    }

    private void initializeToolOptionsUI() {
        RangeSlider slider = mToolOptions.findViewById(R.id.saturationValueSlider);
        slider.setValueFrom(0);
        slider.setValueTo(255);
        slider.setStepSize(1);
        slider.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull @NotNull RangeSlider slider, float value, boolean fromUser) {
                if (fromUser) {
//                    Fucking lag :/
                    value = Float.valueOf(value).intValue();
                    debug.setText("" + value);
                    Log.d("SATURATION", "" + value);
                    updateBitmap();
                }
            }
        });
    }

    public void updateBitmap () {
//        Do sth, then
        mSaturationHander.saturationFilter(value);
        mChangeBitmapHandler.changeBitmap(mSaturationHander.getBitmap());
    }
}