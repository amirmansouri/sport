package com.example.test_sport;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.test_sport.databinding.CustomLoginPopupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomLoginPopup extends Dialog {
    private static final String TAG = "CustomLoginPopup";
    private CustomLoginPopupBinding mBinding;
    private Context context;
    private EditText cm ;


    public CustomLoginPopup(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = CustomLoginPopupBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
//mBinding.send.setOnClickListener(new View.OnClickListener() {
//
//    @Override
//    public void onClick(View view) {
//     // mBinding.cm.getText();
//
//        mBinding.cm.findViewById(R.id.cm);
//mBinding.cm
//        return builder.create
//    }
//});

        getWindow().setContentView(mBinding.getRoot(), new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        mBinding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(context, mBinding.cm.getText(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
