package com.example.mad_411_assignments.fragments

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.mad_411_assignments.model.Expense
import com.example.mad_411_assignments.R

class ExpenseRowView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // setting views to variables


     val nameText: TextView = itemView.findViewById(R.id.name)

     val amountText: TextView = itemView.findViewById(R.id.amount)
     val dateText: TextView = itemView.findViewById(R.id.date)

     val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
    val showDetailsButton: Button = itemView.findViewById(R.id.showDetails)

    // made a function to create the row inside the recycle view
    fun createView(expense: Expense, onDelete: (Int) -> Unit) {
        nameText.text = expense.name
        amountText.text = "$${expense.amount}"
        dateText.text = expense.date

        deleteButton.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onDelete(adapterPosition)
            }

        }

        showDetailsButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("name", expense.name)
                putString("amount", expense.amount)
                putString("date", expense.date)
                putString("code",expense.CurrencyCode)
                putString("code",expense.ConvertedCurrency.toString())
            }
            itemView.findNavController().navigate(R.id.action_expenseListFragment_to_expenseDetailsFragment, bundle)
        }

    }
}