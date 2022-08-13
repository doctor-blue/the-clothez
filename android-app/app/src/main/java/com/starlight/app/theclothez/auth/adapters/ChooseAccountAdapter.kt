package com.starlight.app.theclothez.auth.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChooseAccountAdapter : RecyclerView.Adapter<ChooseAccountViewHolder>() {

    private var accounts: List<String> = listOf(
        "Ahehe",
        "Ahoho",
        "Ahaha",
        "Ahkhk",
        "Ahuhu",
        "Ahahi",
        "Ahiha",
        "Ahiho"
    )

    private var options: List<ChooseAccountItem> = listOf(
        ChooseAccountItem("Guest", "Login as Guest"),
        ChooseAccountItem("+", "Create new account")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseAccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChooseAccountViewHolder.create(inflater, parent)
    }

    override fun onBindViewHolder(holder: ChooseAccountViewHolder, position: Int) {
        if (position >= accounts.size) {
            holder.onBindOtherOptions(options[position - accounts.size])
            Log.d("AAAAAA", position.toString())
        } else {
            holder.onBind(accounts[position])
            Log.d("AAAAAA", position.toString())
        }

    }

    override fun getItemCount(): Int = accounts.size + 2
}

data class ChooseAccountItem(
    val firstChar: String,
    val text: String,
)