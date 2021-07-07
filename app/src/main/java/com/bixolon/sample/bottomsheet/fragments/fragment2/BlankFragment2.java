package com.bixolon.sample.bottomsheet.fragments.fragment2;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bixolon.sample.MainActivity;
import com.bixolon.sample.PrinterConnectActivity;
import com.bixolon.sample.R;
import com.bixolon.sample.databinding.FragmentBlank2Binding;
import com.bxl.config.editor.BXLConfigLoader;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.bixolon.sample.bottomsheet.ActionBottomDialogFragment.m_bluetoothAdapter;
import static com.bixolon.sample.bottomsheet.ActionBottomDialogFragment.m_paredDevices;

public class BlankFragment2 extends Fragment implements ItemListener {

    private FragmentBlank2Binding mBinding;

    private final int REQUEST_PERMISSION = 0;

    private int portType = BXLConfigLoader.DEVICE_BUS_BLUETOOTH;
    private String SPP_R200III = "SPP-R200III";
    private String address = "";

    private CheckBox checkBoxAsyncMode;
    private ProgressBar mProgressLarge;

    private TextView txtModelo;

    private RecyclerView rvDevice;
    RecyclerAdapter adapterDevice;
    private ArrayList<BluetoothDevice> listDevice = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentBlank2Binding.inflate(getLayoutInflater());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkBoxAsyncMode = view.findViewById(R.id.checkBoxAsyncMode);

        mProgressLarge = view.findViewById(R.id.progressBar1);
        mProgressLarge.setVisibility(ProgressBar.GONE);

        txtModelo = view.findViewById(R.id.txtModel);
        txtModelo.setText(getString(R.string.txt_modelo, SPP_R200III));


        /**Mostramos la lista en cuanto abrimos la app*/
        setPairedDevices();

        /**Llenamos este array de los dispositivos que obtuvimos*/
        rvDevice = view.findViewById(R.id.rvPairedDevices);
        adapterDevice = new RecyclerAdapter(listDevice, R.layout.card_item_device, this);
        rvDevice.setAdapter(adapterDevice);

        mBinding.btnOpenConfig.setOnClickListener(v -> {
            Intent i = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(i);
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                }
            }
        }
    }

    /**
     * Jalamos los dispositivos vinculados que tiene el dispositivo
     */
    private void setPairedDevices() {
        listDevice.clear();

        m_paredDevices = m_bluetoothAdapter.getBondedDevices();

        if (m_paredDevices != null) {

            for (BluetoothDevice device : m_paredDevices) {
                if (device.getBluetoothClass().getMajorDeviceClass() == 1536) {
                    listDevice.add(device);
                }
            }
        }

        if (adapterDevice != null) {
            adapterDevice.notifyDataSetChanged();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(getContext(), "permission denied", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public final Handler mHandler = new Handler(new Handler.Callback() {
        @SuppressWarnings("unchecked")
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mProgressLarge.setVisibility(ProgressBar.VISIBLE);
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    break;
                case 1:
                    String data = (String) msg.obj;
                    if (data != null && data.length() > 0) {
                        Toast.makeText(getContext(), data, Toast.LENGTH_LONG).show();
                    }
                    mProgressLarge.setVisibility(ProgressBar.GONE);
                    getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    break;
            }
            return false;
        }
    });

    public void goTOImageFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_blankFragment2_to_imageFragment);
    }

    @Override
    public void onCLickListenerInfo(BluetoothDevice device, int position) {
        buttonPopupwindow(requireView(), device.getName(), device.getAddress());
    }

    @Override
    public void onCLickListenerCard(BluetoothDevice device, int position) {
        /**Guardamos el address*/
        address = device.getAddress();
        mHandler.obtainMessage(0).sendToTarget();

        mProgressLarge.setVisibility(View.VISIBLE);
        if (PrinterConnectActivity.getPrinterInstance().printerOpen(portType, SPP_R200III, address, checkBoxAsyncMode.isChecked())) {
            goTOImageFragment(requireView());
        } else {
            mHandler.obtainMessage(1, 0, 0, "Fail to printer open!!").sendToTarget();
        }

//        new Thread(() -> {
//            if (PrinterConnectActivity.getPrinterInstance().printerOpen(portType, SPP_R200III, address, checkBoxAsyncMode.isChecked())) {
//                goTOImageFragment(requireView());
//                Toast.makeText(getContext(), "click ok", Toast.LENGTH_SHORT).show();
//            } else {
//                mHandler.obtainMessage(1, 0, 0, "Fail to printer open!!").sendToTarget();
//            }
//        }).start();

    }

    public void buttonPopupwindow(View view, String name, String address) {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View viewPopupwindow = layoutInflater.inflate(R.layout.card_item_device_detail, null);
        final PopupWindow popupWindow = new PopupWindow(viewPopupwindow, 900, 500, true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        TextView txtName;
        TextView txtAddress;

        txtName = viewPopupwindow.findViewById(R.id.txtDeviceName);
        txtAddress = viewPopupwindow.findViewById(R.id.txtDeviceMac);

        txtName.setText(name);
        txtAddress.setText(address);

        viewPopupwindow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

}