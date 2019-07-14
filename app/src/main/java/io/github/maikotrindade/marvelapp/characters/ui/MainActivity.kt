package io.github.maikotrindade.marvelapp.characters.ui

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import io.github.maikotrindade.marvelapp.R
import io.github.maikotrindade.marvelapp.base.BaseActivity
import io.github.maikotrindade.marvelapp.characters.ui.list.FavoriteCharactersFragment
import io.github.maikotrindade.marvelapp.characters.ui.list.ListCharactersFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
    }

    private fun setupUI() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar_title.text = getString(R.string.characters)
        setupTabLayout()
    }

    private fun setupTabLayout() {
        tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(ListCharactersFragment.newInstance(), getString(R.string.characters))
        tabAdapter.addFragment(FavoriteCharactersFragment.newInstance(), getString(R.string.favorites))

        viewPager.adapter = tabAdapter
        tabLayout.setupWithViewPager(viewPager)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                setToolbarTitle(position)
            }

        })
    }

    private fun setToolbarTitle(position: Int) {
        toolbar_title.text = if (position == TabPositions.LIST.index) {
            getString(R.string.characters)
        } else {
            getString(R.string.favorites)
        }
    }


}
