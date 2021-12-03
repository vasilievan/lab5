package aleksey.vasiliev.lab5.task1

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ContinueWatch2 : AppCompatActivity() {
    private var textSecondsElapsed: TextView? = null
    private var secondsElapsed: Int = 0
    private var executorService: ExecutorService? = null

    private fun getBackgroundRunnable() {
        while (true) {
            Thread.sleep(1000)
            textSecondsElapsed?.post {
                secondsElapsed++
                val secondsElapsedString = "Seconds elapsed: $secondsElapsed"
                textSecondsElapsed?.text = secondsElapsedString
            }
        }
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
        executorService = Executors.newFixedThreadPool(1)
        executorService?.submit {
            getBackgroundRunnable()
        }
        super.onResume()
    }

    override fun onPause() {
        textSecondsElapsed = null
        executorService?.shutdown()
        executorService = null
        super.onPause()
    }
}
