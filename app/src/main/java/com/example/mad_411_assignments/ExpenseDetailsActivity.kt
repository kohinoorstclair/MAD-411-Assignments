package com.example.mad_411_assignments

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class ExpenseDetailsActivity : AppCompatActivity() {

     lateinit var nameText: TextView
     lateinit var amountText: TextView
     lateinit var dateText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_details)

        nameText = findViewById(R.id.name_details)
        amountText = findViewById(R.id.amount_details)
        dateText = findViewById(R.id.date_details)

        // getting data usnig intend extra
        val name = intent.getStringExtra("name")
        val amount = intent.getStringExtra("amount")
        val date = intent.getStringExtra("date")

        nameText.text = "Expense Name: $name"
        amountText.text = " Amount: $amount"
        dateText.text = " Date -> $date"
    }
}
