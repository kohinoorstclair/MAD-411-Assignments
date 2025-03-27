
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_411_assignments.Expense
import com.example.mad_411_assignments.ExpenseDetailsActivity
import com.example.mad_411_assignments.ExpenseRowView
import com.example.mad_411_assignments.R

// adaptor class the binds the expenses to the recylceview

class ExpenseViewListAdapter(private val expenses: MutableList<Expense>, private val footerFragment: FooterFragment) : RecyclerView.Adapter<ExpenseRowView>() {

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
        }

    }
//
    override fun getItemCount(): Int = expenses.size

    // this adds expense to list and notify update recycleview
    fun addExpense(expense: Expense) {

        expenses.add(expense)
        footerFragment.addToTotal(expense.amount)
        notifyItemInserted(expenses.size - 1)
    }

}
