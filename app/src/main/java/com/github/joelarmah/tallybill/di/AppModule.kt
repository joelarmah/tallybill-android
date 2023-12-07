package com.github.joelarmah.tallybill.di

import android.content.Context
import androidx.room.Room
import com.github.joelarmah.tallybill.data.CustomerRepository
import com.github.joelarmah.tallybill.data.CustomerRepositoryImpl
import com.github.joelarmah.tallybill.data.source.local.LocalDataSource
import com.github.joelarmah.tallybill.data.source.local.LocalDataSourceImpl
import com.github.joelarmah.tallybill.data.source.local.TallybillDatabase
import com.github.joelarmah.tallybill.data.source.remote.ApiRemoteDataSource
import com.github.joelarmah.tallybill.data.source.remote.RemoteDatasource
import com.github.joelarmah.tallybill.domain.AddCustomerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideAddCustomerUseCase(
        repository: CustomerRepository
    ): AddCustomerUseCase {
        return AddCustomerUseCase(repository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideCustomerRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDatasource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CustomerRepository {
        return CustomerRepositoryImpl(localDataSource, remoteDataSource, ioDispatcher)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDatasource {
        return ApiRemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(
        database: TallybillDatabase,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): LocalDataSource {
        return LocalDataSourceImpl(database.customerDao(), ioDispatcher)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object DatabaseModule {
        @Singleton
        @Provides
        fun provideDatabase(@ApplicationContext context: Context): TallybillDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                TallybillDatabase::class.java,
                "tallybill.db"
            ).build()
        }
    }

}