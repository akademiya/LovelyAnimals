package com.gvd.lovelyanimal.main.presenter

import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import com.gvd.lovelyanimal.AndroidApplication
import com.gvd.lovelyanimal.BasePresenter
import com.gvd.lovelyanimal.main.view.MainActivity

class MainPresenter(mainView: MainActivity, applicationComponent: Application) : BasePresenter<MainActivity>(mainView) {
    init { (applicationComponent as AndroidApplication).applicationComponent.inject(this) }

    private var counterClickSelectImg = 0F

    override fun onBindView() {}

    override fun onUnbindView() {}

    fun clickByTitle7(counter: Float) {
        view?.visibilityFabButton(counter.equals(7F))
    }

    fun createNewPostAnimal() {
        view?.showDialogToCreateNewPost()
    }

    fun onSelectImage() {
        var firstSelectImgFlag = false
        counterClickSelectImg++
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.ACTION_SEND_MULTIPLE, arrayOf("image/jpeg", "image/png", "image/jpg"))
        firstSelectImgFlag = counterClickSelectImg == 1F
        view?.onSelectImageFromGallery(intent, firstSelectImgFlag)
    }

}