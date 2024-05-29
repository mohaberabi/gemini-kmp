package shared

import android.content.Context
import core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

actual class KoinInit(
    private val context: Context,
) {

    actual fun init() {
        startKoin {
            androidContext(context)
            androidLogger()
            modules(
                viewModelModule,
                platFormModule,
                appModule,
            )
        }
    }
}