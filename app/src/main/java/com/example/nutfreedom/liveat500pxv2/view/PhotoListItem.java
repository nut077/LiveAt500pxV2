package com.example.nutfreedom.liveat500pxv2.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.nutfreedom.liveat500pxv2.R;
import com.example.nutfreedom.liveat500pxv2.databinding.ListItemPhotoBinding;
import com.example.nutfreedom.liveat500pxv2.view.state.BundleSavedState;

public class PhotoListItem extends BaseCustomViewGroup {

    ListItemPhotoBinding binding;

    public PhotoListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public PhotoListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public PhotoListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public PhotoListItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = ListItemPhotoBinding.inflate(inflater, this, true);
    }

    private void initInstances() {
        // findViewById here
    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec); // width in px
        int height = (int) (width * 2 / 2.5); // ัตราส่วน 2 ต่อ 3
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        // Self
        setMeasuredDimension(width, height);
    }

    public void setTitleText(String text) {
        binding.tvTitle.setText(text);
    }

    public void setDescriptionsText(String text) {
        binding.tvDescriptions.setText(text);
    }

    public void setImageUrl(String url) {
        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivImg);

        // ถ้ารูปจาก url เดียวกัน แล้วใช้รูปนั้นหลายที่จะใช้ .diskCacheStrategy(DiskCacheStrategy.ALL) แต่ถ้าไม่ก็เอาออก
    }
}
