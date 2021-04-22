package com.example.passwordvault.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.passwordvault.R
import com.example.passwordvault.data.Account
import com.example.passwordvault.data.AccountViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.cbLowerCase
import kotlinx.android.synthetic.main.fragment_add.cbNumbers
import kotlinx.android.synthetic.main.fragment_add.cbSpecialChars
import kotlinx.android.synthetic.main.fragment_add.cbUpperCase
import kotlinx.android.synthetic.main.fragment_add.etNumOfCharacters
import kotlinx.android.synthetic.main.fragment_add.etPassword
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlin.random.Random


class AddFragment : Fragment() {

    private lateinit var mAccountViewModel: AccountViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        //
        mAccountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        view.btnSave.setOnClickListener {
            insertDataToDatabase()
        }

        view.btnGenerate.setOnClickListener{
            generatePassword()
        }

        return view
    }

    private fun insertDataToDatabase() {

        // textbox values
        val title = etTitle.text.toString()
        val userID = etUserid.text.toString()
        val password = etPassword.text.toString()
        val website = etWebsite.text.toString()


        // if it is true that title, id, password, and website have values
       if  (inputCheck(title, userID, password, website)){
            // creating account object
           val account = Account(0, title, userID, password, website)

           // add data to database
           mAccountViewModel.addAccount(account)
           Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
           // navigate to list  may or may not use this
           //findNavController().navigate(R.id.action_listFragment_to_addFragment)
       }
        // else something is empty tell the user
        else{
           Toast.makeText(requireContext(), "Please fill out all fields!", Toast.LENGTH_LONG).show()
       }
    }

    private fun inputCheck(title: String, userID: String, password: String, website: String): Boolean{
        // if anything is empty returns false otherwise return true
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(userID) || TextUtils.isEmpty(password) || TextUtils.isEmpty(website))
    }

    private fun generatePassword(){
        val numOfChars = etNumOfCharacters.text.toString().toInt()
        val builder = StringBuilder()
        var char = ""
        val passwordBuilder = StringBuilder()
        val upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val lower = "abcdefghijklmnopqrstuvwxyz"
        val nums = "0123456789"
        val specialchars = "!@#$%^&*()_+=-/.,;'[]?><:{}|"

        Log.d("Testnumofchars", numOfChars.toString())

        if (cbLowerCase.isChecked){
            builder.append(lower)
        }
        if (cbUpperCase.isChecked){
            builder.append(upper)
        }
        if (cbNumbers.isChecked){
            builder.append(nums)
        }
        if (cbSpecialChars.isChecked){
            builder.append(specialchars)
        }
        val chars = builder.toString()
        Log.d("char", chars)

        for (i in 0 until numOfChars){
            // generates a random number between 1 and length of character string
            val start = getRandomNumber(chars.length)
            // stop variable for the substring search
            val stop = start + 1
            char = chars.substring(start, stop)
            passwordBuilder.append(char)
            Log.d("password", passwordBuilder.toString())
        }
        val password = passwordBuilder.toString()
        etPassword.setText(password)
    }

    private fun getRandomNumber(length: Int): Int {
        return Random.nextInt(1, length)
    }

}