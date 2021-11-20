package com.crishna.contactsbook.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.crishna.contactsbook.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class DataBaseContacts : RoomDatabase() {

    abstract val contactsDao: DaoContacts

    companion object {
        private var INSTANCE: DataBaseContacts? = null

        fun getInstance(context: Context): DataBaseContacts {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, DataBaseContacts::class.java, "Contacts_DB")
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}