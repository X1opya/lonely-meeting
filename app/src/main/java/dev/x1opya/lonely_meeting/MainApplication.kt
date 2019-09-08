package dev.x1opya.lonely_meeting

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

class MainApplication : Application() {
    val firestore = FirebaseFirestore.getInstance()
    companion object {
        val intstance: MainApplication = MainApplication()
    }

    private val tokenTracker = object: VKTokenExpiredHandler {
        override fun onTokenExpired() {
            // token expired
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    fun login() {
        VK.initialize(applicationContext)
        VK.addTokenExpiredHandler(tokenTracker)
    }
}