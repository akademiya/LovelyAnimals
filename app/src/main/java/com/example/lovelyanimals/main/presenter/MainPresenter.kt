package com.example.lovelyanimals.main.presenter

import android.app.Application
import com.example.lovelyanimals.AndroidApplication
import com.example.lovelyanimals.BasePresenter
import com.example.lovelyanimals.main.view.MainActivity

class MainPresenter(mainView: MainActivity, applicationComponent: Application) : BasePresenter<MainActivity>(mainView) {
    init { (applicationComponent as AndroidApplication).applicationComponent.inject(this) }
    override fun onBindView() {}

    override fun onUnbindView() {}

}