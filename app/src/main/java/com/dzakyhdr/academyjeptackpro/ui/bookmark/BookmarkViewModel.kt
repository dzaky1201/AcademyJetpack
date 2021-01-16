package com.dzakyhdr.academyjeptackpro.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dzakyhdr.academyjeptackpro.data.CourseEntity
import com.dzakyhdr.academyjeptackpro.data.source.AcademyRepository
import com.dzakyhdr.academyjeptackpro.utils.DataDummy

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}