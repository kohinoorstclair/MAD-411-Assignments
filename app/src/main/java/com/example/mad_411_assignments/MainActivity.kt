package com.example.mad_411_assignments

import ExpenseViewListAdapter
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    private lateinit var ExpenseNameInput: EditText
    private lateinit var ExpenseAmountInput: EditText
    private lateinit var ExpenseDateInput: EditText
    private lateinit var DatePickerButton: Button
    private lateinit var DateText: TextView
    private lateinit var addExpenseButton: Button
     lateinit var expenseList: RecyclerView
    private lateinit var expenseAdapter: ExpenseViewListAdapter
    private var selectedDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// initialised views

        ExpenseNameInput = findViewById(R.id.expense_name)
        ExpenseAmountInput = findViewById(R.id.expense_amount)
        addExpenseButton = findViewById(R.id.add_expense)
        expenseList = findViewById(R.id.ExpenseList)
        expenseAdapter = ExpenseViewListAdapter(mutableListOf())
        expenseList.adapter = expenseAdapter

        expenseList.layoutManager = LinearLayoutManager(this)

        DatePickerButton = findViewById(R.id.date_button)
        DateText = findViewById(R.id.expense_date)

        // DatePickerDialog for Date Selection
        DatePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "${selectedDay}/${selectedMonth + 1}/$selectedYear"
                DateText.text = "Date: $selectedDate"
            }, year, month, day).show()
        }
        addExpenseButton.setOnClickListener {
            val name = ExpenseNameInput.text.toString()
            val amount = ExpenseAmountInput.text.toString()

            if (name.isNotBlank() && amount.isNotBlank() && selectedDate.isNotBlank()) {
                val amountValue = amount.toDoubleOrNull()
                if (amountValue != null) {
                    expenseAdapter.addExpense(Expense(name, amount, selectedDate))
                    ExpenseNameInput.text.clear()
                    ExpenseAmountInput.text.clear()
                    DateText.text="no date selected"
                    selectedDate=""
                } else {
                    ExpenseAmountInput.error = "Amount must be a valid number"
                }
            } else {
                if (name.isBlank()) {
                    ExpenseNameInput.error = "Expense name cannot be empty"
                }
                if (amount.isBlank()) {
                    ExpenseAmountInput.error = "Expense amount cannot be empty"
                }
            }
        }
    }
}