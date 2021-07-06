package com.bixolon.sample.bottomsheet.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bixolon.sample.MainActivity;
import com.bixolon.sample.R;
import com.bixolon.sample.databinding.FragmentBlank3Binding;

public class BlankFragment3 extends Fragment implements SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener{

    private static FragmentBlank3Binding mBinding;

    private String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    private int REQUEST_CODE_ACTION_PICK = 1;

    private LinearLayout layoutStartPage;
    private LinearLayout layoutEndPage;

    private RadioGroup radioGroupPrintingType;
    private EditText editTextWidth;
    private EditText editTextStartPage;
    private EditText editTextEndPage;
    private TextView textViewBrightness;
    private TextView textViewFilePath;
    private TextView deviceMessagesTextView;
    private SeekBar seekBarBrightness;

    private int spinnerAlignment = 0;
    private int spinnerDither = 0;
    private int spinnerCompress = 0;

    private String[] fileItems = null;

    //for the bitmap
    FrameLayout myView;
    Bitmap myBitmap;

    public static BlankFragment3 newInstance() {
        BlankFragment3 fragment3 = new BlankFragment3();
        Bundle args = new Bundle();
        fragment3.setArguments(args);
        return fragment3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentBlank3Binding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //todo inflando vista para imprimir bitmap
        View inflatedFrame = getLayoutInflater().inflate(R.layout.ticket_sdk, null);
        FrameLayout frameLayout = inflatedFrame.findViewById(R.id.ticketFrameLayout) ;
        frameLayout.setDrawingCacheEnabled(true);
        frameLayout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        frameLayout.layout(0, 0, frameLayout.getMeasuredWidth(), frameLayout.getMeasuredHeight());
        frameLayout.buildDrawingCache(true);
        myBitmap = frameLayout.getDrawingCache(); /**bitmap*/

        mBinding.btnPrint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
                MainActivity.getPrinterInstance().printImage(myBitmap, 375, 0, 0, 0, 50);
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void setDeviceLog(String data) {
        mHandler.obtainMessage(0, 0, 0, data).sendToTarget();
    }

    public final Handler mHandler = new Handler(new Handler.Callback() {
        @SuppressWarnings("unchecked")
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    deviceMessagesTextView.append((String) msg.obj + "\n");

                    Layout layout = deviceMessagesTextView.getLayout();
                    if (layout != null) {
                        int y = layout.getLineTop(
                                deviceMessagesTextView.getLineCount()) - deviceMessagesTextView.getHeight();
                        if (y > 0) {
                            deviceMessagesTextView.scrollTo(0, y);
                            deviceMessagesTextView.invalidate();
                        }
                    }
                    break;
            }
            return false;
        }
    });
}