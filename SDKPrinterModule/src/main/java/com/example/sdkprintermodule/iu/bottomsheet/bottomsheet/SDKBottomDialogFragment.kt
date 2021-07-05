package com.example.sdkprintermodule.iu.bottomsheet.bottomsheet

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.databinding.SdkBottomDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SDKBottomDialogFragment : BottomSheetDialogFragment() {

    lateinit var mBinding: SdkBottomDialogFragmentBinding

    companion object {
        /**Instancia blobarl bxlPrinter*/
//        var bxlPrinter: BixolonPrinter? = null

        val EXTRA_ADDRESS = "Device_address"
        const val TAG = "SDKBottomDialogFragment"
        var m_bluetoothAdapter: BluetoothAdapter? = null
        lateinit var m_paredDevices: Set<BluetoothDevice>
        const val REQUEST_ENABLE_BLUETOOTH = 1

        fun newInstance(): SDKBottomDialogFragment {
            return SDKBottomDialogFragment()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = SdkBottomDialogFragmentBinding.inflate(layoutInflater)
        return mBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**Inicializa bxlPrinter es global*/
//        bxlPrinter = BixolonPrinter(context)

//        Thread.setDefaultUncaughtExceptionHandler(AppUncaughtExceptionHandler())
        mBinding.btnClose.setOnClickListener { dismiss() }
    }


    override fun onResume() {
        super.onResume()

        mBinding.navHotFragment.apply {

            if (m_bluetoothAdapter != null && !m_bluetoothAdapter!!.isEnabled) {
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

//    class AppUncaughtExceptionHandler : Thread.UncaughtExceptionHandler {
//        override fun uncaughtException(thread: Thread, ex: Throwable) {
//            ex.printStackTrace()
//            Process.killProcess(Process.myPid())
//            exitProcess(10)
//        }
//    }

}
//
//fun BixolonPrinter.getPrinterInstance(): BixolonPrinter? {
//    return bxlPrinter
//}