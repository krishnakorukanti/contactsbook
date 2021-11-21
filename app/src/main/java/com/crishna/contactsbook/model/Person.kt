package com.crishna.contactsbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class Person(

    @ColumnInfo(name = "firstname")
    var firstname: String,

    @ColumnInfo(name = "lastname")
    var lastname: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "type")
    var type: String,

    @ColumnInfo(name = "phone")
    var phone: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var userId: Int = 0
}
