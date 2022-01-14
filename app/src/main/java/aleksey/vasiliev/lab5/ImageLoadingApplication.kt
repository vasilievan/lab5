package aleksey.vasiliev.lab5

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageLoadingApplication: Application() {
    val executorService: ExecutorService = Executors.newSingleThreadExecutor()
}