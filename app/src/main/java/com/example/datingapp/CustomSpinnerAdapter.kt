import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.datingapp.R

class CustomSpinnerAdapter(context: Context, items: List<String>) :
    ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, items) {

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)

        if (position == 0) {
            // Set the text color of the first item to grey
            textView.setTextColor(ContextCompat.getColor(context, R.color.hint_color))
        } else {
            // Set the text color of other items to black
            textView.setTextColor(Color.BLACK)
        }

        return view
    }
}