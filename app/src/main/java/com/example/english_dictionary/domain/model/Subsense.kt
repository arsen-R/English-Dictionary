package com.example.english_dictionary.domain.model

data class Subsense(
    override val definitions: List<String>? = null,
    override val domainClasses: List<DomainClasse>? = null,
    override val examples: List<Example>? = null,
    override val id: String? = null,
    override val notes: List<Note>? = null,
    override val regions: List<Region>? = null,
    override val registers: List<Register>? = null,
    override val shortDefinitions: List<String>? = null,
    override val synonyms: List<Synonym>? = null,
    override val thesaurusLinks: List<ThesaurusLink>? = null,
    override val variantForms: List<VariantForm>? = null,
    override val antonyms: List<Antonym>? = null,
) : AbstractSense()