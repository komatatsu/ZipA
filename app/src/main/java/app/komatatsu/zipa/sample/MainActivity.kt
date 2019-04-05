package app.komatatsu.zipa.sample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.komatatsu.zipa.ZipA
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ZipA.initialize(this)
        search.setOnClickListener {
            Log.v(this.javaClass.simpleName, "${editText.text}")
            val address = ZipA.search(this, editText.text.toString())
            if (address == null) {
                Toast.makeText(this, "該当住所なし", Toast.LENGTH_SHORT).show()
            } else {
                result.text = address
            }
        }
    }
}
