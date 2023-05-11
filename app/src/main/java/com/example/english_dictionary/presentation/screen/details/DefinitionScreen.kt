package com.example.english_dictionary.presentation.screen.details

import android.content.res.Configuration
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.english_dictionary.R
import com.example.english_dictionary.domain.model.Entry
import com.example.english_dictionary.domain.model.Example
import com.example.english_dictionary.domain.model.LexicalEntry
import com.example.english_dictionary.domain.model.Note
import com.example.english_dictionary.domain.model.Region
import com.example.english_dictionary.domain.model.Register
import com.example.english_dictionary.domain.model.Sense
import com.example.english_dictionary.domain.model.VariantForm
import com.example.english_dictionary.domain.model.Word
import com.example.english_dictionary.domain.model.WordResult
import com.example.english_dictionary.domain.util.CopyManager
import com.example.english_dictionary.domain.util.copyWordText
import com.example.english_dictionary.presentation.component.DefinitionSenseRow
import com.example.english_dictionary.presentation.component.ErrorMessageButton
import com.example.english_dictionary.presentation.component.InflectionRow
import com.example.english_dictionary.presentation.component.LoadingProgress
import com.example.english_dictionary.presentation.component.PronunciationsRow
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import com.example.english_dictionary.ui.theme.LightBlue

@Composable
fun DefinitionScreen(
    modifier: Modifier = Modifier,
    viewModel: WordDetailViewModel = hiltViewModel()
) {
    val definitionUiState = viewModel.definitionUiState.collectAsStateWithLifecycle().value
    DefinitionScreen(
        modifier = modifier,
        uiState = definitionUiState,
        onRetryClick = { viewModel.getDefinitionWord() },
        onAddWord = viewModel::addWord,
        onRemoveWord = viewModel::removeWord,
        isWordSaved = viewModel::isSavedWord
    )
}

@Composable
internal fun DefinitionScreen(
    modifier: Modifier = Modifier,
    uiState: DefinitionUiState,
    onRetryClick: () -> Unit,
    onAddWord: (Word) -> Unit,
    onRemoveWord: (String) -> Unit,
    isWordSaved: (String) -> Boolean
) {
    val context = LocalContext.current
    when (uiState) {
        is DefinitionUiState.Loading -> {
            LoadingProgress()
        }

        is DefinitionUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(all = 5.dp)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            space = 5.dp
                        )
                    ) {
                        with(uiState.word) {
                            val isWordSaved =
                                remember { mutableStateOf(isWordSaved(uiState.word.id!!)) }

                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(
                                    5.dp,
                                    alignment = Alignment.End
                                ),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconToggleButton(
                                    checked = isWordSaved.value,
                                    onCheckedChange = {
                                        isWordSaved.value = !isWordSaved.value
                                        if (isWordSaved.value) {
                                            onAddWord(uiState.word)
                                        } else {
                                            onRemoveWord(uiState.word.id!!)
                                        }
                                    },
                                    modifier = modifier.size(30.dp)
                                ) {
                                    Icon(
                                        painter = if (isWordSaved.value)
                                            painterResource(id = R.drawable.round_bookmark_24)
                                        else
                                            painterResource(id =
                                                R.drawable.round_bookmark_border_24),
                                        contentDescription = null
                                    )
                                }

                                IconButton(
                                    modifier = modifier.size(30.dp),
                                    onClick = {
                                        val copyManager = CopyManager()
                                        copyManager.copyToClickBoard(
                                            context,
                                            copyWordText(uiState.word)
                                        )

                                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
                                            Toast.makeText(context, "Copied", Toast.LENGTH_LONG)
                                                .show()
                                        }
                                    },
                                ) {
                                    Icon(
                                        painter =
                                        painterResource(id = R.drawable.round_content_copy_24),
                                        contentDescription = null
                                    )
                                }
                            }
                            Divider()
                            results?.forEach {
                                it.lexicalEntries?.forEach { lexical ->
                                    lexical.text?.let { text ->
                                        Text(
                                            text = text,
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    PronunciationsRow(lexical = lexical)
                                    Text(
                                        text = "${lexical.lexicalCategory?.text}",
                                        fontWeight = FontWeight.Bold,
                                        color = LightBlue
                                    )
                                    lexical.entries?.forEach { entry ->
                                        InflectionRow(entry = entry)
                                        DefinitionSenseRow(entry = entry)
                                    }
                                    Divider()
                                }
                                val etymology =
                                    it.lexicalEntries?.get(0)?.entries?.get(0)?.etymologies?.get(0)
                                etymology?.let {
                                    Text(text = buildAnnotatedString {
                                        withStyle(
                                            style = SpanStyle(
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold
                                            )

                                        ) {
                                            append("Origin: ")
                                        }
                                        append(it)
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }

        is DefinitionUiState.Error -> {
            ErrorMessageButton(
                onRetryClick = {
                    onRetryClick()
                })
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
fun DefinitionScreenPreview() {
    EnglishDictionaryTheme {
        val word = Word(
            results = listOf(
                WordResult(
                    lexicalEntries = listOf(
                        LexicalEntry(
                            entries = listOf(
                                Entry(
                                    senses = listOf(
                                        Sense(
                                            definitions = listOf(
                                                "possess, own, or hold"
                                            ),
                                            variantForms = listOf(
                                                VariantForm(text = "have got")
                                            ),
                                            examples = listOf(
                                                Example(text = "he had a new car and a boat"),
                                                Example(text = "have you got a job yet?"),
                                            )
                                        ),
                                        Sense(
                                            definitions = listOf(
                                                "experience; undergo"
                                            ),
                                            examples = listOf(
                                                Example(
                                                    text = "I went to a few parties and " +
                                                            "had a good time"
                                                ),
                                                Example(
                                                    text = "I was having difficulty in " +
                                                            "keeping awake"
                                                ),
                                            )
                                        ),
                                        Sense(
                                            definitions = listOf(
                                                "be obliged or find it necessary to do the " +
                                                        "specified thing"
                                            ),
                                            notes = listOf(
                                                Note(
                                                    text = "\"the haves\"",
                                                    type = "wordFormNote"
                                                )
                                            ),
                                            regions = listOf(
                                                Region(
                                                    id = "north_american",
                                                    text = "North_America"
                                                )
                                            ),
                                            registers = listOf(
                                                Register(
                                                    id = "informal",
                                                    text = "Informal"
                                                )
                                            ),
                                            examples = listOf(
                                                Example(
                                                    text = "you don't have to accept " +
                                                            "this situation"
                                                ),
                                                Example(text = "we've got to plan for the future"),
                                            )
                                        )
                                    )
                                ),
                                Entry(
                                    senses = listOf(
                                        Sense(
                                            definitions = listOf(
                                                "experience; undergo"
                                            )
                                        )
                                    )
                                ),
                            )
                        )
                    )
                )
            ),
            word = "have"
        )
        val uiState = DefinitionUiState.Success(word)
        DefinitionScreen(
            uiState = uiState,
            onRetryClick = {},
            onAddWord = {},
            onRemoveWord = {},
            isWordSaved = {
                false
            }
        )
    }
}