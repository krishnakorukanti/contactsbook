package com.crishna.contactsbook.room

import androidx.lifecycle.LiveData
import androidx.room.*
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

    @Update
    suspend fun updateContact(person: Person)

    @Query("Select * from Contacts where firstname Like (:searchQuery) or lastname Like (:searchQuery)")
    fun searchContacts(searchQuery : String) : LiveData<List<Person>>

}