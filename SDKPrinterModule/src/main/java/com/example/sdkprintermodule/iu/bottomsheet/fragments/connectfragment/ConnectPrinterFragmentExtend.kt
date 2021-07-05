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
import com.example.sdkprintermodule.databinding.CardItemDeviceDetailBinding
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.SDKBottomDialogFragment.Companion.m_bluetoothAdapter
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.SDKBottomDialogFragment.Companion.m_paredDevices
import com.example.sdkprintermodule.iu.bottomsheet.fragments.connectfragment.adapter.ListDeviceAdapter

fun ConnectPrinterFragment.initElements() {

    mBinding.apply {

        pairedDeviceList()

        btnOpenConfig.setOnClickListener { startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS)) }
    }
}

fun ConnectPrinterFragment.pairedDeviceList() {

    m_paredDevices = m_bluetoothAdapter!!.bondedDevices //jala los dispositivos
    val list: ArrayList<BluetoothDevice> = ArrayList()
    val adapter = ListDeviceAdapter()

    if (m_paredDevices.isNotEmpty()) {

        m_paredDevices.forEach { device ->
            if (device.bluetoothClass.majorDeviceClass == 1536) {
                list.add(device)
                Log.i("device", "" + device)
            }
        }

        if (list.size > 0) {
            mBinding.rvDevices.adapter = adapter

            adapter.submitList(list)

            adapter.onClickListenerInformation = { bluetoothDevice: BluetoothDevice, view: View ->

                CUPopUpWindow(R.layout.card_item_device_detail, view, {
                    val bindingItem = CardItemDeviceDetailBinding.bind(it)
                    bindingItem.apply {
                        txtDeviceName.text = bluetoothDevice.name
                        txtDeviceMac.text = bluetoothDevice.address
                    }
                })
            }
        } else {
            showToast(getString(R.string.no_paired_bluetooth_devices_found))
        }

        adapter.onClickListener = { bluetoothDevice: BluetoothDevice ->
            if (m_bluetoothAdapter!!.isEnabled) {
                showToast(bluetoothDevice.name)
//            val i = Intent(this, ControlActivity::class.java)
//            i.putExtra(EXTRA_ADDRESS, bluetoothDevice.address)
//            startActivity(i)
                val action =
                    ConnectPrinterFragmentDirections.actionConnectPrinterFragmentToToPrintFragment(
                        bluetoothDevice.address
                    )
                findNavController().navigate(action)
            } else {
                findNavController().navigate(R.id.action_connectPrinterFragment_to_activateFragment)
            }
        }
    }
}