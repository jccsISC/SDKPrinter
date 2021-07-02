package com.bixolon.sample.bottomsheet.fragments;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bixolon.sample.MainActivity;
import com.bixolon.sample.R;
import com.bixolon.sample.databinding.FragmentBlank2Binding;
import com.bxl.config.editor.BXLConfigLoader;

import java.util.ArrayList;
import java.util.Set;

public class BlankFragment2 extends Fragment implements AdapterView.OnItemClickListener{

    private FragmentBlank2Binding mBinding;

    private final int REQUEST_PERMISSION = 0;
    private final String DEVICE_ADDRESS_START = " (";
    private final String DEVICE_ADDRESS_END = ")";

    private final ArrayList<CharSequence> bondedDevices = new ArrayList<>();
    /**
     * array para dispositivos
     */
    private ArrayAdapter<CharSequence> arrayAdapter;

    private int portType = BXLConfigLoader.DEVICE_BUS_BLUETOOTH;
    private String SPP_R200III = "SPP-R200III";
    private String address = "";
    private ListView listView;
    private CheckBox checkBoxAsyncMode;
    private ProgressBar mProgressLarge;
    private TextView txtModelo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_single_choice, bondedDevices);
        listView = view.findViewById(R.id.listViewPairedDevices);
        listView.setAdapter(arrayAdapter);

        /**Le damos ese radioButton*/
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                }
            }
        }

//        mBinding.btnFragment3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_blankFragment2_to_blankFragment3);
//            }
//        });
    }

    /**
     * Jalamos los dispositivos vinculados que tiene el dispositivo
     */
    private void setPairedDevices() {
        bondedDevices.clear();
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> bondedDeviceSet = bluetoothAdapter.getBondedDevices();

        for (BluetoothDevice device : bondedDeviceSet) {
            bondedDevices.add(device.getName() + DEVICE_ADDRESS_START + device.getAddress() + DEVICE_ADDRESS_END);
        }

        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String device = ((TextView) view).getText().toString();
        /**Guardamos el address*/
        address = device.substring(device.indexOf(DEVICE_ADDRESS_START) + DEVICE_ADDRESS_START.length(), device.indexOf(DEVICE_ADDRESS_END));

        mHandler.obtainMessage(0).sendToTarget();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (MainActivity.getPrinterInstance().printerOpen(portType, SPP_R200III, address, checkBoxAsyncMode.isChecked())) {
                    getActivity().finish();
                } else {
                    mHandler.obtainMessage(1, 0, 0, "Fail to printer open!!").sendToTarget();
                }
//                if (MainActivity.getPrinterInstance().printerOpen(portType, SPP_R200III, address, checkBoxAsyncMode.isChecked())) {
//                    finish();
//                } else {
//                    mHandler.obtainMessage(1, 0, 0, "Fail to printer open!!").sendToTarget();
//                }
            }
        }).start();

        Toast.makeText(getContext(), "Click" + address, Toast.LENGTH_SHORT).show();
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
}