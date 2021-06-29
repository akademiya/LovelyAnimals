package com.example.lovelyanimals.main.view

import android.os.Bundle
import com.example.lovelyanimals.BaseActivity
import com.example.lovelyanimals.R

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