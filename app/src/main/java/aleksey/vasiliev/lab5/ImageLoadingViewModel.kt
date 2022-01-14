package aleksey.vasiliev.lab5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class ImageLoadingViewModel(private val application: ImageLoadingApplication): AndroidViewModel(application) {
    val currentResource: MutableLiveData<Bitmap> by lazy {
        MutableLiveData<Bitmap>()
    }

    fun loadImageCoroutines(uri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val bm = downloadImage(uri)
            setCurrentResource(bm)
        }
    }

    private fun downloadImage(uri: String): Bitmap {
        val url = URL(uri)
        return BitmapFactory.decodeStream(url.openConnection().getInputStream())
    }

    private fun setCurrentResource(bm: Bitmap) {
        viewModelScope.launch {
            currentResource.value = bm
        }
    }

    fun loadImageExecutors(uri: String) {
        application.executorService.submit {
            val bm = downloadImage(uri)
            setCurrentResource(bm)
        }
    }
}