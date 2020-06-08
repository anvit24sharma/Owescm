package com.owescm.supplier.activities

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.owescm.OwescmApplication.Companion.prefs
import com.owescm.supplier.R
import com.owescm.supplier.fragment.HomeFragment


class MainActivity : AppCompatActivity() {
    internal var prevMenuItem: Int? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    val fragment = HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openMainFragment()


        drawerLayout = findViewById(R.id.drawerLayout)
        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView = findViewById(R.id.navigationView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.notification -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked on Notifications",
                        Toast.LENGTH_SHORT
                    ).show()


                }
                R.id.management -> {
                    drawerLayout.closeDrawer(GravityCompat.START)


                    Toast.makeText(
                        this@MainActivity,
                        "Clicked on User Management",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.ebid -> {

                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this@MainActivity, "Clicked on eBid", Toast.LENGTH_SHORT).show()

                }
                R.id.supportManagement -> {


                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked on Support Management",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                R.id.eAuction -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this@MainActivity, "Clicked on eAuction", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.scorecard -> {


                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked on Final Evaluations",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.scorecard -> {


                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this@MainActivity, "Clicked on Scorecard", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.contracts -> {


                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(this@MainActivity, "Clicked on contracts", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.salesManagement -> {


                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked on Sales Management",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.supportManagement -> {


                    drawerLayout.closeDrawer(GravityCompat.START)
                    Toast.makeText(
                        this@MainActivity,
                        "Clicked on Support Management",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                R.id.logout -> {

                    val builder = AlertDialog.Builder(this)

                    builder.setMessage("Do you want to logout this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog: DialogInterface, i: Int ->
                            prefs.edit().clear().apply()
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        }
                        .setNegativeButton("No") { dialog: DialogInterface, i: Int ->
                            dialog.cancel()
                        }

                    val alert = builder.create()
                    alert.setTitle("LOGOUT")
                    alert.show()
                }
            }
            return@setNavigationItemSelectedListener true


        }


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
