package com.example.sdkprintermodule.iu.bottomsheet.fragments.connectfragment

import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bancoazteca.corresponsal.cacommonutils.component.dialogs.popupwindow.CUPopUpWindow
import com.bancoazteca.corresponsal.cacommonutils.component.showToast
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.databinding.CardItemDeviceDetailSdkBinding
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.BottomDialogFragmentSDK.Companion.m_bluetoothAdapter_sdk
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.BottomDialogFragmentSDK.Companion.m_paredDevices_sdk
import com.example.sdkprintermodule.iu.bottomsheet.fragments.connectfragment.adapter.ListDeviceAdapter

fun ConnectPrinterFragment.initElements() {

    mBinding.apply {

        pairedDeviceList()

        btnOpenConfigSDK.setOnClickListener { startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS)) }
    }
}

fun ConnectPrinterFragment.pairedDeviceList() {

    m_paredDevices_sdk = m_bluetoothAdapter_sdk!!.bondedDevices //jala los dispositivos
    val list: ArrayList<BluetoothDevice> = ArrayList()
    val adapter = ListDeviceAdapter()

    if (m_paredDevices_sdk.isNotEmpty()) {

        m_paredDevices_sdk.forEach { device ->
            if (device.bluetoothClass.majorDeviceClass == 1536) {
                list.add(device)
                Log.i("device", "" + device)
            }
        }

        if (list.size > 0) {
            mBinding.rvDevicesSDK.adapter = adapter

            adapter.submitList(list)

            adapter.onClickListenerInformation = { bluetoothDevice: BluetoothDevice, view: View ->

                CUPopUpWindow(R.layout.card_item_device_detail_sdk, view, {
                    val bindingItem = CardItemDeviceDetailSdkBinding.bind(it)
                    bindingItem.apply {
                        txtDeviceNameSDK.text = bluetoothDevice.name
                        txtDeviceMacSDK.text = bluetoothDevice.address
                    }
                })
            }
        } else {
            showToast(getString(R.string.no_paired_bluetooth_devices_found))
        }

        adapter.onClickListener = { bluetoothDevice: BluetoothDevice ->
            if (m_bluetoothAdapter_sdk!!.isEnabled) {
                val action = ConnectPrinterFragmentDirections.actionConnectPrinterFragmentToToPrintFragment(bluetoothDevice.address)
                findNavController().navigate(action)
            } else {
                findNavController().navigate(R.id.action_connectPrinterFragment_to_activateFragment)
            }
        }
    }
}