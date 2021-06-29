package com.example.lovelyanimals.shop.presenter

import android.app.Application
import com.example.lovelyanimals.AndroidApplication
import com.example.lovelyanimals.BasePresenter
import com.example.lovelyanimals.shop.view.ShopActivity

class ShopPresenter(shopView: ShopActivity, applicationComponent: Application) : BasePresenter<ShopActivity>(shopView) {
    init { (applicationComponent as AndroidApplication).applicationComponent.inject(this) }
    override fun onBindView() {

    }

    override fun onUnbindView() {

    }

}