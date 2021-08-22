package com.mobarok.rokomarytest.di

import com.mobarok.rokomarytest.model.LoginService
import com.mobarok.rokomarytest.viewModel.LoginViewModel
import com.mobarok.rokomarytest.viewModel.RegisterViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: LoginService)
    fun inject(viewModel:LoginViewModel)
    fun inject(viewModel:RegisterViewModel)
}