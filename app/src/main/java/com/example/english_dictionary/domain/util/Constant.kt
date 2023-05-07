package com.example.english_dictionary.domain.util

import androidx.datastore.preferences.core.intPreferencesKey

object Constant {
    const val BASE_URL: String = "https://od-api.oxforddictionaries.com/api/v2/"

    const val APP_KEY_VALUE: String = "5ebb797501e5bade6ee8d36a9e0cd187"
    const val APP_ID_VALUE: String = "17fe97bc"

    const val APP_KEY_KEY: String = "app_key"
    const val APP_ID_KEY: String = "app_id"

    const val DATABASE_NAME: String = "dictionary.db"

    const val WORD_ARGUMENT_KEY = "wordId"
    const val SOURCE_LANG_ARG_KEY = "sourceLang"

    const val THEME_PREFERENCES_KEY = "THEME_PREFERENCES"
    const val SOURCE_LANGUAGE_KEY = "SOURCE_LANGUAGE"
}