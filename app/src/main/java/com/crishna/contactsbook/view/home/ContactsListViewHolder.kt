package com.crishna.contactsbook.view.home

import androidx.recyclerview.widget.RecyclerView
import com.crishna.contactsbook.databinding.ItemContactsBinding
import com.crishna.contactsbook.model.Person

class ContactsListViewHolder(
    val binding: ItemContactsBinding,
    val contactClickListener: ContactClickListener
) :
    RecyclerView.ViewHolder(binding.root) {

    fun setContactsData(person: Person) {
        binding.apply {
            val name = "${person.firstname} ${person.lastname}"
            contactNameTV.text = name
            contactHolder.setOnClickListener {
                contactClickListener.OpenContactInfo(person)
            }
            callBtn.setOnClickListener {
                contactClickListener.CallContact(person)
            }


        }
    }
}