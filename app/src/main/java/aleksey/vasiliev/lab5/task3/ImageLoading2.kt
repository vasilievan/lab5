package aleksey.vasiliev.lab5.task3

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.BitmapFactory
import kotlinx.coroutines.*
import java.net.URL

class ImageLoading2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val image = findViewById<ImageView>(R.id.image)
        CoroutineScope(Dispatchers.IO).launch {
            loadImage(image)
        }
    }

    private fun loadImage(image: ImageView) {
        val url = URL("https://cdnimg.rg.ru/img/content/161/31/13/kinopoisk.ru-Shrek-13985_d_850.jpg")
        val bm = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        runOnUiThread {
            image.setImageBitmap(bm)
        }
    }
}
