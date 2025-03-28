package com.example.mad_411_assignments

import ExpenseViewListAdapter
import FooterFragment
import HeaderFragment
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class ExpenseListFragment : Fragment() {

    private lateinit var ExpenseNameInput: EditText
    private lateinit var ExpenseAmountInput: EditText
    private lateinit var DatePickerButton: Button
    private lateinit var DateText: TextView
    private lateinit var addExpenseButton: Button
    lateinit var expenseList: RecyclerView
    private lateinit var expenseAdapter: ExpenseViewListAdapter
    private var selectedDate: String = ""
    lateinit var footerFragment: FooterFragment
    private val expenseFileEditor = ExpenseFileEditor()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expense_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        ExpenseNameInput = view.findViewById(R.id.expense_name)
        ExpenseAmountInput = view.findViewById(R.id.expense_amount)
        addExpenseButton = view.findViewById(R.id.add_expense)
        expenseList = view.findViewById(R.id.ExpenseList)

        footerFragment = FooterFragment()
        expenseAdapter = ExpenseViewListAdapter(mutableListOf(), footerFragment, requireContext())
        expenseList.adapter = expenseAdapter
        expenseList.layoutManager = LinearLayoutManager(context)

        DatePickerButton = view.findViewById(R.id.date_button)
        DateText = view.findViewById(R.id.expense_date)

        // DatePickerDialog for Date Selection
        DatePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "${selectedDay}/${selectedMonth + 1}/$selectedYear"
                DateText.text = "Date: $selectedDate"
            }, year, month, day).show()
        }

        // Handle adding new expense
        addExpenseButton.setOnClickListener {
            val name = ExpenseNameInput.text.toString()
            val amount = ExpenseAmountInput.text.toString()

            if (name.isNotBlank() && amount.isNotBlank() && selectedDate.isNotBlank()) {
                val amountValue = amount.toDoubleOrNull()
                if (amountValue != null) {
                    expenseAdapter.addExpense(Expense(name, amount, selectedDate))

                    ExpenseNameInput.text.clear()
                    ExpenseAmountInput.text.clear()
                    DateText.text = "no date selected"
                    selectedDate = ""
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





        val financialTipsButton: Button = view.findViewById(R.id.tipsButton)
        financialTipsButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.investopedia.com/personal-finance-4427765"))
            startActivity(intent)
        }

        // Added fragments for header and footer
        val headerFragment = HeaderFragment()
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.headerContainer, headerFragment)
            replace(R.id.footerContainer, footerFragment)
            commit()
        }

    }

    override fun onStart() {
        super.onStart()
        //Call the footer update
        val expenses = expenseFileEditor.loadExpensesFromFile(requireContext())
        expenseAdapter.updateExpenses(expenses)
    }
}

