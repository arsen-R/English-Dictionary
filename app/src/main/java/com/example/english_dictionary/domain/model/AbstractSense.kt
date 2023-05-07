package com.example.english_dictionary.domain.model

abstract class AbstractSense {
    abstract val antonyms: List<Antonym>?
    abstract val definitions: List<String>?
    abstract val domainClasses: List<DomainClasse>?
    abstract val examples: List<Example>?
    abstract val id: String?
    abstract val notes: List<Note>?
    abstract val regions: List<Region>?
    abstract val registers: List<Register>?
    abstract val shortDefinitions: List<String>?
    abstract val thesaurusLinks: List<ThesaurusLink>?
    abstract val variantForms: List<VariantForm>?
    abstract val synonyms: List<Synonym>?
}

