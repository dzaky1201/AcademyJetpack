package com.dzakyhdr.academyjeptackpro.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dzakyhdr.academyjeptackpro.data.source.remote.RemoteDataSource
import com.dzakyhdr.academyjeptackpro.data.source.remote.response.ContentResponse
import com.dzakyhdr.academyjeptackpro.data.source.remote.response.CourseResponse
import com.dzakyhdr.academyjeptackpro.data.source.remote.response.ModuleResponse
import com.dzakyhdr.academyjeptackpro.utils.DataDummy
import com.dzakyhdr.academyjeptackpro.utils.LiveDataTestUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.*

class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponses = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCourseReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        verify(remote).getAllCourses(any())
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(eq(courseId), any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))

        verify(remote).getModules(eq(courseId), any())

        assertNotNull(courseEntities)
        assertEquals(moduleResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCourseReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())

        verify(remote).getAllCourses(any())

        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback)
                .onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(eq(courseId), any())
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadContentCallback)
                .onContentReceived(content)
            null
        }.`when`(remote).getContent(eq(moduleId), any())

        val courseEntitiesContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))

        verify(remote)
            .getModules(eq(courseId), any())

        verify(remote)
            .getContent(eq(moduleId), any())

        assertNotNull(courseEntitiesContent)
        assertNotNull(courseEntitiesContent.contentEntity)
        assertNotNull(courseEntitiesContent.contentEntity?.content)
        assertEquals(content.content, courseEntitiesContent.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCoursesCallback)
                .onAllCourseReceived(courseResponses)
            null
        }.`when`(remote).getAllCourses(any())

        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))

        verify(remote).getAllCourses(any())

        assertNotNull(courseEntities)
        assertNotNull(courseEntities.title)
        assertEquals(courseResponses[0].title, courseEntities.title)
    }
}


