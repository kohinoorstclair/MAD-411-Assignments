package com.example.mad_411_assignments

import android.content.Context
import android.util.Log
import com.example.mad_411_assignments.model.Expense
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken

import com.google.gson.Gson
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

private  const val FILE_NAME = "expenses.txt"
class ExpenseFileEditor {

    fun saveExpensesToFile(context: Context, Expenses: List<Expense>) {
        try {
            val json = Gson().toJson(Expenses)

            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { output ->
                output.write(json.toByteArray())
            }
            Log.d("FileStorage", "Tasks saved successfully")
        } catch (e: IOException) {
            Log.e("FileStorage", "Error saving tasks: ${e.message}")
        }
    }

     fun loadExpensesFromFile(context: Context): MutableList<Expense> {
        val ExpensesList: MutableList<Expense> = mutableListOf()
        try {
            val file = File(context.filesDir, FILE_NAME)
            if (!file.exists()) return ExpensesList

            val json = file.readText()
            val type = object : TypeToken<List<Expense>>() {}.type
            val loadedTasks: List<Expense> = Gson().fromJson(json, type)
            ExpensesList.addAll(loadedTasks)

            Log.d("FileStorage", "Tasks loaded successfully")
        } catch (e: FileNotFoundException) {
            Log.e("FileStorage", "File not found: ${e.message}")
        } catch (e: IOException) {
            Log.e("FileStorage", "Error reading file: ${e.message}")
        }
        return ExpensesList
    }
}