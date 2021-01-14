package com.dzakyhdr.academyjeptackpro.ui.academy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dzakyhdr.academyjeptackpro.data.source.AcademyRepository
import com.dzakyhdr.academyjeptackpro.ui.academy.AcademyViewModel
import com.dzakyhdr.academyjeptackpro.ui.academy.di.Injection
import com.dzakyhdr.academyjeptackpro.ui.bookmark.BookmarkViewModel
import com.dzakyhdr.academyjeptackpro.ui.detail.DetailCourseViewModel
import com.dzakyhdr.academyjeptackpro.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(AcademyViewModel::class.java) ->{
                return AcademyViewModel(mAcademyRepository) as T
            }

            modelClass.isAssignableFrom(DetailCourseViewModel::class.java)->{
                return DetailCourseViewModel(mAcademyRepository) as T
            }

            modelClass.isAssignableFrom(BookmarkViewModel::class.java)->{
                return BookmarkViewModel(mAcademyRepository) as T
            }

            modelClass.isAssignableFrom(CourseReaderViewModel::class.java)->{
                return CourseReaderViewModel(mAcademyRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel Class: " + modelClass.name)

        }
    }
}