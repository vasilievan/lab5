package aleksey.vasiliev.lab5

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import java.util.concurrent.Executors

class ImageLoadingViewModel: ViewModel() {
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
        val executorService = Executors.newSingleThreadExecutor { runnable ->
            val thread = Thread(runnable)
            thread.isDaemon = true
            thread
        }
        executorService.submit {
            val bm = downloadImage(uri)
            setCurrentResource(bm)
        }
    }
}