package com.example.english_dictionary.domain.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class CopyManager {
    fun copyToClickBoard(context: Context, copyText: String) {
        val clipBoardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copy", copyText)
        clipBoardManager.setPrimaryClip(clip)
    }
}