package com.example.sdkprintermodule.iu.bottomsheet.bottomsheet

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.databinding.BottomSheetDialogLayoutBinding
import com.example.sdkprintermodule.iu.bottomsheet.bixolon.PrinterControl.BixolonPrinter
import com.example.sdkprintermodule.iu.bottomsheet.bottomsheet.ActionBottomDialogFragment.Companion.bxlPrinter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ActionBottomDialogFragment : BottomSheetDialogFragment() {

    lateinit var mBinding: BottomSheetDialogLayoutBinding

    companion object {
        var bxlPrinter: BixolonPrinter? = null
        val EXTRA_ADDRESS = "Device_address"
        const val TAG = "CustomBottomSheetDialogFragment"
        var m_bluetoothAdapter: BluetoothAdapter? = null
        lateinit var m_paredDevices: Set<BluetoothDevice>
        val REQUEST_ENABLE_BLUETOOTH = 1
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = BottomSheetDialogLayoutBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        bxlPrinter = BixolonPrinter(context)

        mBinding.apply {
            btnClose.setOnClickListener { dismiss() }
        }
    }

    override fun onResume() {
        super.onResume()

        mBinding.fragmentContainer.apply {
            if (!m_bluetoothAdapter!!.isEnabled) {
                when (findNavController().currentDestination!!.label) {
                    FragmentLabelEnum.CONNECT_FRAGMENT.titleLabel -> {
                        findNavController()
                                .navigate(R.id.action_connectPrinterFragment_to_activateFragment)
                    }
                    FragmentLabelEnum.TO_PRINT_FRAGMENT.titleLabel -> {
                        findNavController()
                                .navigate(R.id.action_toPrintFragment_to_activateFragment)
                    }
                    else -> {
                        findNavController().currentDestination!!.label
                    }
                }
            } else {
                when (findNavController().currentDestination!!.label) {
                    FragmentLabelEnum.ACTIVATE_FRAGMENT.titleLabel -> {
                        findNavController()
                                .navigate(R.id.action_activateFragment_to_connectPrinterFragment)
                    }
                    else -> {
                        findNavController().currentDestination!!.label
                    }
                }
            }
        }
    }

    //todo revisar la instancia
    override fun onDestroy() {
        super.onDestroy()
        bxlPrinter?.printerClose()
    }

}

fun BixolonPrinter.getPrinterInstance(): BixolonPrinter? {
    return bxlPrinter
}