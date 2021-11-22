package com.crishna.contactsbook.view.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.crishna.contactsbook.databinding.ActivityMainBinding
import com.crishna.contactsbook.model.Person
import com.crishna.contactsbook.view.create.CreateContactActivity
import com.crishna.contactsbook.view.info.ContactInfoActivity
import com.crishna.contactsbook.viewmodel.HomeViewModel
import com.crishna.contactsbook.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity(), ContactClickListener {

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
        contactsListAdapter = ContactsListAdapter(contactList,this)
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
            contactList = it
            setContactsList()
            contactsListAdapter.updateContactsList(it)
        })

    }

    private fun initViews() {

        binding.apply {
            radioFilters.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
                override fun onCheckedChanged(group: RadioGroup?, p1: Int) {
                    applyFilter(group?.getCheckedRadioButtonId())
                }

            })
            floatingAddContactBtn.setOnClickListener {
                val intent = Intent(this@MainActivity, CreateContactActivity::class.java)
                startActivity(intent)
            }

            searchContacts.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(search: String?): Boolean {
                    if (search!=null)
                        homeViewModel.searchContacts(search).observe(this@MainActivity,{
                            contactList = it
                            setContactsList()
                            contactsListAdapter.updateContactsList(it)
                        })
                    return false
                }

                override fun onQueryTextChange(search: String?): Boolean {
                    if (search!=null)
                    homeViewModel.searchContacts(search).observe(this@MainActivity,{
                        contactList = it
                        setContactsList()
                        contactsListAdapter.updateContactsList(it)
                    })
                    return false
                }

            })

        }
    }

    private fun applyFilter(checkedRadioButtonId: Int?) {
        when (checkedRadioButtonId) {

            binding.filterAll.id -> {
                getAllContacts()
            }
            binding.filterBusiness.id -> {
                homeViewModel.observeFilters(binding.filterBusiness.text.toString()).observe(this, {
                    contactList = it
                    setContactsList()
                    contactsListAdapter.updateContactsList(it)
                })

            }
            binding.filterPersonal.id -> {
                homeViewModel.observeFilters(binding.filterPersonal.text.toString()).observe(this, {
                    contactList = it
                    setContactsList()
                    contactsListAdapter.updateContactsList(it)
                })
            }
            else -> getAllContacts()
        }

    }

    override fun CallContact(person: Person) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", person.phone.toString(), null))
        startActivity(intent)

    }

    override fun OpenContactInfo(person: Person) {
        val intent = Intent(this, ContactInfoActivity::class.java)
        intent.putExtra("id", person.userId)
        startActivity(intent)
    }

}