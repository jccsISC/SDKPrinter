package com.example.sdkprintermodule.iu.bottomsheet.fragments.connectfragment.adapter

import android.bluetooth.BluetoothDevice
import androidx.recyclerview.widget.DiffUtil

//Hacemos uso de DiffCallback para identificar que item se agregó o borró es lo mismo siempre
object DiffCallback : DiffUtil.ItemCallback<BluetoothDevice>() {

    override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
        return oldItem.name == newItem.name //igualamos si el item nuevo con el viejo es el mismo
    }

    override fun areContentsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice): Boolean {
        return oldItem == newItem //para igualar modelos debe de ser una data class
    }
}