package aleksey.vasiliev.lab5.task1

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class ContinueWatch2 : AppCompatActivity() {
    @Volatile
    private var secondsElapsed: Int = 0
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor{ runnable ->
        val thread = Thread(runnable)
        thread.isDaemon = true
        thread
    }
    private var future: Future<*>? = null
    private lateinit var textSecondsElapsed: TextView

    private fun getBackgroundRunnable() {
        while (true) {
            Thread.sleep(1000)
            secondsElapsed++
            val secondsElapsedString = "Seconds elapsed: $secondsElapsed"
            runOnUiThread {
                textSecondsElapsed.text = secondsElapsedString
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
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("seconds_elapsed", secondsElapsed)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getInt("seconds_elapsed")
    }

    override fun onStart() {
       future = executorService.submit {
            getBackgroundRunnable()
        }
        super.onStart()
    }

    override fun onStop() {
        future?.cancel(true)
        future = null
        super.onStop()
    }
}
