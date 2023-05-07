package com.example.english_dictionary.presentation.component

import android.content.res.Configuration
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.english_dictionary.R
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun ErrorMessageButton(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 8.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.round_wifi_off_24),
            contentDescription = null,
            modifier = modifier.size(50.dp),
            tint = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.no_connection_message),
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = stringResource(id = R.string.error_message),
            color = MaterialTheme.colors.onBackground
        )
        Button(
            onClick = onRetryClick,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .background(color = MaterialTheme.colors.background),
            shape = RoundedCornerShape(20.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_refresh_24),
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = modifier.width(5.dp))
            Text(
                text = stringResource(id = R.string.try_again_label),
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Preview(name = "Light Mode", showBackground = true)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun ErrorButtonPreview() {
    EnglishDictionaryTheme {
        ErrorMessageButton(onRetryClick = {

        })
    }
}