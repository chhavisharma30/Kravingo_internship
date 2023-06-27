package com.example.datingapp.utils

import android.content.Context

fun dpToPx(context: Context, dp: Float): Int {
    val density = context.resources.displayMetrics.density
    return (dp * density + 0.5f).toInt()
}