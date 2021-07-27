package com.gvd.lovelyanimal.main.view

import android.os.Bundle
import com.gvd.lovelyanimal.BaseActivity
import com.gvd.lovelyanimal.R

class PetInfoView : BaseActivity() {

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_main_card_list)
    }

//    override fun onAttachedToWindow() {
//        super.onAttachedToWindow()
//        presenter.bindView(this)
//    }
//
//    override fun onDetachedFromWindow() {
//        super.onDetachedFromWindow()
//        presenter.unbindView(this)
//    }
}