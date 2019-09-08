package dev.x1opya.lonely_meeting

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.vk.api.sdk.VK
import dev.x1opya.lonely_meeting.groups_screen.GroupFragment
import dev.x1opya.lonely_meeting.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import dev.x1opya.lonely_meeting.list_screen.ListFragment


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction().replace(contentFrame.id, ListFragment.newInstance()).commitNowAllowingStateLoss()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction().replace(contentFrame.id, GroupFragment.newInstance()).commitNowAllowingStateLoss()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener  false
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        if (VK.isLoggedIn()) {
            navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
            supportFragmentManager.beginTransaction().replace(contentFrame.id, ListFragment.newInstance()).commitNowAllowingStateLoss()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
