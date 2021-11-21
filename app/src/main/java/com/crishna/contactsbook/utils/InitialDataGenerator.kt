package com.crishna.contactsbook.utils

import com.crishna.contactsbook.model.Person

class InitialDataGenerator {

    companion object{
        fun getInitialContacts() : List<Person>{
            return listOf(
                Person("Sai","Krishna","crishnakorukanti@gmail.com","Personal",9505588009),
                Person("Lloyd","Dcosta","crishnakorukanti@gmail.com","Business",6300675220),
                Person("Manjula","Rao","crishnakorukanti@gmail.com","Personal",8501258963),
                Person("Muktha","Rao","crishnakorukanti@gmail.com","Business",9874561230),
                Person("Crishna","Korukanti","crishnakorukanti@gmail.com","Business",7894561230),
            )
        }
    }
}