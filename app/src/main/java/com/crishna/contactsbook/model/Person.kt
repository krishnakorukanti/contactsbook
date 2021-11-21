package com.crishna.contactsbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contacts")
data class Person(
    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

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

}
