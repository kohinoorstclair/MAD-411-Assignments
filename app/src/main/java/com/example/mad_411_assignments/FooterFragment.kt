import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mad_411_assignments.R

class FooterFragment : Fragment() {

    private lateinit var totalAmountTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_footer, container, false)
        totalAmountTextView = view.findViewById(R.id.totalAmount)
        return view
    }


    fun updateTotalAmount(amount: Double) {
        totalAmountTextView.text = "Total Expenses: $%.2f".format(amount)
    }
}
