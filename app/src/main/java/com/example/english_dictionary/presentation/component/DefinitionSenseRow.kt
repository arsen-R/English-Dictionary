package com.example.english_dictionary.presentation.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.english_dictionary.domain.model.AbstractSense
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
import com.example.english_dictionary.domain.util.capitalize
import com.example.english_dictionary.ui.theme.EnglishDictionaryTheme
import com.example.english_dictionary.ui.theme.ForestGreen
import com.example.english_dictionary.ui.theme.LightBlue

@Composable
fun DefinitionSenseRow(
    modifier: Modifier = Modifier,
    entry: Entry
) {
    Column(modifier = modifier) {
        entry.senses?.forEachIndexed { index, sense ->
            WordSenseRow(indexWordSense = "${index + 1}.", annotatedString = buildAnnotatedString {
                append(annotatedStringForWordSense(sense))
                entry.notes?.get(0)?.let { note ->
                    if (note.type == "grammaticalNote") {
                        append("[${note.text}] ")
                    } else if (note.type == "wordFormNote") {
                        append("(")
                        withStyle(style = SpanStyle(color = LightBlue)) {
                            append("${note.text}")
                        }
                        append(") ")
                    }
                }
                sense.crossReferenceMarkers?.let { crossReference ->
                    append(" ${crossReference[0]}")
                }
            })
            sense.examples?.forEach { example ->
                ExampleTextItem(example = example)
            }
            sense.subsenses?.let { subsenses ->
                subsenses.forEachIndexed { index, subsense ->
                    WordSenseRow(
                        modifier = modifier.padding(horizontal = 5.dp),
                        indexWordSense = "${index + 1})",
                        annotatedString = annotatedStringForWordSense(subsense)
                    )
                    subsense.examples?.forEach { example ->
                        ExampleTextItem(example = example)
                    }
                }
            }
        }
    }
}

@Composable
fun WordSenseRow(
    modifier: Modifier = Modifier,
    indexWordSense: String,
    annotatedString: AnnotatedString
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(space = 3.dp)
    ) {
        Text(
            text = indexWordSense,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = annotatedString
        )
    }
}
@Composable
fun ExampleTextItem(
    modifier: Modifier = Modifier,
    example: Example,
) {
    example.text?.let { exampleText ->
        Text(
            text = exampleText.capitalize(),
            fontStyle = FontStyle.Italic,
            modifier = modifier.padding(horizontal = 10.dp)
        )
    }
}

fun annotatedStringForWordSense(sense: AbstractSense): AnnotatedString {
    val text = buildAnnotatedString {
        sense.variantForms?.get(0)?.text?.let {
            append("(Also ")
            withStyle(style = SpanStyle(color = LightBlue)) {
                append(it)
            }
            append(") ")
        }
        sense.notes?.get(0)?.let { note ->
            if (note.type == "grammaticalNote") {
                append("[${note.text}] ")
            } else if (note.type == "wordFormNote") {
                append("(")
                withStyle(style = SpanStyle(color = LightBlue)) {
                    append("${note.text}")
                }
                append(") ")
            }
        }
        sense.registers?.get(0)?.let {
            withStyle(style = SpanStyle(color = ForestGreen)) {
                append("${it.text}")
            }
        }
        sense.regions?.get(0)?.let {
            withStyle(style = SpanStyle(color = ForestGreen)) {
                val regionSplit = it.text?.split("_")
                for (region in regionSplit!!) {
                    append("$region ")
                }
            }
        }
        sense.definitions?.let { definition ->
            append(" ${definition[0]}")
        }
    }

    return text
}

@Preview(name = "Light mode", showBackground = true)
@Preview(
    name = "Dark mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF212324
)
@Composable
fun DefinitionSenseRowPreview() {
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
            )
        )
        Column() {
            word.results?.forEach {
                it.lexicalEntries?.forEach { lexical ->
                    lexical.entries?.forEach { entry ->
                        DefinitionSenseRow(entry = entry)
                    }
                }
            }
        }
    }
}