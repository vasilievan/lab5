package aleksey.vasiliev.lab5.task1

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContinueWatch3 : AppCompatActivity() {
    private lateinit var textSecondsElapsed: TextView
    @Volatile
    private var secondsElapsed: Int = 0
    private lateinit var job: Job
    private val secondsElapsedKey: String = "seconds_elapsed"

    private fun doCount() {
        textSecondsElapsed.post {
            secondsElapsed++
            val secondsElapsedString = "Seconds elapsed: $secondsElapsed"
            textSecondsElapsed.text = secondsElapsedString
        }
    }

    private fun startCount() {
        stopCount()
        job = this.lifecycleScope.launch {
            while(true) {
                delay(1000)
                doCount()
            }
        }
    }

    private fun stopCount() {
        if (this::job.isInitialized) job.cancel()
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

    override fun onResume() {
        startCount()
        super.onResume()
    }

    override fun onPause() {
        stopCount()
        super.onPause()
    }
}
