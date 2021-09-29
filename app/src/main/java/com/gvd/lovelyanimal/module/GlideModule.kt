package com.gvd.lovelyanimal.module

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideModule : AppGlideModule() {
//    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        registry.append(
//            StorageReference::class.java, InputStream::class.java,
//            FirebaseImageLoader.Factory())
//    }
}