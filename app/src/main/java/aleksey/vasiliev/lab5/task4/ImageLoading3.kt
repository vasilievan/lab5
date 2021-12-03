package aleksey.vasiliev.lab5.task4

import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.facebook.drawee.backends.pipeline.Fresco
import android.net.Uri

class ImageLoading3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main3)
        val imageUri: Uri =
            Uri.parse("https://cdnimg.rg.ru/img/content/161/31/13/kinopoisk.ru-Shrek-13985_d_850.jpg")
        val image = findViewById<ImageView>(R.id.image_fresco)
        image.setImageURI(imageUri)
    }
}
