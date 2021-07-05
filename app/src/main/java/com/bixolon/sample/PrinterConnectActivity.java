package com.bixolon.sample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.bixolon.sample.bottomsheet.ActionBottomDialogFragment;
import com.bixolon.sample.databinding.ActivityPrinterConnectBinding;
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.SDKBottomDialogFragment;

public class PrinterConnectActivity extends AppCompatActivity {

    private ActivityPrinterConnectBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityPrinterConnectBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.button.setOnClickListener(v -> showBottomSheet());
        mBinding.btnOpenSDK.setOnClickListener(v -> openSDKPrinter());
    }

    public void showBottomSheet() {
        ActionBottomDialogFragment addPhotoBottomDialogFragment = ActionBottomDialogFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), ActionBottomDialogFragment.TAG);
    }

    public void openSDKPrinter() {
        SDKBottomDialogFragment openBottomSheetSDK = SDKBottomDialogFragment.Companion.newInstance();
        openBottomSheetSDK.show(getSupportFragmentManager(), SDKBottomDialogFragment.TAG);
    }
}
