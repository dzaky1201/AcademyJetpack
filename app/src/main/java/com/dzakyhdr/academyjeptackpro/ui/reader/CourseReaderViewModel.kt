package com.dzakyhdr.academyjeptackpro.ui.reader

import androidx.lifecycle.ViewModel
import com.dzakyhdr.academyjeptackpro.data.ContentEntity
import com.dzakyhdr.academyjeptackpro.data.ModuleEntity
import com.dzakyhdr.academyjeptackpro.data.source.AcademyRepository
import com.dzakyhdr.academyjeptackpro.utils.DataDummy

class CourseReaderViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    private lateinit var courseId: String
    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }


    fun getModules(): ArrayList<ModuleEntity> = academyRepository.getAllModulesByCourse(courseId)

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)
}