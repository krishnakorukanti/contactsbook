package com.crishna.contactsbook.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.crishna.contactsbook.model.Person

@Dao
interface DaoContacts {

    @Insert
    suspend fun InsertContact(person: Person)


    @Insert
    suspend fun InsertContactList(contactList: List<Person>)

    @Query("Select * From Contacts")
    fun getContacts(): LiveData<List<Person>>

    @Query("Select * From Contacts Where type like :filterType")
    fun filterContacts(filterType: String): LiveData<List<Person>>

    @Query("Select * From Contacts where id=:id")
    fun getContact(id: Int): LiveData<Person>

    @Delete
    fun deleteContact(person: Person)
}