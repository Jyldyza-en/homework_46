package kg.tutorialapp.homework_46

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var myPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.editText)
        val tvText = findViewById<TextView>(R.id.textView)
        val btnSave = findViewById<Button>(R.id.btn_add)
        val btnDel = findViewById<Button>(R.id.btn_delete)

        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE)

        val text = myPrefs!!.getString("text_key", "")

        tvText.text = text

        btnSave.setOnClickListener{
            if (editText.text.toString().isEmpty())
                Toast.makeText(applicationContext, "Empty line", Toast.LENGTH_LONG).show()
            else{
                tvText.text = editText.text.toString()

                val editor = myPrefs!!.edit()
                editor.putString("text_key", editText.text.toString())
                editor.apply()

                Toast.makeText(applicationContext, "Saved", Toast.LENGTH_LONG).show()
            }
        }

        btnDel.setOnClickListener{
            val editor = myPrefs!!.edit()
            editor.clear()
            editor.apply()

            tvText.setText("").toString()

            Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_LONG).show()
        }
    }
}