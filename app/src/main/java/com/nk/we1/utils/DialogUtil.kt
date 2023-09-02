/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */
package com.nk.we1.utils

import android.content.Context
import com.nk.we1.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogUtil {
    /**
     * Creates alert material single selection dialog for language selection
     * @param context of type [Context]
     * @param posButtonText [String] provide text to show on positive button
     * @param negButtonText [String] provide text to show on negative button
     * @param preferenceHelper [PreferenceHelper] provide preferenceHelper object to save data to Shared preferences
     * @param languageSelectorListener [LanguageSelectorListener] provide languageSelectorListener to listen for when language is selected
     */
    fun createAlertDialogForLanguageSelection(
        context: Context,
        posButtonText: String,
        negButtonText: String,
        preferenceHelper: PreferenceHelper,
        languageSelectorListener: LanguageSelectorListener
    ) {
        val languages = arrayOf(context.getString(R.string.english), context.getString(R.string.japanese), context.getString(R.string.bengali))
        val languageLocal = arrayOf("en", "ja", "bn")
        val selectedPos = languageLocal.indexOfFirst { it == preferenceHelper.getSelectedLanguageCode() }
        val alertDialogBox = MaterialAlertDialogBuilder(context, R.style.MaterialAlertDialogTheme)
        var selected = languageLocal[selectedPos]
        alertDialogBox.setSingleChoiceItems( languages, selectedPos) { _, p1 ->
            selected = languageLocal[p1]
        }
        alertDialogBox.setTitle(context.getString(R.string.select_language_message))
        alertDialogBox.setPositiveButton(posButtonText
        ) { _, _ ->
            if (selected != preferenceHelper.getSelectedLanguageCode())
                languageSelectorListener.onLanguageChanged(selected)
        }
        alertDialogBox.setNegativeButton(negButtonText
        ) { _, _ -> }
        alertDialogBox.show()
    }
}

interface AlertDialogListener {
    fun onPositiveButtonClicked()
    fun onNegativeButtonClicked()
}

interface LanguageSelectorListener {
    fun onLanguageChanged(languageCode: String)
}

