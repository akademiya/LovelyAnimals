package com.gvd.lovelyanimals.main.presenter

import android.app.Application
import com.gvd.lovelyanimals.AndroidApplication
import com.gvd.lovelyanimals.BasePresenter
import com.gvd.lovelyanimals.main.view.MainActivity

class MainPresenter(mainView: MainActivity, applicationComponent: Application) : BasePresenter<MainActivity>(mainView) {
    init { (applicationComponent as AndroidApplication).applicationComponent.inject(this) }
    override fun onBindView() {}

    override fun onUnbindView() {}

}