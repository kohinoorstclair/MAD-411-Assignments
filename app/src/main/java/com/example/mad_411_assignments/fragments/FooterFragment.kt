package com.example.mad_411_assignments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mad_411_assignments.R

class FooterFragment : Fragment() {

    private lateinit var totalAmountTextView: TextView
    var totalAmount = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_footer, container, false)
        totalAmountTextView = view.findViewById(R.id.totalAmount)
        updateTotalAmount()
        return view
    }

    fun addToTotal(amount: String) {
        totalAmount += amount.toDoubleOrNull() ?: 0.0
        updateTotalAmount()
    }

    fun subtractFromTotal(amount: String) {
        totalAmount -= amount.toDoubleOrNull() ?: 0.0
        updateTotalAmount()
    }

    private fun updateTotalAmount() {
        totalAmountTextView.text = "Total Expenses: $%.2f".format(totalAmount)
    }
}