package com.example.midterm_proj.StudioTool;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.midterm_proj.ui.main.ChangeBitmapHandler;
import com.example.midterm_proj.ui.main.StudioFragmentViewModel;

public class StudioToolManager {

    private StudioFragmentViewModel mViewModel;
    private LinearLayout mToolBtnView;
    private LinearLayout mCanvasView;
    private String currentTool = "";

    public LinearLayout mToolOptionsView;
    public Context mContext;
    public LayoutInflater mInflater;
    public ChangeBitmapHandler mChangeBitmapHandler;

//    Tools
    private BrightTool mBrightTool;
    private BrushTool mBrushTool;
    private ContrastTool mContrastTool;
    private CropTool mCropTool;
    private ExposureTool mExposureTool;
    private SaturationTool mSaturationTool;
    private SharpenTool mSharpenTool;
    private TextTool mTextTool;

    public StudioToolManager (LayoutInflater inflater, LinearLayout canvasView, LinearLayout toolBtnView, LinearLayout toolOptionsview, Context context, ChangeBitmapHandler changeBitmapHandler, StudioFragmentViewModel viewModel) {
        mInflater = inflater;
        mCanvasView = canvasView;
        mToolBtnView = toolBtnView;
        mToolOptionsView = toolOptionsview;
        mContext = context;
        mChangeBitmapHandler = changeBitmapHandler;
        mViewModel = viewModel;
        initialize();
    }

    private void initialize() {
        currentTool = "";
        mBrightTool = new BrightTool(this, (BrightTool.BrightHandler) mViewModel);
        mBrushTool = new BrushTool(this, (BrushTool.BrushHandler) mViewModel);
        mContrastTool = new ContrastTool(this, (ContrastTool.ContrastHandler) mViewModel);
        mCropTool = new CropTool(this, (CropTool.CropHandler) mViewModel);
        mExposureTool = new ExposureTool(this, (ExposureTool.ExposureHandler) mViewModel);
        mSaturationTool = new SaturationTool(this, (SaturationTool.SaturationHandler) mViewModel);
        mSharpenTool = new SharpenTool(this, (SharpenTool.SharpenHandler) mViewModel);
        mTextTool = new TextTool(this, (TextTool.TextHandler) mViewModel);
//        Add more tools here

//        Attach to toolBtnView
        mToolBtnView.removeAllViews();
        mToolBtnView.addView(((StudioTool) mBrightTool).inflateButton());
        mToolBtnView.addView(((StudioTool) mBrushTool).inflateButton());
        mToolBtnView.addView(((StudioTool) mContrastTool).inflateButton());
        mToolBtnView.addView(((StudioTool) mCropTool).inflateButton());
        mToolBtnView.addView(((StudioTool) mExposureTool).inflateButton());
        mToolBtnView.addView(((StudioTool) mSaturationTool).inflateButton());
        mToolBtnView.addView(((StudioTool) mSharpenTool).inflateButton());
        mToolBtnView.addView(((StudioTool) mTextTool).inflateButton());
    }

    public void chooseTool (String toolName) {
        switch (toolName) {
            case "crop": {
                currentTool = toolName;
                mCropTool.choose();
            }
            default: {
//                Do nothing
            }
        }
    }

    public StudioTool getCurrentTool () {
        switch (currentTool) {
            case "crop": {
                return mCropTool;
            }
            default: {
//                Some error
                return null;
            }
        }
    }

    public void cancel() {
        currentTool = "";
        mToolOptionsView.removeAllViews();
    }
}