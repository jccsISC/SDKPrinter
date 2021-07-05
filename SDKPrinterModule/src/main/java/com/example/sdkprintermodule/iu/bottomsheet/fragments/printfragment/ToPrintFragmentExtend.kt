package com.example.sdkprintermodule.iu.bottomsheet.fragments.printfragment

import androidx.navigation.fragment.navArgs
import com.bancoazteca.corresponsal.cacommonutils.component.showToast
import com.example.sdkprintermodule.iu.bottomsheet.fragments.printfragment.ToPrintFragment

fun ToPrintFragment.initElements() {
    val args: ToPrintFragmentArgs by navArgs()

    showToast(args.address.toString())


}