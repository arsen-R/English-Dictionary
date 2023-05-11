package com.example.english_dictionary.presentation.component

import android.content.res.Configuration
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.model.*
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import okio.IOException

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PronunciationsRow(
    modifier: Modifier = Modifier,
    lexical: LexicalEntry,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.dp
        ), verticalAlignment = Alignment.CenterVertically, maxItemsInEachRow = 3
    ) {
        lexical.entries?.get(0)?.pronunciations?.filter {
            it.phoneticNotation == "IPA"
        }?.forEach { pronunciation ->
            PronunciationItem(pronunciation = pronunciation)
        }
    }
}

@Composable
fun PronunciationItem(
    modifier: Modifier = Modifier,
    pronunciation: Pronunciation
) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 5.dp)
    ) {
        Text(
            text = "/${pronunciation.phoneticSpelling}/"
        )
        pronunciation.audioFile?.let {
            val media = MediaPlayer.create(context, Uri.parse(it))
            DisposableEffect(
                Icon(
                    painter = painterResource(id = R.drawable.round_volume_up_24),
                    contentDescription = null,
                    modifier = modifier
                        .background(shape = CircleShape, color = MaterialTheme.colors.onBackground)
                        .clickable {
                            media.start()
                        },
                    tint = MaterialTheme.colors.background
                )
            ) {
                onDispose {
                    media.stop()
                    media.release()
                }
            }
        }
    }
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PronunciationsRowPreview() {
    EnglishDictionaryTheme {
        val lexical = listOf(
            LexicalEntry(
                entries = listOf(
                    Entry(
                        pronunciations = listOf(
                            Pronunciation(
                                phoneticNotation = "IPA",
                                phoneticSpelling = "have",
                                audioFile = "https://audio.oxforddictionaries.com/en/mp3/have__gb_3.mp3"
                            ),
                        )
                    ),
                    Entry(
                        pronunciations = listOf(
                            Pronunciation(phoneticNotation = "IPA", phoneticSpelling = "have on"),
                            Pronunciation(phoneticNotation = "IPA", phoneticSpelling = "have out"),
                        )
                    ),
                )
            ),
            LexicalEntry(
                entries = listOf(
                    Entry(
                        pronunciations = listOf(
                            Pronunciation(phoneticNotation = "IPA", phoneticSpelling = "have to"),
                        )
                    ),
                    Entry(
                        pronunciations = listOf(
                            Pronunciation(phoneticNotation = "IPA", phoneticSpelling = "have off"),
                        )
                    ),
                )
            ),
        )
        lexical.forEach {
            PronunciationsRow(lexical = it)
        }

    }
}