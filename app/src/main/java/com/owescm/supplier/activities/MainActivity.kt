package com.owescm.supplier.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.owescm.supplier.R
import com.owescm.supplier.adapter.ViewPagerAdapter
import com.owescm.supplier.fragment.HomeFragment


class MainActivity : AppCompatActivity() {
    internal var prevMenuItem: Int? = null
    lateinit var  drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView
    val fragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openMainFragment()

//        menu_bottom.get(0).isSelected =true
//        menu_bottom.setOnItemSelectedListener{
//            when (it) {
//                R.id.home -> viewpager.currentItem = 0
//                R.id.profile -> viewpager.currentItem = 1
//                R.id.settings -> viewpager.currentItem = 2
//            }
//        }


/*        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    menu_bottom.get(prevMenuItem!!).isSelected =false
                } else {
                    menu_bottom.get(0).isSelected =false
                }
                Log.d("page", "onPageSelected: $position")
                menu_bottom.get(position).isSelected =true
                prevMenuItem =  position
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        });*/
        drawerLayout = findViewById(R.id.drawer_layout)
        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView = findViewById(R.id.nav_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setClickOnNavigation()


    }

    private fun openMainFragment() {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

    private fun setClickOnNavigation() {
        navigationView.setNavigationItemSelectedListener { item ->
            val id = item.itemId
            if (id == R.id.home) {
              //  startActivity(Intent(this@MainActivity, HomeFragment::class.java))
                drawerLayout.closeDrawers()
            }
            true
        }

    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter =
            ViewPagerAdapter(supportFragmentManager)
        val homeFragment = HomeFragment()
        adapter.addFragment(homeFragment)
//        adapter.addFragment(HistoryFragment())
//        adapter.addFragment(ProfileFragment())
        viewPager.adapter = adapter
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {

            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START)
            else
                drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

}
