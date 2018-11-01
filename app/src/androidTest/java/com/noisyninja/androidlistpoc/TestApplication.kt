package com.noisyninja.androidlistpoc

import android.content.Context
import android.content.res.Resources
import androidx.test.InstrumentationRegistry
import com.noisyninja.androidlistpoc.layers.AppExecutors
import com.noisyninja.androidlistpoc.layers.RefWatcherModule
import com.noisyninja.androidlistpoc.layers.UtilModule
import com.noisyninja.androidlistpoc.layers.database.DataBaseModule
import com.noisyninja.androidlistpoc.layers.database.viewmodel.ViewModelFactory
import com.noisyninja.androidlistpoc.layers.di.NinjaComponent
import com.noisyninja.androidlistpoc.layers.network.HttpClient
import com.noisyninja.androidlistpoc.layers.network.NetworkModule
import org.mockito.Mockito

/**
 * Mock test application with mocked dagger dependencies
 * Created by sudiptadutta on 20/05/18.
 */
class TestApplication : NinjaApp() {

    lateinit var appContext: Context
    lateinit var testApplication: TestApplication
    lateinit var resourcesModule: Resources
    lateinit var utilModule: UtilModule
    lateinit var networkModule: NetworkModule
    lateinit var dataBaseModule: DataBaseModule
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var refWatcherModule: RefWatcherModule
    lateinit var appExecutors: AppExecutors

    override val ninjaComponent: NinjaComponent by lazy {
        initialise()

        DaggerTestComponent.builder()
                .database(dataBaseModule)
                .app(testApplication)
                .appContext(appContext)
                .executor(appExecutors)
                .network(networkModule)
                .resources(resourcesModule)
                .util(utilModule)
                .refWatcher(refWatcherModule)
                .vmf(viewModelFactory)
                .build()
    }

    private fun initialise() {
        testApplication = InstrumentationRegistry.getTargetContext().applicationContext as TestApplication
        appContext = InstrumentationRegistry.getTargetContext()
        utilModule = UtilModule(appContext)
        resourcesModule = appContext.resources
        networkModule = NetworkModule(HttpClient(appContext, utilModule).client)
        dataBaseModule = DataBaseModule(utilModule, appContext)
        viewModelFactory = ViewModelFactory(dataBaseModule, utilModule, resources, networkModule)

        refWatcherModule = Mockito.mock(RefWatcherModule::class.java)
        appExecutors = Mockito.mock(AppExecutors::class.java)
    }
}