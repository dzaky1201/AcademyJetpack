package com.dzakyhdr.academyjeptackpro.data.source.remote

import com.dzakyhdr.academyjeptackpro.data.source.remote.response.ContentResponse
import com.dzakyhdr.academyjeptackpro.data.source.remote.response.CourseResponse
import com.dzakyhdr.academyjeptackpro.data.source.remote.response.ModuleResponse
import com.dzakyhdr.academyjeptackpro.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourse()

    fun getModules(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String): ContentResponse = jsonHelper.loadContent(moduleId)
}