package com.dt5gen.recycler_pro

import androidx.annotation.DrawableRes

sealed class AdapterItem

class NumberItem (val textNumberItem:String ) : AdapterItem()
class HeaderItem (val headerItem:String ) : AdapterItem()
class ImageItem (@DrawableRes val img: Int) : AdapterItem()