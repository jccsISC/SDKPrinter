package com.bixolon.sample.bottomsheet.fragments.fragment2;

import android.bluetooth.BluetoothDevice;

public interface ItemListener {
    void onCLickListenerInfo(BluetoothDevice device, int position);
    void onCLickListenerCard(BluetoothDevice device, int position);
}