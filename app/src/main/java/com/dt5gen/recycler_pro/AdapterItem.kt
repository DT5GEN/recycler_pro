package com.dt5gen.recycler_pro

import androidx.annotation.DrawableRes

sealed class AdapterItem (val key: String)

data class NumberItem (val id: String, val textNumberItem:String ) : AdapterItem(id)
data class HeaderItem (val id: String, val headerItem:String ) : AdapterItem(id)
data class ImageItem (val id: String, @DrawableRes val img: Int) : AdapterItem(id)