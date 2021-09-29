package com.gvd.lovelyanimal.main

import android.content.Intent

interface IMainActivity {
    fun visibilityFabButton(isVisible: Boolean)
    fun showDialogToCreateNewPost()
    fun onSelectImageFromGallery(intent: Intent, flag: Boolean)
}