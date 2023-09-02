/*
 * MIT License
 *
 * Copyright (c) 2020 Shreyas Patil
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nk.we1.utils

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.nk.we1.R

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun AppCompatButton.makeButtonSelectedOrNot(isSelected: Boolean) {
    if (isSelected) {
        this.background = context?.let { it ->
            ContextCompat.getDrawable(
                it,
                R.drawable.rectangle_background_accent
            )
        }
        val colorP = context?.let { ContextCompat.getColor(it, R.color.colorPrimary) }
        colorP?.let { this.setTextColor(it) }
    }
    else {
        this.background = context?.let { it ->
            ContextCompat.getDrawable(
                it,
                R.drawable.rectangle_outlined_bold_black
            )
        }
        val colorP = context?.let { ContextCompat.getColor(it, R.color.textColor) }
        colorP?.let { this.setTextColor(it) }
    }
}

