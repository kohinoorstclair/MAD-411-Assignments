
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_411_assignments.model.Expense
import com.example.mad_411_assignments.ExpenseFileEditor
import com.example.mad_411_assignments.fragments.ExpenseRowView
import com.example.mad_411_assignments.R
import com.example.mad_411_assignments.fragments.FooterFragment


// adaptor class the binds the expenses to the recylceview

class ExpenseViewListAdapter(private val expenses: MutableList<Expense>, private val footerFragment: FooterFragment, private val context: Context) : RecyclerView.Adapter<ExpenseRowView>() {
    private val expenseFileEditor = ExpenseFileEditor()
    // this function is used to add expenserowview in place of each iten

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseRowView {
        // this is the single line view being called and added to recyle view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_view, parent, false)
        return ExpenseRowView(view)
    }
// this is use th connect dats to teh view of expense and use of delete call back
    override fun onBindViewHolder(holder: ExpenseRowView, position: Int) {
        val expense = expenses[position]
        holder.createView(expense) { pos ->
            footerFragment.subtractFromTotal(expenses[pos].amount)
            expenses.removeAt(pos)
            notifyItemRemoved(pos)
            expenseFileEditor.saveExpensesToFile(context, expenses)
        }
    }
    override fun getItemCount(): Int = expenses.size
    // this adds expense to list and notify update recycleview
    fun addExpense(expense: Expense) {
        expenses.add(expense)
        footerFragment.addToTotal(expense.amount)
        notifyItemInserted(expenses.size - 1)
        expenseFileEditor.saveExpensesToFile(context, expenses)
    }
    fun updateExpenses(newExpenses: List<Expense>) {
        expenses.clear()
        expenses.addAll(newExpenses)
        var totalAmount = 0.0
        for (expense in expenses) {
            try {
                totalAmount += expense.amount.toDouble()
            } catch (e: NumberFormatException) {
                Log.e("ExpenseViewListAdapter", "Invalid amount: ${expense.amount}")
            }
        }

        footerFragment.addToTotal(totalAmount.toString())
        notifyDataSetChanged()
    }

}
