package app.komatatsu.zipa.sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search.setOnClickListener {
            Log.v(this.javaClass.simpleName, "${editText.text}")
            // TODO call ZipA.search
            result.text = "結果"
        }

    }
}
