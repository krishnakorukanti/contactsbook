package com.crishna.contactsbook.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.crishna.contactsbook.model.Person
import com.crishna.contactsbook.repository.OperationsRepo

class CreateViewModel(context: Context) : ViewModel() {
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

    fun getContactDetail(id : Int): LiveData<Person> {
       return operationsRepo.getContactData(id)
    }

    fun deleteContact(person: Person){
        operationsRepo.deleteContact(person)
    }
    fun udpateContact(person: Person){
        operationsRepo.updateContact(person)
    }
}