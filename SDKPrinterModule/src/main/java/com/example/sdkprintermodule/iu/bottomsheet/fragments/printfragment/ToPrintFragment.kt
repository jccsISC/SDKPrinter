package com.example.sdkprintermodule.iu.bottomsheet.fragments.printfragment

import android.os.Bundle
import com.bancoazteca.corresponsal.cacommonutils.base.CUBaseFragment
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.databinding.ToPrintFragmentBinding


class ToPrintFragment : CUBaseFragment<ToPrintFragmentBinding, ToPrintFragmentViewModel>(ToPrintFragmentViewModel::class) {
    override fun getLayout() = R.layout.to_print_fragment

    override fun initDependency(savedInstanceState: Bundle?) {}

    override fun initObservers() {}

    override fun initView() {
        initElements()
    }

}