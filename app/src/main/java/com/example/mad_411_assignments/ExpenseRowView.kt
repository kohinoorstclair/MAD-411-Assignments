package com.example.mad_411_assignments

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseRowView(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // setting views to variables


     val nameText: TextView = itemView.findViewById(R.id.name)

     val amountText: TextView = itemView.findViewById(R.id.amount)
     val dateText: TextView = itemView.findViewById(R.id.date)

     val deleteButton: Button = itemView.findViewById(R.id.deleteButton)

    // made a function to create the row inside the recycle view
    fun createView(expense: Expense, onDelete: (Int) -> Unit) {
        nameText.text = expense.name
        amountText.text = expense.amount
        dateText.text = expense.date

        deleteButton.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onDelete(adapterPosition)
            }

        }
    }
}