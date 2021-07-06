package com.bixolon.sample.bottomsheet;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bixolon.sample.databinding.BottomSheetDialogLayoutBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.Set;

public class ActionBottomDialogFragment extends BottomSheetDialogFragment {

    private BottomSheetDialogLayoutBinding mBinding;

    /**Variables globales*/
    public static String TAG = "ActionBottomDialog";
    public static BluetoothAdapter m_bluetoothAdapter = null;
    public static Set<BluetoothDevice> m_paredDevices = null;
    public static int REQUEST_ENABLE_BLUETOOTH = 1;

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

        mBinding.btnClose.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!m_bluetoothAdapter.isEnabled()) {
//            switch () {
//
//            }
        }
    }
}