package com.gvd.lovelyanimal

/**
 * Interface representing a BasePresenter in a model view presenter (MVP) pattern.
 */
interface IPresenter<in V: Any> {
    fun bindView(view: V)
    fun unbindView(view: V)
}