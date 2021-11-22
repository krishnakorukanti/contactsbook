package com.crishna.contactsbook.view.update

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crishna.contactsbook.databinding.ActivityCreateContactBinding
import com.crishna.contactsbook.model.Person
import com.crishna.contactsbook.viewmodel.CreateViewModel
import com.crishna.contactsbook.viewmodel.CreateViewModelFactory

class EditContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateContactBinding
    private lateinit var createViewModel: CreateViewModel
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createViewModel = CreateViewModelFactory(this).create(CreateViewModel::class.java)
        getContactDetails()
        initViews()
    }

    private fun getContactDetails() {
         id = intent.getIntExtra("id", 1)
        createViewModel.getContactDetail(id).observe(this, { person ->

            binding.apply {
                if (person != null) {
                    if (person.type == "Personal")
                        filterPersonal.isChecked = true
                    else
                        filterBusiness.isChecked = true
                    firstNameET.setText(person.firstname)
                    lastNameET.setText(person.lastname)
                    emailET.setText(person.email)
                    phoneET.setText(person.phone.toString())
                }
            }
        })
    }

    private fun initViews() {
        binding.apply {
            saveBtn.setOnClickListener {
                if (isDataValid()) {
                    val type: String = getContactType()
                    val person = Person(firstNameET.text.toString(),
                        lastNameET.text.toString(),
                        emailET.text.toString(),
                        type,
                        phoneET.text.toString().toLong())
                   person.userId = id
                    createViewModel.udpateContact(person)
                    finish()
                }
            }

        }
    }
    private fun getContactType(): String {
        if (binding.filterPersonal.isChecked)
            return "Personal"
        return "Business"


    }
    private fun isDataValid(): Boolean {
        binding.apply {
            if (phoneET.text.isNullOrEmpty() && !android.util.Patterns.PHONE.matcher(phoneET.text)
                    .matches()
            ) {
                return false
            }
            if (emailET.text.isNullOrEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(emailET.text)
                    .matches()
            ) {
                return false
            }
            if (firstNameET.text.isNullOrEmpty()) {
                return false
            }
            if (lastNameET.text.isNullOrEmpty()) {
                return false
            }
            return true
        }
    }
}