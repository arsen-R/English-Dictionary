package com.example.english_dictionary.presentation.screen.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.english_dictionary.R
import com.example.english_dictionary.presentation.component.TopBar
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopBar(
                openDrawer = onOpenDrawer,
                title = stringResource(id = R.string.settings_label)
            )
        },
        content = {
            Box(modifier = modifier.padding(it)) {
                Text(text = "Settings")
            }
        }
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SettingsScreenPreview() {
    EnglishDictionaryTheme {
        SettingsScreen(onOpenDrawer =  {

        })
    }
}