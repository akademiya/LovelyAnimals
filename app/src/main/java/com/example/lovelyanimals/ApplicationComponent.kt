package com.example.lovelyanimals

import com.example.lovelyanimals.main.presenter.MainPresenter
import com.example.lovelyanimals.main.view.MainActivity
import com.example.lovelyanimals.module.AppModule
import com.example.lovelyanimals.shop.presenter.ShopPresenter
import com.example.lovelyanimals.shop.view.ShopActivity
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