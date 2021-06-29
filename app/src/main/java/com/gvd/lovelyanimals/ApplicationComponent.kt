package com.gvd.lovelyanimals

import com.gvd.lovelyanimals.main.presenter.MainPresenter
import com.gvd.lovelyanimals.main.view.MainActivity
import com.gvd.lovelyanimals.module.AppModule
import com.gvd.lovelyanimals.shop.presenter.ShopPresenter
import com.gvd.lovelyanimals.shop.view.ShopActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface ApplicationComponent {
    fun inject(modApplication: AndroidApplication)

    // VIEW
    fun inject(mainActivity: MainActivity)
    fun inject(shopActivity: ShopActivity)

    // PRESENTER
    fun inject(mainPresenter: MainPresenter)
    fun inject(shopPresenter: ShopPresenter)
}