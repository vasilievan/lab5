package aleksey.vasiliev.lab5.task1

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContinueWatch : AppCompatActivity() {
    private lateinit var textSecondsElapsed: TextView
    private lateinit var backgroundThread: Thread
    @Volatile
    private var secondsElapsed: Int = 0
    private val secondsElapsedKey: String = "seconds_elapsed"

    private fun getBackgroundThread(): Thread {
        return Thread {
            try {
                while (true) {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        secondsElapsed++
                        val secondsElapsedString = "Seconds elapsed: $secondsElapsed"
                        textSecondsElapsed.text = secondsElapsedString
                    }
                }
            } catch (e: InterruptedException) {
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
        backgroundThread = getBackgroundThread()
        backgroundThread.start()
        super.onResume()
    }

    override fun onPause() {
        backgroundThread.interrupt()
        super.onPause()
    }
}
