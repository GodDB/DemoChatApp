package com.example.feature.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("visible")
internal fun View.visible(isVisible : Boolean) {
    if(isVisible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}