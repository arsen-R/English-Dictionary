package com.example.english_dictionary.data.mapper

import com.example.english_dictionary.data.database.entity.WordEntity
import com.example.english_dictionary.data.database.entity.WordSearchEntity
import com.example.english_dictionary.data.network.dto.*
import com.example.english_dictionary.domain.model.*

fun MetadataDto.toMetadata(): Metadata {
    return Metadata(
        limit = this.limit,
        offset = this.offset,
        operation = this.operation,
        provider = this.provider,
        schema = this.schema,
        sourceLanguage = this.sourceLanguage,
        total = this.total
    )
}

fun WordSearchDto.toWordSearch(): WordSearch {
    return WordSearch(
        id = this.id,
        label = this.label,
        matchString = this.matchString,
        matchType = this.matchType,
        region = this.region,
        score = this.score,
        word = this.word
    )
}

fun WordSearchEntity.toWordSearch(): WordSearch {
    return WordSearch(
        id = this.id,
        label = this.label,
        matchString = this.matchString,
        matchType = this.matchType,
        region = this.region,
        score = this.score,
        word = this.word
    )
}

fun WordSearch.toWordSearchEntity(): WordSearchEntity {
    return WordSearchEntity(
        id = this.id!!,
        label = this.label,
        matchString = this.matchString,
        matchType = this.matchType,
        region = this.region,
        score = this.score,
        word = this.word
    )
}

fun WordSearchResultDto.toWordSearchResult(): WordSearchResult {
    return WordSearchResult(
        metadata = this.metadata?.toMetadata(),
        results = this.results?.map { it?.toWordSearch() }
    )
}

fun DomainClasseDto.toDomainClasse(): DomainClasse {
    return DomainClasse(
        id = this.id,
        text = this.text
    )
}

fun EntryDto.toEntry(): Entry {
    return Entry(
        etymologies = this.etymologies,
        grammaticalFeatures = this.grammaticalFeatures?.map { it.toGrammaticalFeature() },
        inflections = this.inflections?.map { it.toInflection() },
        notes = this.notes?.map { it.toNote() },
        pronunciations = this.pronunciations?.map { it.toPronunciation() },
        senses = this.senses?.map { it.toSense() }
    )
}

fun ExampleDto.toExample(): Example {
    return Example(
        notes = this.notes?.map { it.toNote() },
        text = this.text
    )
}

fun GrammaticalFeatureDto.toGrammaticalFeature(): GrammaticalFeature {
    return GrammaticalFeature(
        id = this.id,
        text = this.text,
        type = this.type
    )
}

fun InflectionDto.toInflection(): Inflection {
    return Inflection(
        grammaticalFeatures = this.grammaticalFeatures?.map { it.toGrammaticalFeature() },
        inflectedForm = this.inflectedForm,
        pronunciations = this.pronunciations?.map { it.toPronunciation() }
    )
}

fun LexicalCategoryDto.toLexicalCategory(): LexicalCategory {
    return LexicalCategory(
        id = this.id, text = this.text
    )
}

fun LexicalEntryDto.toLexicalEntry(): LexicalEntry {
    return LexicalEntry(
        entries = this.entries?.map { it.toEntry() },
        language = this.language,
        lexicalCategory = this.lexicalCategory?.toLexicalCategory(),
        phrasalVerbs = this.phrasalVerbs?.map { it.toPhrasalVerb() },
        phrases = this.phrases?.map { it.toPhrase() },
        text = this.text
    )
}

fun NoteDto.toNote(): Note {
    return Note(
        text = this.text,
        type = this.type
    )
}

fun PhrasalVerbDto.toPhrasalVerb(): PhrasalVerb {
    return PhrasalVerb(
        id = this.id, text = this.text
    )
}

fun PhraseDto.toPhrase(): Phrase {
    return Phrase(
        id = this.id,
        text = this.text
    )
}

fun PronunciationDto.toPronunciation(): Pronunciation {
    return Pronunciation(
        audioFile = this.audioFile,
        dialects = this.dialects,
        phoneticNotation = this.phoneticNotation,
        phoneticSpelling = this.phoneticSpelling
    )
}

fun RegionDto.toRegion(): Region {
    return Region(
        id = this.id,
        text = this.text
    )
}

fun RegisterDto.toRegister(): Register {
    return Register(
        id = this.id,
        text = this.text
    )
}

fun WordResultDto.toWordResult(): WordResult {
    return WordResult(
        id = this.id,
        language = this.language,
        lexicalEntries = this.lexicalEntries?.map { it.toLexicalEntry() },
        type = this.type,
        word = this.word
    )
}

fun SemanticClasseDto.toSematicClasse(): SemanticClasse {
    return SemanticClasse(
        id = this.id,
        text = this.text
    )
}

fun SenseDto.toSense(): Sense {
    return Sense(
        crossReferenceMarkers = this.crossReferenceMarkers,
        crossReferences = this.crossReferences?.map { it.toCrossReference() },
        definitions = this.definitions,
        domainClasses = this.domainClasses?.map { it.toDomainClasse() },
        examples = this.examples?.map { it.toExample() },
        id = this.id,
        notes = this.notes?.map { it.toNote() },
        regions = this.regions?.map { it.toRegion() },
        registers = this.registers?.map { it.toRegister() },
        semanticClasses = this.semanticClasses?.map { it.toSematicClasse() },
        shortDefinitions = this.shortDefinitions,
        subsenses = this.subsenses?.map { it.toSubsense() },
        synonyms = this.synonyms?.map { it.toSynonym() },
        thesaurusLinks = this.thesaurusLinks?.map { it.toThesaurusLink() },
        variantForms = this.variantForms?.map { it.toVariantForm() },
        antonyms = this.antonyms?.map { it.toAntonym() }
    )
}

fun SubsenseDto.toSubsense(): Subsense {
    return Subsense(
        definitions = this.definitions,
        domainClasses = this.domainClasses?.map { it.toDomainClasse() },
        examples = this.examples?.map { it.toExample() },
        id = this.id,
        notes = this.notes?.map { it.toNote() },
        regions = this.regions?.map { it.toRegion() },
        registers = this.registers?.map { it.toRegister() },
        shortDefinitions = this.shortDefinitions,
        synonyms = this.synonyms?.map { it.toSynonym() },
        thesaurusLinks = this.thesaurusLinks?.map { it.toThesaurusLink() },
        variantForms = this.variantForms?.map { it.toVariantForm() },
        antonyms = this.antonyms?.map { it.toAntonym() }
    )
}

fun SynonymDto.toSynonym(): Synonym {
    return Synonym(
        language = this.language,
        text = this.text
    )
}

fun ThesaurusLinkDto.toThesaurusLink(): ThesaurusLink {
    return ThesaurusLink(
        entry_id = this.entry_id,
        sense_id = this.sense_id
    )
}

fun VariantFormDto.toVariantForm(): VariantForm {
    return VariantForm(
        text = this.text
    )
}

fun WordDto.toWord(): Word {
    return Word(
        id = this.id,
        metadata = this.metadata?.toWordMetadata(),
        results = this.results?.map { it.toWordResult() },
        word = this.word
    )
}

fun WordMetadataDto.toWordMetadata(): WordMetadata {
    return WordMetadata(
        operation = this.operation,
        provider = this.provider,
        schema = this.schema
    )
}

fun CrossReferenceDto.toCrossReference(): CrossReference {
    return CrossReference(
        id = this.id,
        text = this.text,
        type = this.type
    )
}

fun AntonymDto.toAntonym(): Antonym {
    return Antonym(
        language = this.language, text = this.text
    )
}

fun Word.toWordEntity(): WordEntity {
    return WordEntity(
        id = this.id!!,
        word = this.word
    )
}

fun WordEntity.toWord(): Word {
    return Word(
        id = this.id,
        word = this.word
    )
}