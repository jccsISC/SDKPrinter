package com.example.sdkprintermodule.iu.bottomsheet.fragments.activatefragment

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.bancoazteca.corresponsal.cacommonutils.component.showToast
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.BottomDialogFragmentSDK.Companion.REQUEST_ENABLE_BLUETOOTH_SDK
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.BottomDialogFragmentSDK.Companion.m_bluetoothAdapter_sdk

fun ActiveBthFragment.initElements() {

    mBinding.apply {

        //Verificamos si el dispositivo es compatible con el bluetooth
        m_bluetoothAdapter_sdk = BluetoothAdapter.getDefaultAdapter()
        if (m_bluetoothAdapter_sdk == null) {
            showToast(getString(R.string.this_device_does_not_support_bth))
        }

        if (!m_bluetoothAdapter_sdk!!.isEnabled) {
            findNavController().navigate(R.id.action_activateFragment_to_connectPrinterFragment)
        }

        btnActivateBth.setOnClickListener {
            //Verificamos si está activado el bluetooth si no lo activamos
            if (!m_bluetoothAdapter_sdk!!.isEnabled) {
                val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH_SDK)
            }
        }
    }
}