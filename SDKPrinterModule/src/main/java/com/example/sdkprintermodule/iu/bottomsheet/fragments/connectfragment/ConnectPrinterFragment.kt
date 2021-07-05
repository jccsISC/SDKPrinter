package com.example.sdkprintermodule.iu.bottomsheet.fragments.connectfragment

import android.os.Bundle
import com.bancoazteca.corresponsal.cacommonutils.base.CUBaseFragment
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.databinding.ConnectPrinterFragmentBinding


class ConnectPrinterFragment : CUBaseFragment<ConnectPrinterFragmentBinding, ConnectPrinterFragmentViewModel>(ConnectPrinterFragmentViewModel::class) {

    override fun getLayout() = R.layout.connect_printer_fragment

    override fun initDependency(savedInstanceState: Bundle?) {}

    override fun initObservers() {}

    override fun initView() {
        initElements()
    }
}