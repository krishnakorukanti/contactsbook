package com.crishna.contactsbook.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.crishna.contactsbook.model.Person
import com.crishna.contactsbook.repository.OperationsRepo

class HomeViewModel(context: Context) : ViewModel() {
    private val operationsRepo = OperationsRepo(context)

    fun insertContacts(

        firstname: String,
        lastname: String,
        email: String,
        type: String,
        phone: Long
    ) {
        operationsRepo.addContacts(firstname, lastname, email, type, phone)
    }

    fun getAllContacts(): LiveData<List<Person>> {
        return operationsRepo.getAllContacts()
    }

    fun observeFilters(filter: String): LiveData<List<Person>> {
        return operationsRepo.filterContacts(filter)
    }
}