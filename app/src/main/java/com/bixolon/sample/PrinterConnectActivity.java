package com.bixolon.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bixolon.sample.bottomsheet.ActionBottomDialogFragment;
import com.bixolon.sample.databinding.ActivityPrinterConnectBinding;

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
        Toast.makeText(this, "Éste botón abrirá el SDK ya con kotlin", Toast.LENGTH_SHORT).show();
    }
}
