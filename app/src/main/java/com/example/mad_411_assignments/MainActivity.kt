package com.example.mad_411_assignments

import ExpenseViewListAdapter
import FooterFragment
import HeaderFragment
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
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
    lateinit var footerFragment: FooterFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("ActivityLifecycle", "onCreate called")
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
        val financialTipsButton: Button = findViewById(R.id.tipsButton)

        financialTipsButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.investopedia.com/personal-finance-4427765"))
            startActivity(intent)
        }
        // added code for fragments
        val headerFragment = HeaderFragment()
        footerFragment = FooterFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.headerContainer, headerFragment)
            replace(R.id.footerContainer, footerFragment)
            commit()
        }



    }
    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifecycle", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifecycle", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifecycle", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifecycle", "onDestroy called")
    }
    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun updateFooter(expenseAmount: Double) {
        footerFragment.updateTotalAmount(expenseAmount)
    }
}