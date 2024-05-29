package shared

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual val platFormModule = module {
    single {
        DispatchersProvider()
    }
    single {
        ImageStorage(get(), get())
    }

}