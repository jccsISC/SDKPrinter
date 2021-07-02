package com.bixolon.sample.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bixolon.sample.databinding.BottomSheetDialogLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetDialogLayoutBinding mBinding;

    public static final String TAG = "ActionBottomDialog";

    public static ActionBottomDialogFragment newInstance() {
        return new ActionBottomDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = BottomSheetDialogLayoutBinding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

}