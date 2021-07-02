package com.bixolon.sample;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.bixolon.sample.bottomsheet.ActionBottomDialogFragment;

public class PrinterConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printer_connect);

        Button mBottton = findViewById(R.id.button);
        mBottton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });
    }

    public void showBottomSheet() {
        ActionBottomDialogFragment addPhotoBottomDialogFragment = ActionBottomDialogFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), ActionBottomDialogFragment.TAG);
    }

}
