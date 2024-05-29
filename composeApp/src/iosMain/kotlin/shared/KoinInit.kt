package shared

import core.di.appModule
import org.koin.core.context.startKoin

actual class KoinInit {

    actual fun init() {


        startKoin {
            modules(
                viewModelModule,
                platFormModule,
                appModule,
            )
        }
    }
}