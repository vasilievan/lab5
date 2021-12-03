package aleksey.vasiliev.lab5.task1

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.NonCancellable.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ContinueWatch3 : AppCompatActivity() {
    private var textSecondsElapsed: TextView? = null
    private var secondsElapsed: Int = 0
    private var job: Job? = null
    private val scope = MainScope()

    private fun doCount() {
        textSecondsElapsed?.post {
            secondsElapsed++
            val secondsElapsedString = "Seconds elapsed: $secondsElapsed"
            textSecondsElapsed?.text = secondsElapsedString
        }
    }

    private fun startCount() {
        stopCount()
        job = scope.launch {
            while(true) {
                delay(1000)
                doCount()
            }
        }
    }

    private fun stopCount() {
        job?.cancel()
        job = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                secondsElapsed = getInt("seconds_elapsed")
            }
        } else {
            secondsElapsed = 0
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("seconds_elapsed", secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("seconds_elapsed")
    }

    override fun onResume() {
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        startCount()
        super.onResume()
    }

    override fun onPause() {
        textSecondsElapsed = null
        stopCount()
        super.onPause()
    }
}
