package com.crishna.contactsbook.view.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crishna.contactsbook.databinding.ActivityCreateContactBinding
import com.crishna.contactsbook.viewmodel.CreateViewModel
import com.crishna.contactsbook.viewmodel.CreateViewModelFactory

class CreateContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateContactBinding
    private lateinit var createViewModel: CreateViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createViewModel = CreateViewModelFactory(this).create(CreateViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            saveBtn.setOnClickListener {
                if (isDataValid()) {
                    val type: String = getContactType()
                    createViewModel.insertContacts(
                        firstNameET.text.toString(),
                        lastNameET.text.toString(),
                        emailET.text.toString(),
                        type,
                        phoneET.text.toString().toLong()
                    )
                    finish()
                }
            }
            topAppBar.setNavigationOnClickListener {
                finish()
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