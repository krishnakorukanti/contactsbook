package com.crishna.contactsbook.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.crishna.contactsbook.model.Person
import com.crishna.contactsbook.room.DataBaseContacts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OperationsRepo(private val context: Context) {

    fun addContacts(
        firstname: String?,
        lastname: String?,
        email: String?,
        type: String?,
        phone: Long?

        ) {
        CoroutineScope(Dispatchers.IO).launch {
            val person = Person(firstname, lastname, email, type, phone)
            DataBaseContacts.getInstance(context).contactsDao.InsertContact(person)
        }
    }

    fun getAllContacts(): LiveData<List<Person>> {
        return DataBaseContacts.getInstance(context).contactsDao.getContacts()
    }
}