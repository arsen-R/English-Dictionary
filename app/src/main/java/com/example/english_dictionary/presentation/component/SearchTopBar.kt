package com.example.english_dictionary.presentation.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun SearchTopBar(
    modifier: Modifier = Modifier,
    openDrawer: () -> Unit,
    query: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onCleanSearchQuery: () -> Unit,
    onCloseSearch: () -> Unit,
    topState: Boolean
) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            AppTextField(
                query = query,
                onValueChange = onValueChange,
                onSearch = onSearch,
                onCleanSearchText = onCleanSearchQuery
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (topState) {
                    onCloseSearch()
                } else {
                    openDrawer()
                }
            }) {
                Icon(
                    imageVector = if (topState) Icons.Default.ArrowBack else Icons.Default.Menu,
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchTopBarPreview() {
    EnglishDictionaryTheme {
        SearchTopBar(
            openDrawer = {},
            query = "Live",
            onValueChange = {},
            onSearch = {},
            onCleanSearchQuery = {},
            onCloseSearch = {},
            topState = true
        )
    }
}