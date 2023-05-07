package com.example.english_dictionary.presentation.screen.settings

import android.content.res.Configuration
import android.widget.Space
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.util.Constant
import com.example.english_dictionary.presentation.component.DialogPreferencesSelection
import com.example.english_dictionary.presentation.component.PreferenceGroup
import com.example.english_dictionary.presentation.component.TextPreferences
import com.example.english_dictionary.presentation.component.TopBar
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
    navController: NavController
) {
    SettingsScreen(
        modifier = modifier,
        onOpenDrawer = onOpenDrawer,
        navController = navController,
        viewModel = hiltViewModel()
    )
}

@Composable
internal fun SettingsScreen(
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
    navController: NavController,
    viewModel: SettingsViewModel
) {
    val currentTheme = viewModel.selectedTheme.collectAsStateWithLifecycle().value ?: 0
    val currentSourceLang =
        viewModel.selectedSourceLanguage.collectAsStateWithLifecycle().value ?: 0

    val showThemeDialog = remember { mutableStateOf(false) }
    val showSourceLangDialog = remember { mutableStateOf(false) }

    val themeLabel = stringArrayResource(id = R.array.theme_labels)[currentTheme]
    val sourceLangLabel =
        stringArrayResource(id = R.array.source_language_labels)[currentSourceLang]

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
            Column(modifier = modifier.padding(it)) {
                Spacer(modifier = modifier.height(14.dp))

                PreferenceGroup(titleGroup = stringResource(id = R.string.personalise_label)) {
                    TextPreferences(
                        icon = painterResource(id = R.drawable.round_lightbulb_24),
                        title = stringResource(id = R.string.change_theme_label),
                        subTitle = themeLabel
                    ) {
                        showThemeDialog.value = !showThemeDialog.value
                    }

                    if (showThemeDialog.value) {
                        ChangeTheme(
                            viewModel = viewModel,
                            showDialog = showThemeDialog,
                            currentValue = themeLabel
                        )
                    }

//                    TextPreferences(
//                        icon = painterResource(id = R.drawable.round_lightbulb_24),
//                        title = stringResource(id = R.string.change_source_language),
//                        subTitle = sourceLangLabel
//                    ) {
//                        showSourceLangDialog.value = !showSourceLangDialog.value
//                    }
//
//                    if (showSourceLangDialog.value) {
//                        ChangeSourceLang(
//                            viewModel = viewModel,
//                            showDialog = showSourceLangDialog,
//                            currentValue = sourceLangLabel
//                        )
//                    }
                }
            }
        }
    )
}


@Composable
fun ChangeTheme(
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferencesSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_theme_label),
        currentValue = currentValue ?: stringResource(id = R.string.system_default_label),
        labels = stringArrayResource(id = R.array.theme_labels),
        onNegativeClick = { showDialog.value = false },
        onOptionSelected = { theme ->
            viewModel.savePreferencesSelection(
                key = Constant.THEME_PREFERENCES_KEY,
                selection = theme
            )
        }
    )
}

@Composable
fun ChangeSourceLang(
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferencesSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_source_language),
        currentValue = currentValue ?: stringResource(id = R.string.gb_label),
        labels = stringArrayResource(id = R.array.source_language_labels),
        onNegativeClick = { showDialog.value = false },
        onOptionSelected = { theme ->
            viewModel.savePreferencesSelection(
                key = Constant.SOURCE_LANGUAGE_KEY,
                selection = theme
            )
        }
    )
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SettingsScreenPreview() {
    EnglishDictionaryTheme {
        SettingsScreen(
            onOpenDrawer = {

            },
            navController = rememberNavController(),
            viewModel = hiltViewModel()
        )
    }
}