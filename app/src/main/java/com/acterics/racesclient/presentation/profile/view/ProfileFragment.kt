package com.acterics.racesclient.presentation.profile.view

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.design.widget.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acterics.racesclient.R
import com.acterics.racesclient.common.ui.CustomToolbarHolder
import com.acterics.racesclient.common.ui.fragment.BaseScopedFragment
import com.acterics.racesclient.data.database.entity.User
import com.acterics.racesclient.di.ComponentsManager
import com.acterics.racesclient.presentation.profile.ProfileViewModel
import com.acterics.racesclient.presentation.profile.general.ProfileGeneralFragment
import com.acterics.racesclient.presentation.profile.history.view.ProfileHistoryFragment
import com.acterics.racesclient.presentation.profile.presenter.ProfilePresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by root on 09.10.17.
 */
class ProfileFragment: BaseScopedFragment(), ProfileView, CustomToolbarHolder {


    companion object {
        private val GENERAL_FRAGMENT_POSITION = 0
        private val HISTORY_FRAGMENT_POSITION = 1
    }

    @Inject lateinit var router: Router
    @Inject lateinit var appContext: Context
    @Inject lateinit var navigationHolder: DrawerLayout
    @Inject lateinit var navigationView: NavigationView
    @InjectPresenter lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun provideProfilePresenter(): ProfilePresenter = ProfilePresenter(router, appContext)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val contextWrapper = ContextThemeWrapper(context, R.style.WhiteAccentTheme)
        val localInflater = inflater.cloneInContext(contextWrapper)
        return localInflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarProfile.apply {
            setNavigationIcon(R.drawable.ic_menu_black)
            setNavigationOnClickListener { navigationHolder.openDrawer(navigationView) }
            title = ""
        }


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



        btEdit.setOnClickListener {
            deleteFabBehavior()
            presenter.onEditProfileClicked(btEdit)
        }

    }

    override fun onResume() {
        super.onResume()
        setupFabBehavior()
    }


    override fun showUser(user: User) {
        Glide.with(context)
                .load(user.avatar)
                .centerCrop()
//                .listener(object : RequestListener<String, GlideDrawable> {
//                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
//                        return false
//                    }
//                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
//                        val vNavIcon = toolbarProfile.getNavigationIconView()
//                        presenter.defineTheme((resource as GlideBitmapDrawable).bitmap,
//                                imProfileAvatar.getGlobalVisibleRect(),
//                                tvUsername.getGlobalVisibleRect(),
//                                vNavIcon.getGlobalVisibleRect())
//                        return false
//                    }
//                })
                .into(imProfileAvatar)
        tvUsername.text = getString(R.string.username_template,
                user.firstName,
                user.lastName)
    }

    override fun applyTheme(profileViewModel: ProfileViewModel) {
//        toolbarProfile.navigationIcon = profileViewModel.navigationIcon
//        toolbarProfile.title = ""
//        tvUsername.setTextColor(profileViewModel.textColor)
    }

    override fun injectComponent() {
        ComponentsManager.mainComponent!!.inject(this)
    }

    override fun rejectComponent() {
        ComponentsManager.clearProfileComponent()
    }


    private fun deleteFabBehavior() {
        (btEdit.layoutParams as CoordinatorLayout.LayoutParams).behavior = null
    }

    private fun setupFabBehavior() {
        (btEdit.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                FloatingActionButton.Behavior()

    }


}