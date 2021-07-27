package com.gvd.lovelyanimal.main.presenter

import android.app.Application
import android.content.Intent
import android.provider.MediaStore
import com.gvd.lovelyanimal.AndroidApplication
import com.gvd.lovelyanimal.BasePresenter
import com.gvd.lovelyanimal.main.view.MainActivity

class MainPresenter(mainView: MainActivity, applicationComponent: Application) : BasePresenter<MainActivity>(mainView) {
    init { (applicationComponent as AndroidApplication).applicationComponent.inject(this) }
    override fun onBindView() {}

    override fun onUnbindView() {}

    fun clickByTitle7(counter: Float) {
        view?.visibilityFabButton(counter.equals(7F))
    }

    fun createNewPostAnimal() {
        view?.showDialogToCreateNewPost()
    }

    fun onSelectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png", "image/jpg"))
        view?.onSelectImageFromGallery(intent)
    }

}