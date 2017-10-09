package com.acterics.racesclient.ui.profile

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.ui.main.MainDrawerFragment
import com.acterics.racesclient.ui.profile.general.ProfileGeneralFragment
import com.acterics.racesclient.ui.profile.history.ProfileHistoryFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by root on 09.10.17.
 */
class ProfileFragment: MainDrawerFragment(), ProfileView {
    companion object {
        private val GENERAL_FRAGMENT_POSITION = 0
        private val HISTORY_FRAGMENT_POSITION = 1
    }

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileToolbar.title = ""
        vProfilePager.adapter = object: FragmentPagerAdapter(childFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return when(position) {
                    GENERAL_FRAGMENT_POSITION -> ProfileGeneralFragment()
                    HISTORY_FRAGMENT_POSITION -> ProfileHistoryFragment()
                    else -> throw IllegalStateException()
                }
            }
            override fun getCount(): Int {
                return holderProfileTabs.tabCount
            }
        }
        vProfilePager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                holderProfileTabs.setScrollPosition(position, positionOffset, false)
            }
            override fun onPageSelected(position: Int) {
                holderProfileTabs.getTabAt(position)?.select()
            }
        })
        holderProfileTabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabSelected(tab: TabLayout.Tab) {
                vProfilePager.currentItem = tab.position
            }
        })
        btEdit.setOnClickListener { presenter.onEditProfileClicked() }
    }

    override fun getToolbar(): Toolbar {
        return profileToolbar
    }

}