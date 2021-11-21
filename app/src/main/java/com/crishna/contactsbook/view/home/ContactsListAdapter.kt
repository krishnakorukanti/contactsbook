package com.crishna.contactsbook.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crishna.contactsbook.databinding.ItemContactsBinding
import com.crishna.contactsbook.model.Person


class ContactsListAdapter(var contactsList: List<Person>) :
    RecyclerView.Adapter<ContactsListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsListViewHolder {
        val binding =
            ItemContactsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactsListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsListViewHolder, position: Int) {
        holder.setContactsData(contactsList.get(position))
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    fun updateContactsList(contactsList: List<Person>) {
        this.contactsList = contactsList
        notifyDataSetChanged()
    }
}