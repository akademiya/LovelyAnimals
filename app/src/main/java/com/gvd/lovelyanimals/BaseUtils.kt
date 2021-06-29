package com.gvd.lovelyanimals

import android.content.Intent
import android.view.View

fun Intent.noAnimation() : Intent {
    return addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
}

fun Boolean.toAndroidVisibility() = if (this) View.VISIBLE else View.GONE