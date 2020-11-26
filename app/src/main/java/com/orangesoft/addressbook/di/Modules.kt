package com.orangesoft.addressbook.di

import com.orangesoft.addressbook.provider.SystemContactsProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val applicationModule = module(override = true) {
    factory { SystemContactsProvider(androidContext())}
}