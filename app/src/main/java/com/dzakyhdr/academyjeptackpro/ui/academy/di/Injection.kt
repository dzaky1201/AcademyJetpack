package com.dzakyhdr.academyjeptackpro.ui.academy.di

import android.content.Context
import com.dzakyhdr.academyjeptackpro.data.source.AcademyRepository
import com.dzakyhdr.academyjeptackpro.data.source.remote.RemoteDataSource
import com.dzakyhdr.academyjeptackpro.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return AcademyRepository.getInstance(remoteDataSource)
    }
}