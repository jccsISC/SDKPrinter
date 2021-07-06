package com.example.sdkprintermodule.iu.bottomsheet.fragments.connectfragment.adapter

import android.bluetooth.BluetoothDevice
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sdkprintermodule.databinding.CardItemDeviceSdkBinding

class ListDeviceAdapter: ListAdapter<BluetoothDevice, ListDeviceAdapter.DeviceViewHolder>(DiffCallback) {

    lateinit var onClickListener: (BluetoothDevice) -> Unit
    lateinit var onClickListenerInformation: (BluetoothDevice, View) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = CardItemDeviceSdkBinding.inflate(LayoutInflater.from(parent.context))
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val deviceModel = getItem(position)
        holder.bind(deviceModel)
    }

    inner class DeviceViewHolder(private val binding: CardItemDeviceSdkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(deviceModel: BluetoothDevice) = with(binding) {

            txtDeviceSDK.text = deviceModel.name

            btnInfoDeviceSDK.setOnClickListener {
                if (::onClickListenerInformation.isInitialized) {
                    onClickListenerInformation(deviceModel, it)
                }else {
                    Log.e("error", "No se inicializó el onClickLambda")
                }
            }

            root.setOnClickListener {
                if (::onClickListener.isInitialized) {
                    onClickListener(deviceModel)
                }else {
                    Log.e("error", "No se inicializó el onClickLambda")
                }
            }
        }
    }
}