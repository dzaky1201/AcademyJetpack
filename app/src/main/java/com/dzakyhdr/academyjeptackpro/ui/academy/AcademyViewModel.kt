package com.dzakyhdr.academyjeptackpro.ui.academy

import androidx.lifecycle.ViewModel
import com.dzakyhdr.academyjeptackpro.data.CourseEntity
import com.dzakyhdr.academyjeptackpro.data.source.AcademyRepository
import com.dzakyhdr.academyjeptackpro.utils.DataDummy

class AcademyViewModel(private val academyRepository: AcademyRepository): ViewModel() {
    fun getCourses(): List<CourseEntity> = academyRepository.getAllCourses()
}