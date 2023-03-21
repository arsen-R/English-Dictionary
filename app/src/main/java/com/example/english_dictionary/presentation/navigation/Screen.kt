package com.example.english_dictionary.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.util.Constant

sealed class Screen(
    @StringRes val title: Int,
    val route: String,
    @DrawableRes val icon: Int?
) {
    object Search : Screen(R.string.search_label, "search_screen", R.drawable.round_search_24)
    object Bookmarks :
        Screen(R.string.bookmarks_label, "bookmarks_screen", R.drawable.round_bookmark_24)
    object Settings : Screen(R.string.settings_label, "settings_screen", R.drawable.round_settings_24)
    object WordDetail : Screen(R.string.detail_label, "detail_screen/{${Constant.WORD_ARGUMENT_KEY}}", null) {
        fun passWordId(wordId: String) = "detail_screen/$wordId"
    }
    object History : Screen(R.string.history_label, "history_screen", R.drawable.round_history_24)
}