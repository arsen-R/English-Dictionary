package com.example.english_dictionary.presentation.screen.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.english_dictionary.domain.model.Antonym
import com.example.english_dictionary.domain.model.Entry
import com.example.english_dictionary.domain.model.LexicalEntry
import com.example.english_dictionary.domain.model.Sense
import com.example.english_dictionary.domain.model.Synonym
import com.example.english_dictionary.domain.model.Word
import com.example.english_dictionary.domain.model.WordResult
import com.example.english_dictionary.presentation.component.AntonymRow
import com.example.english_dictionary.presentation.component.ErrorMessageButton
import com.example.english_dictionary.presentation.component.LoadingProgress
import com.example.english_dictionary.presentation.component.SynonymRow
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme

@Composable
fun ThesaurusScreen(
    modifier: Modifier = Modifier,
    viewModel: WordDetailViewModel,
) {
    val uiState = viewModel.thesaurusUiState.collectAsStateWithLifecycle().value
    ThesaurusScreen(
        modifier = modifier,
        uiState = uiState,
        onRetryClick = { viewModel.getThesaurusWord() }
    )
}

@Composable
internal fun ThesaurusScreen(
    modifier: Modifier = Modifier,
    uiState: ThesaurusUiState,
    onRetryClick: () -> Unit
) {
    when (uiState) {
        is ThesaurusUiState.Loading -> {
            LoadingProgress()
        }
        is ThesaurusUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                uiState.word.results?.forEach { result ->
                    result.lexicalEntries?.forEach { lexical ->

                        lexical.entries?.forEach { entry ->
                            AntonymRow(entry = entry, lexicalCategory = lexical.lexicalCategory)
                            SynonymRow(entry = entry, lexicalCategory = lexical.lexicalCategory)
                        }

                    }
                }
            }
        }
        is ThesaurusUiState.Error -> {
            ErrorMessageButton() {
                onRetryClick()
            }
        }
    }
}

@Preview(name = "Light mode", showBackground = true)
@Preview(
    name = "Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF212324
)
@Composable
fun ThesaurusScreenPreview(
    modifier: Modifier = Modifier,
) {
    EnglishDictionaryTheme() {
        val word = Word(
            results = listOf(
                WordResult(
                    lexicalEntries = listOf(
                        LexicalEntry(
                            entries = listOf(
                                Entry(
                                    senses = listOf(
                                        Sense(
                                            antonyms = listOf(
                                                Antonym(language = "en", text = "be bereft of")
                                            ),
                                            synonyms = listOf(
                                                Synonym(language = "en", text = "posses"),
                                                Synonym(language = "en", text = "posses 1"),
                                                Synonym(language = "en", text = "posses 2"),
                                                Synonym(language = "en", text = "posses 3"),
                                                Synonym(language = "en", text = "posses 4"),
                                                Synonym(language = "en", text = "posses 5"),
                                            )
                                        ),
                                        Sense(
                                            synonyms = listOf(
                                                Synonym(language = "en", text = "posses 6"),
                                                Synonym(language = "en", text = "posses 7"),
                                                Synonym(language = "en", text = "posses 8"),
                                                Synonym(language = "en", text = "posses 9"),
                                                Synonym(language = "en", text = "posses 10"),
                                                Synonym(language = "en", text = "posses 11"),
                                            )
                                        ),
                                        Sense(
                                            antonyms = listOf(
                                                Antonym(language = "en", text = "be bereft of")
                                            ),
                                            synonyms = listOf(
                                                Synonym(language = "en", text = "posses 12"),
                                                Synonym(language = "en", text = "posses 13"),
                                                Synonym(language = "en", text = "posses 14"),
                                                Synonym(language = "en", text = "posses 15"),
                                                Synonym(language = "en", text = "posses 16"),
                                                Synonym(language = "en", text = "posses 17"),
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            ),
            word = "have"
        )
        val uiState = ThesaurusUiState.Success(word)
        ThesaurusScreen(uiState = uiState, onRetryClick = {})
    }
}
