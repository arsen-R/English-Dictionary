package com.example.english_dictionary.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.english_dictionary.R
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    query: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    onCleanSearchText: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val interactionSource = remember { MutableInteractionSource() }
    val colors = TextFieldDefaults.textFieldColors(cursorColor = Color.Black)

    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        BasicTextField(
            value = query,
            onValueChange = onValueChange,
            modifier = modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(25.dp))
                .indicatorLine(
                    enabled = true,
                    isError = false,
                    interactionSource = interactionSource,
                    colors = colors
                )
                .height(40.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (query.isNotBlank() || query.isNotEmpty()) {
                        onSearch()
                    }
                    keyboardController?.hide()
                }
            ),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp
            ),
            maxLines = 1,
            singleLine = true
        ) {
            TextFieldDefaults.TextFieldDecorationBox(
                value = query,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_label),
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                    )
                },
                trailingIcon = {
                    if (query.isNotBlank() || query.isNotEmpty()) {
                        IconButton(onClick = {
                            onCleanSearchText("")
                            keyboardController?.hide()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Search Icon",
                            )
                        }
                    }
                },
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    top = 5.dp,
                    bottom = 5.dp
                )
            )
        }
    }
}


@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppTextFieldPreview() {
    EnglishDictionaryTheme {
        AppTextField(
            query = "",
            onValueChange = {

            },
            onCleanSearchText = {

            },
            onSearch = {

            }
        )
    }
}