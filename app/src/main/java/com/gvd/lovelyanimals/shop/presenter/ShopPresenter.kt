package com.gvd.lovelyanimals.shop.presenter

import android.app.Application
import com.gvd.lovelyanimals.AndroidApplication
import com.gvd.lovelyanimals.BasePresenter
import com.gvd.lovelyanimals.shop.view.ShopActivity

class ShopPresenter(shopView: ShopActivity, applicationComponent: Application) : BasePresenter<ShopActivity>(shopView) {
    init { (applicationComponent as AndroidApplication).applicationComponent.inject(this) }
    override fun onBindView() {

    }

    override fun onUnbindView() {

    }

}