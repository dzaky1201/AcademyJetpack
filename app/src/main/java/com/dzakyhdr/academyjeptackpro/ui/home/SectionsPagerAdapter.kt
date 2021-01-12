package com.dzakyhdr.academyjeptackpro.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dzakyhdr.academyjeptackpro.R
import com.dzakyhdr.academyjeptackpro.ui.academy.AcademyFragment
import com.dzakyhdr.academyjeptackpro.ui.bookmark.BookmarkFragment

class SectionsPagerAdapter(private val mComtext: Context, fm: FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.home, R.string.bookmark)
    }
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> AcademyFragment()
            1 -> BookmarkFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mComtext.resources.getString(TAB_TITLES[position])


}