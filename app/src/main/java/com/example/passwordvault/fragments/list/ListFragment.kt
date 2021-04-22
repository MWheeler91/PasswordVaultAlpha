package com.example.passwordvault.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.passwordvault.R
import com.example.passwordvault.data.AccountViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {

    // accountViewModel Object
    private lateinit var mAccountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_list, container, false)


        // recyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        // assigns there recycler view the adapter object (ListAdapter.kt)
        recyclerView.adapter = adapter
        // layout allows the developer to create custom layouts.  Layoutmanager is required for recycler view.
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //userViewModel
        mAccountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)

        // ReadAll data is part Init block of the AccountViewModel Class
        // which traces all the way back to the Account Data Access Object (the Query)
        // This will query the database, and fill the
        mAccountViewModel.readAllData.observe(viewLifecycleOwner, { account ->
            adapter.setData(account)
        })

        //changes the view to new password fragment
        view.fabNewPassword.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view
    }


}