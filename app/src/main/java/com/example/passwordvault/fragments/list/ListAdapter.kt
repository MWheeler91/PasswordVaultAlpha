package com.example.passwordvault.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.passwordvault.R
import com.example.passwordvault.data.Account
import kotlinx.android.synthetic.main.custom_row.view.*

// list adapter is inheriting from Recycler view
class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    // empty of Account objects
    private var accountList = emptyList<Account>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // returns the row layout
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }
        // gets the size of the list
    override fun getItemCount(): Int {
        return accountList.size
    }
        // This function is pulling the data from the database's current item and assigning the value
        // to the elements on the row layout
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = accountList[position]
        holder.itemView.tvListTitle.text = currentItem.title
        holder.itemView.tvListUserId.text = currentItem.username
        //holder.itemView.tvListPassword.text = currentItem.password
        //holder.itemView.tvListWebsite.text = currentItem.website
    }

    fun setData(account: List<Account>){
        // updates the account list to the new values being passed in
        this.accountList = account
        // Notify any registered observers that the item at position has changed with an optional payload object.
        notifyDataSetChanged()
    }


}