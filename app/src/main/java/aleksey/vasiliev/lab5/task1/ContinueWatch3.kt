package aleksey.vasiliev.lab5.task1

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay

class ContinueWatch3 : AppCompatActivity() {
    private lateinit var textSecondsElapsed: TextView
    private var secondsElapsed: Int = 0
    private val secondsElapsedKey: String = "seconds_elapsed"

    private fun doCount() {
        textSecondsElapsed.post {
            secondsElapsed++
            val secondsElapsedString = "Seconds elapsed: $secondsElapsed"
            textSecondsElapsed.text = secondsElapsedString
        }
    }

    private fun startCount() {
        this.lifecycleScope.launchWhenStarted {
            while(true) {
                delay(1000)
                doCount()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                secondsElapsed = getInt(secondsElapsedKey)
            }
        } else {
            secondsElapsed = 0
        }
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        startCount()
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(secondsElapsedKey, secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        secondsElapsed = savedInstanceState.getInt(secondsElapsedKey)
        super.onRestoreInstanceState(savedInstanceState)
    }
}
