package com.bixolon.sample.bottomsheet.fragments;

import android.bluetooth.BluetoothDevice;

public interface ItemListener {
    void onCLickListenerInfo(BluetoothDevice device, int position);
    void onCLickListenerCard(BluetoothDevice device, int position);
}