package com.crishna.contactsbook.view.info

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crishna.contactsbook.databinding.ActivityContactInfoBinding
import com.crishna.contactsbook.view.update.EditContactActivity
import com.crishna.contactsbook.viewmodel.CreateViewModel
import com.crishna.contactsbook.viewmodel.CreateViewModelFactory

class ContactInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactInfoBinding
    private lateinit var createViewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createViewModel = CreateViewModelFactory(this).create(CreateViewModel::class.java)
        getContactData()
        initViews()
    }

    private fun getContactData() {
        val id = intent.getIntExtra("id", 1)
        createViewModel.getContactDetail(id).observe(this, { person ->

            binding.apply {
                if (person != null) {
                    val fullName = "${person.firstname} ${person.lastname}"
                    val type = "( ${person.type} )"
                    contactCard.apply {

                        contactNameTV.text = fullName
                        contactEmail.text = person.email
                        contactTypeTV.text = type
                        contactPhone.text = person.phone.toString()

                        deleteContact.setOnClickListener {
                            createViewModel.deleteContact(person)
                            finish()

                        }
                        editContact.setOnClickListener {
                            val intent =
                                Intent(this@ContactInfoActivity, EditContactActivity::class.java)
                            intent.putExtra("id", person.userId)
                            startActivity(intent)
                        }
                    }
                }
            }
        })
    }

    private fun initViews() {
        binding.apply {
            topAppBar.setNavigationOnClickListener {
                finish()
            }
        }
    }
}