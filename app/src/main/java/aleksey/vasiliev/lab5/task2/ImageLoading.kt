package aleksey.vasiliev.lab5.task2

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executors
import android.graphics.BitmapFactory
import java.net.URL

class ImageLoading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val image = findViewById<ImageView>(R.id.image)
        val executorService = Executors.newFixedThreadPool(1)
        val task = executorService.submit {
            loadImage(image)
        }
        task.get()
    }

    private fun loadImage(image: ImageView) {
        val url = URL("https://cdnimg.rg.ru/img/content/161/31/13/kinopoisk.ru-Shrek-13985_d_850.jpg")
        val bm = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        runOnUiThread {
            image.setImageBitmap(bm)
        }
    }
}
