package com.crishna.contactsbook.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crishna.contactsbook.model.Person

@Dao
interface DaoContacts {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertContact(person: Person)

    @Query("SELECT * FROM Contacts")
     fun getContacts(): LiveData<List<Person>>
}