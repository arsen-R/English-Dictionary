package com.example.english_dictionary.domain.model

data class Sense(
    val crossReferenceMarkers: List<String>? = null,
    val crossReferences: List<CrossReference>? = null,
    override val definitions: List<String>? = null,
    override val domainClasses: List<DomainClasse>? = null,
    override val examples: List<Example>? = null,
    override val id: String? = null,
    override val notes: List<Note>? = null,
    override val regions: List<Region>? = null,
    override val registers: List<Register>? = null,
    val semanticClasses: List<SemanticClasse>? = null,
    override val shortDefinitions: List<String>? = null,
    val subsenses: List<Subsense>? = null,
    override val synonyms: List<Synonym>? = null,
    override val thesaurusLinks: List<ThesaurusLink>? = null,
    override val variantForms: List<VariantForm>? = null,
    override val antonyms: List<Antonym>? = null,
): AbstractSense()