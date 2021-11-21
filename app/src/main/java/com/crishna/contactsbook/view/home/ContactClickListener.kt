package com.crishna.contactsbook.view.home

import com.crishna.contactsbook.model.Person

interface ContactClickListener {

    fun CallContact(person: Person)

    fun OpenContactInfo(person: Person)
}