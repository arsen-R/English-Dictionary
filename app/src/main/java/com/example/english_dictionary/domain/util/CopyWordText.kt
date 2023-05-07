package com.example.english_dictionary.domain.util

import com.example.english_dictionary.domain.model.Word

fun copyWordText(word: Word): String {
    val wordText = StringBuilder()
    wordText.append("${word.word}, ")
    word.results?.forEach { result ->
        result.lexicalEntries?.forEach { lexical ->
            lexical.entries?.forEach { entry ->
                entry.pronunciations?.filter {
                    it.phoneticNotation == "IPA"
                }?.forEach { pronunciation ->
                    wordText.append("${pronunciation.phoneticSpelling}, ")
                }
                entry.senses?.forEach { sense ->
                    sense.variantForms?.get(0)?.text?.let {
                        wordText.append("(Also ")
                        wordText.append(it)
                        wordText.append(") ")
                    }
                    sense.notes?.get(0)?.let { note ->
                        if (note.type == "grammaticalNote") {
                            wordText.append("[${note.text}] ")
                        }
                        if (note.type == "wordFormNote") {
                            wordText.append("(")
                            wordText.append("${note.text}")
                            wordText.append(") ")
                        }
                    }
                    sense.registers?.get(0)?.let {
                        wordText.append("${it.text}")
                    }
                    sense.regions?.get(0)?.let {
                        val regionSplit = it.text?.split("_")
                        for (region in regionSplit!!) {
                            wordText.append("$region ")
                        }
                    }
                    sense.definitions?.let { definition ->
                        wordText.append(" ${definition[0]}")
                    }
                }
            }
        }
    }
    return wordText.toString()
}