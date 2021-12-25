package aleksey.vasiliev.lab5.task2

import aleksey.vasiliev.lab5.ImageLoadingViewModel
import aleksey.vasiliev.lab5.R
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels

class ImageLoading : AppCompatActivity() {
    private val model: ImageLoadingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val image = findViewById<ImageView>(R.id.image)
        model.loadImageExecutors(resources.getString(R.string.uri))
        model.currentResource.observe(this, { resource ->
            image.setImageBitmap(resource)
        })
    }
}
