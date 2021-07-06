package com.example.sdkprintermodule.iu.bottomsheet.bottomsheet

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bancoazteca.corresponsal.cacommonutils.application.CUAppInit
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.databinding.BottomDialogFragmentSdkBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomDialogFragmentSDK : BottomSheetDialogFragment() {

    lateinit var mBinding: BottomDialogFragmentSdkBinding

    /**Variables globales*/
    companion object {
        /**Instancia blobarl bxlPrinter*/
//        var bxlPrinter: BixolonPrinter? = null

        const val TAG = "BottomSheetSDK"
        var m_bluetoothAdapter_sdk: BluetoothAdapter? = null
        lateinit var m_paredDevices_sdk: Set<BluetoothDevice>
        const val REQUEST_ENABLE_BLUETOOTH_SDK = 1

        fun newInstance(): BottomDialogFragmentSDK {
            return BottomDialogFragmentSDK()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = BottomDialogFragmentSdkBinding.inflate(layoutInflater)

        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**Inicializa bxlPrinter es global*/
//        bxlPrinter = BixolonPrinter(context)

        CUAppInit().init(requireActivity().application, requireActivity().applicationContext)

        mBinding.btnCloseSDK.setOnClickListener { dismiss() }
    }


    override fun onResume() {
        super.onResume()

        mBinding.navHotFragmentSdk.apply {

            if (!m_bluetoothAdapter_sdk!!.isEnabled) {

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
//        bxlPrinter?.printerClose()
    }

}
//
//fun BixolonPrinter.getPrinterInstance(): BixolonPrinter? {
//    return bxlPrinter
//}