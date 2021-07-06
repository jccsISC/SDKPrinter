package com.bixolon.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bixolon.sample.PrinterControl.BixolonPrinter;
import com.bixolon.sample.bottomsheet.ActionBottomDialogFragment;
import com.bixolon.sample.databinding.ActivityPrinterConnectBinding;
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.BottomDialogFragmentSDK;

public class PrinterConnectActivity extends AppCompatActivity {

    /**Instancia blobarl bxlPrinter*/
    private static BixolonPrinter bxlPrinter = null;

    private ActivityPrinterConnectBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityPrinterConnectBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        /**Inicializa bxlPrinter es global*/
        bxlPrinter = new BixolonPrinter(getApplicationContext());

        mBinding.button.setOnClickListener(this::showBottomSheet);
        mBinding.btnOpenSDK.setOnClickListener(this::openSDKPrinter);
    }

    public void showBottomSheet(View view) {
        ActionBottomDialogFragment addPhotoBottomDialogFragment = ActionBottomDialogFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), ActionBottomDialogFragment.TAG);
    }

    public void openSDKPrinter(View view) {
        BottomDialogFragmentSDK openBottomSheetSDK = BottomDialogFragmentSDK.Companion.newInstance();
        openBottomSheetSDK.show(getSupportFragmentManager(), BottomDialogFragmentSDK.TAG);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        getPrinterInstance().printerClose();
    }

    /**retornamos la instancia*/
    public static BixolonPrinter getPrinterInstance()
    {
        return bxlPrinter;
    }
}
