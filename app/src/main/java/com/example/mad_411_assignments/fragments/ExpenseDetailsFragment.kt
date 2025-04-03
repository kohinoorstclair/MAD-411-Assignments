package com.example.mad_411_assignments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mad_411_assignments.R

class ExpenseDetailsFragment  : Fragment() {
    lateinit var nameText: TextView
    lateinit var amountText: TextView
    lateinit var dateText: TextView
 lateinit var convertedText :TextView
 lateinit var  button: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_expense_details, container, false)

        nameText = view.findViewById(R.id.name_details)
        amountText = view.findViewById(R.id.amount_details)
        dateText = view.findViewById(R.id.date_details)
        convertedText = view.findViewById(R.id.convertedCostDisplay)
        button = view.findViewById(R.id.button)
        // Retrieve data using arguments
        val name = arguments?.getString("name")
        val amount = arguments?.getString("amount")
        val date = arguments?.getString("date")
        val convCost = arguments?.getString("CAmount")
        val code = arguments?.getString("code")

        nameText.text = "Expense Name: $name"
        amountText.text = "Amount: $amount $code"
        dateText.text = "Date: $date"
        convertedText.text="Converted Amount = $convCost CAD"
        return view




    }
}