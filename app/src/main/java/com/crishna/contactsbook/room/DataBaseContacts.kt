package com.crishna.contactsbook.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.crishna.contactsbook.model.Person
import com.crishna.contactsbook.utils.InitialDataGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = [Person::class], version = 1, exportSchema = false)
abstract class DataBaseContacts : RoomDatabase() {

    abstract fun contactsDao(): DaoContacts

    companion object {
        @Volatile private var instance: DataBaseContacts? = null

        fun getInstance(context: Context): DataBaseContacts {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): DataBaseContacts {
            return Room.databaseBuilder(context, DataBaseContacts::class.java, "Contacts_DB")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //pre-populate data
                        Executors.newSingleThreadExecutor().execute {
                            instance?.let {

                                CoroutineScope(Dispatchers.IO).launch {
                                    it.contactsDao()
                                        .InsertContactList(InitialDataGenerator.getInitialContacts())
                                }
                            }

                        }
                    }

                })
                .build()
        }
    }


}