package com.acterics.racesclient.ui.profile

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.data.entity.User
import com.acterics.racesclient.ui.main.MainDrawerFragment
import com.acterics.racesclient.ui.profile.general.ProfileGeneralFragment
import com.acterics.racesclient.ui.profile.history.ProfileHistoryFragment
import com.acterics.racesclient.utils.getGlobalVisibleRect
import com.acterics.racesclient.utils.getNavigationIconView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_profile.*
import java.lang.Exception

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
        val contextWrapper = ContextThemeWrapper(context, R.style.WhiteAccentTheme)
        val localInflater = inflater.cloneInContext(contextWrapper)
        return localInflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                holderProfileTabs.setScrollPosition(position, positionOffset, false)
            }
            override fun onPageSelected(position: Int) {
                holderProfileTabs.getTabAt(position)?.select()
            }
        })
        holderProfileTabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab) {
                vProfilePager.currentItem = tab.position
            }
        })
        btEdit.setOnClickListener { presenter.onEditProfileClicked() }
    }

    override fun getToolbar(): Toolbar {
        return profileToolbar
    }

    override fun showUser(user: User) {
        Glide.with(context)
                .load(user.avatar)
                .centerCrop()
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        val vNavIcon = profileToolbar.getNavigationIconView()
                        presenter.defineTheme((resource as GlideBitmapDrawable).bitmap,
                                imProfileAvatar.getGlobalVisibleRect(),
                                tvUsername.getGlobalVisibleRect(),
                                vNavIcon.getGlobalVisibleRect())
                        return false
                    }
                })
                .into(imProfileAvatar)
        tvUsername.text = getString(R.string.username_template,
                user.firstName,
                user.lastName)
    }

    override fun applyTheme(profileViewModel: ProfileViewModel) {
        profileToolbar.navigationIcon = profileViewModel.navigationIcon
        profileToolbar.title = ""
        tvUsername.setTextColor(profileViewModel.textColor)
    }




}