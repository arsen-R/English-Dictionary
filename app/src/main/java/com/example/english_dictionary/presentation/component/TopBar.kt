package com.example.english_dictionary.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.english_dictionary.R
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme


@Composable
fun TopBar(
    openDrawer: () -> Unit,
    title: String
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = openDrawer) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }
    )
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    EnglishDictionaryTheme {
        TopBar(openDrawer = {}, title = stringResource(id = R.string.app_name))
    }
}