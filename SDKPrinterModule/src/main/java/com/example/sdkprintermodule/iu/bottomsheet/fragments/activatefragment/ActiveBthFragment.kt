package com.example.sdkprintermodule.iu.bottomsheet.fragments.activatefragment

import android.os.Bundle
import com.bancoazteca.corresponsal.cacommonutils.base.CUBaseFragment
import com.example.sdkprintermodule.R
import com.example.sdkprintermodule.databinding.ActivateBthFragmentBinding

class ActiveBthFragment : CUBaseFragment<ActivateBthFragmentBinding, ActiveBthFragmentViewModel>(
    ActiveBthFragmentViewModel::class
) {

    override fun getLayout() = R.layout.activate_bth_fragment

    override fun initDependency(savedInstanceState: Bundle?) {}

    override fun initObservers() {}

    override fun initView() {
        initElements()
    }

}