package com.gvd.lovelyanimal.main.view

import android.os.Bundle
import com.gvd.lovelyanimal.BaseActivity
import com.gvd.lovelyanimal.R
import kotlinx.android.synthetic.main.view_card_info.*

class PetInfoView : BaseActivity() {

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_card_info)

        toolbar.setNavigationIcon(R.drawable.ic_back_white)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        info_title.text = intent.extras?.getString("petTitle")
        info_description.text = intent.extras?.getString("petDescription")

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