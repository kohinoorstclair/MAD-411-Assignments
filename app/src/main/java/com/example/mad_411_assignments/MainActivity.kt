package com.example.mad_411_assignments

import ExpenseViewListAdapter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var ExpenseNameInput: EditText
    private lateinit var ExpenseAmountInput: EditText

    private lateinit var ExpenseDateInput: EditText
    private lateinit var addExpenseButton: Button
     lateinit var expenseList: RecyclerView
    private lateinit var expenseAdapter: ExpenseViewListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// initialised views

        ExpenseNameInput = findViewById(R.id.expense_name)
        ExpenseAmountInput = findViewById(R.id.expense_amount)
        ExpenseDateInput = findViewById(R.id.expense_date)


        addExpenseButton = findViewById(R.id.add_expense)
        expenseList = findViewById(R.id.ExpenseList)
        expenseAdapter = ExpenseViewListAdapter(mutableListOf())
        expenseList.adapter = expenseAdapter

        expenseList.layoutManager = LinearLayoutManager(this)


        addExpenseButton.setOnClickListener {
            val name = ExpenseNameInput.text.toString()
            val amount = ExpenseAmountInput.text.toString()
            val date = ExpenseDateInput.text.toString()
            if (name.isNotBlank() && amount.isNotBlank()) {
                expenseAdapter.addExpense(Expense(name, amount, date))
                ExpenseNameInput.text.clear()
                ExpenseAmountInput.text.clear()
                ExpenseDateInput.text.clear()
            }
        }
    }
}