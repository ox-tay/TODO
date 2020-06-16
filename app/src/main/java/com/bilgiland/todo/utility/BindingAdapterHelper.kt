package com.bilgiland.todo.utility

import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapterHelper {
    @BindingAdapter("strike")
    @JvmStatic
    fun setStrike(view: TextView, line: Int) {
        if (line != 0)
            view.paintFlags = STRIKE_THRU_TEXT_FLAG
        else
            view.paintFlags = ANTI_ALIAS_FLAG
    }
}