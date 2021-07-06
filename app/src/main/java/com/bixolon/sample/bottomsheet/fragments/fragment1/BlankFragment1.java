package com.bixolon.sample.bottomsheet.fragments.fragment1;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bixolon.sample.R;
import com.bixolon.sample.databinding.FragmentBlank1Binding;
import static com.bixolon.sample.bottomsheet.ActionBottomDialogFragment.REQUEST_ENABLE_BLUETOOTH;
import static com.bixolon.sample.bottomsheet.ActionBottomDialogFragment.m_bluetoothAdapter;

public class BlankFragment1 extends Fragment {

    private static FragmentBlank1Binding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentBlank1Binding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (m_bluetoothAdapter == null) {
            Toast.makeText(getContext(), "Este dispositivo no soporta bluetooth", Toast.LENGTH_SHORT).show();
        }

        if (m_bluetoothAdapter.isEnabled()) {
            Navigation.findNavController(view).navigate(R.id.action_blankFragment1_to_blankFragment2);
        }

        mBinding.btnActivateBth.setOnClickListener(v -> {
            if (!m_bluetoothAdapter.isEnabled()) {
                Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH);
            }
        });
    }
}