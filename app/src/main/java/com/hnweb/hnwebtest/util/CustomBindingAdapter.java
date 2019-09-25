package com.hnweb.hnwebtest.util;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.hnweb.hnwebtest.R;

public class CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("loadImage")
    public static void loadImage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext()).load(imageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);
    }
}
