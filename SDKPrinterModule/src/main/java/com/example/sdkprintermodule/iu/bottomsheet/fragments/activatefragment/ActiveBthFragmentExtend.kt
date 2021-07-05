package com.example.sdkprintermodule.iu.bottomsheet.fragments.activatefragment

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import com.bancoazteca.corresponsal.cacommonutils.component.showToast
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.SDKBottomDialogFragment.Companion.REQUEST_ENABLE_BLUETOOTH
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.SDKBottomDialogFragment.Companion.m_bluetoothAdapter


fun ActiveBthFragment.initElements() {

    mBinding.apply {

        //Verificamos si el dispositivo es compatible con el bluetooth
        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (m_bluetoothAdapter == null) {
            showToast(getString(R.string.this_device_does_not_support_bth))
        }

        btnActivateBth.setOnClickListener {
            //Verificamos si está activado el bluetooth si no lo activamos
            if (!m_bluetoothAdapter!!.isEnabled) {
                txtBthStatus.text = getString(R.string.bluetooth_desactivado)
                val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
            }
        }
    }
}