package com.example.marvelac

import android.app.Application
import com.example.data.repository.CharactersRepository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.example.interactor.GetCharacterById
import com.example.interactor.GetCharacterComics
import com.example.interactor.GetCharacterSeries
import com.example.interactor.GetCharacters
import com.example.marvelac.data.database.MarvelDatabase
import com.example.marvelac.data.database.RoomDataSource
import com.example.marvelac.data.server.MarvelDataSource
import com.example.marvelac.ui.character.CharacterFragment
import com.example.marvelac.ui.character.CharacterViewModel
import com.example.marvelac.ui.main.MainFragment
import com.example.marvelac.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
k
fun Application.initServiceLocator(){
    startKoin {
        androidLogger()
        androidContext(this@initServiceLocator)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single { MarvelDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get())}
    factory<RemoteDataSource> { MarvelDataSource() }
}

private val dataModule = module {
    factory { CharactersRepository(get(), get()) }
}

private val scopesModule = module {
    scope(named<MainFragment>()){
        viewModel { MainViewModel(get()) }
        scoped { GetCharacters(get())  }
    }

    scope(named<CharacterFragment>()){
        viewModel { (characterId : Long) -> CharacterViewModel(get(), get(), get(), characterId) }
        scoped { GetCharacterById(get())  }
        scoped { GetCharacterSeries(get())  }
        scoped { GetCharacterComics(get())  }
    }
}