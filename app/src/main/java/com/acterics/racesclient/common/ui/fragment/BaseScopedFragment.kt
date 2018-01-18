package com.acterics.racesclient.common.ui.fragment

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatFragment

/**
 * Created by root on 29.10.17.
 */
abstract class BaseScopedFragment: MvpAppCompatFragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponent()
        super.onCreate(savedInstanceState)
    }


    override fun onDestroy() {
        if (isRemoving || activity!!.isFinishing && !isStateSaved) {
            rejectComponent()
        }
        super.onDestroy()
    }

//    override fun onDetach() {
//        if (isRemoving || activity!!.isFinishing && !isStateSaved) {
//            rejectComponent()
//        }
//        super.onDetach()
//    }

    abstract fun injectComponent()
    open fun rejectComponent() {}
}