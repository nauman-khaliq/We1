/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */
package com.nk.we1.utils.extensions

import android.content.Context
import android.view.WindowManager
import android.widget.PopupWindow

import android.view.View

/**
 * Dim the background when PopupWindow shows
 */
fun PopupWindow.dimBehind() {
//    val container = contentView.rootView
//    val context = contentView.context
//    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//    val p = container.layoutParams as WindowManager.LayoutParams
//    p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
//    p.dimAmount = 0.3f
//    wm.updateViewLayout(container, p)
    val container: View = if (background == null) {
        contentView.parent as View
    } else {
        contentView.parent.parent as View
    }
    val context: Context = contentView.context
    val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val p = container.layoutParams as WindowManager.LayoutParams
    p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
    p.dimAmount = 0.3f
    wm.updateViewLayout(container, p)
}


