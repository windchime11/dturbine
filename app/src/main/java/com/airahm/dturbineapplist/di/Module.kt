package com.airahm.dturbineapplist.di

import com.airahm.dturbineapplist.db.db.DTurbineRoomDb
import com.airahm.dturbineapplist.repo.MainRepo
import com.airahm.dturbineapplist.service.WebDataService
import com.airahm.dturbineapplist.viewmodel.AppDetailViewModel
import com.airahm.dturbineapplist.viewmodel.AppListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModule = module {
    viewModel { AppListViewModel(get()) }
    viewModel { AppDetailViewModel(get()) }
    single { MainRepo(get()) }
    single { DTurbineRoomDb.getDatabase(get())!!}
}

val serviceModule = module {
    factory { WebDataService() }
}
