package com.gvd.lovelyanimal.shop.presenter

import android.app.Application
import com.gvd.lovelyanimal.AndroidApplication
import com.gvd.lovelyanimal.BasePresenter
import com.gvd.lovelyanimal.shop.view.ShopActivity

class ShopPresenter(shopView: ShopActivity, applicationComponent: Application) : BasePresenter<ShopActivity>(shopView) {
    init { (applicationComponent as AndroidApplication).applicationComponent.inject(this) }
    override fun onBindView() {

    }

    override fun onUnbindView() {

    }

}