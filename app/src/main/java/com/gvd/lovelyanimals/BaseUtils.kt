package com.gvd.lovelyanimals

import android.content.Intent

fun Intent.noAnimation() : Intent {
    return addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
}