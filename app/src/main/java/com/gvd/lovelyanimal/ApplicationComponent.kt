package com.gvd.lovelyanimal

import com.gvd.lovelyanimal.main.presenter.MainPresenter
import com.gvd.lovelyanimal.main.view.MainActivity
import com.gvd.lovelyanimal.module.AppModule
import com.gvd.lovelyanimal.shop.presenter.ShopPresenter
import com.gvd.lovelyanimal.shop.view.ShopActivity
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