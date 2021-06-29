package com.gvd.lovelyanimals.shop.view

import android.os.Bundle
import com.gvd.lovelyanimals.BaseActivity
import com.gvd.lovelyanimals.R
import com.gvd.lovelyanimals.shop.presenter.ShopPresenter

class ShopActivity : BaseActivity() {

    private lateinit var presenter: ShopPresenter

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.view_main_card_list)
        presenter = ShopPresenter(this, application)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.unbindView(this)
    }

}