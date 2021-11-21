package com.crishna.contactsbook.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.crishna.contactsbook.databinding.ActivityMainBinding
import com.crishna.contactsbook.model.Person
import com.crishna.contactsbook.view.home.ContactsListAdapter
import com.crishna.contactsbook.viewmodel.HomeViewModel
import com.crishna.contactsbook.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {

    private var contactList = emptyList<Person>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var contactsListAdapter: ContactsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeViewModel = HomeViewModelFactory(this).create(HomeViewModel::class.java)
        initViews()
        setContactsList()
        getAllContacts()

    }

    private fun setContactsList() {
        contactsListAdapter = ContactsListAdapter(contactList)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.contactsListRecycler.apply {
            layoutManager = linearLayoutManager
            adapter = contactsListAdapter
        }
        if (contactList.size < 1) {
            binding.noContactsTV.visibility = View.VISIBLE
        } else {
            binding.noContactsTV.visibility = View.GONE
        }
    }

    private fun getAllContacts() {
        homeViewModel.getAllContacts().observe(this, {

        })
    }

    private fun initViews() {

    }
}