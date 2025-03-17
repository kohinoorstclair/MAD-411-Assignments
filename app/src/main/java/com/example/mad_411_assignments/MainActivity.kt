package com.example.mad_411_assignments

import android.os.Bundle
import android.view.View
import android.widget.Button

import androidx.appcompat.app.AppCompatActivity

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var ExpenseNameInput: EditText
    private lateinit var ExpenseAmountInput: EditText

    private lateinit var ExpenseDateInput: EditText
    private lateinit var addExpenseButton: Button
    private lateinit var expenseList: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ExpenseNameInput = findViewById(R.id.expense_name)
        ExpenseAmountInput = findViewById(R.id.expense_amount)
        ExpenseDateInput = findViewById(R.id.expense_date)


        addExpenseButton = findViewById(R.id.add_expense)
        expenseList = findViewById(R.id.ExpenseList)
    }


}