package com.example.test_sport;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.test_sport.databinding.CustomLoginPopup2Binding;
import com.example.test_sport.databinding.CustomLoginPopupBinding;
import com.example.test_sport.databinding.FragmentPlaningBinding;

import org.jetbrains.annotations.NotNull;

import Fragment.PlaningFragment;
import kotlin.Unit;

public class CustomLoginPopup2 extends Dialog {

    private CustomLoginPopup2Binding mBinding;
    private Context context;


    public CustomLoginPopup2(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = CustomLoginPopup2Binding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getWindow().setContentView(mBinding.getRoot(), new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mBinding.emailCoach.setTextColor(Color.parseColor("#152478"));




    }
}