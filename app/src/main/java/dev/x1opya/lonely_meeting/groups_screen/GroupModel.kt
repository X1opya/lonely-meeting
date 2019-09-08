package dev.x1opya.lonely_meeting.groups_screen

import android.util.Log
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import dev.x1opya.lonely_meeting.ArrayCallback
import dev.x1opya.lonely_meeting.Consts
import dev.x1opya.lonely_meeting.MainApplication
import dev.x1opya.lonely_meeting.models.Group
import dev.x1opya.lonely_meeting.vk_api.RequestGroup
import dev.x1opya.lonely_meeting.vk_api.RequestSubscribersCount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupModel {

    fun getGroups(callback: ArrayCallback<Group>) {
        MainApplication.intstance.firestore
            .collection("groups")
            .get()
            .addOnFailureListener {
                callback.onFailure(it.localizedMessage)
            }
            .addOnSuccessListener {
                var list = ArrayList<Int>()
                for (item in it.documents) {
                    list.add(item.get(Consts.Firebase.ID).toString().toInt())
                }
                if(list.isEmpty()){
                    callback.onFailure("Ненайдены группы")
                } else {
                    requestGroups(callback, list)
                }
            }
    }

    private fun requestGroups(callback: ArrayCallback<Group>, ids: ArrayList<Int>){
        VK.execute(RequestGroup(ids), object : VKApiCallback<List<Group>> {
            override fun fail(error: VKApiExecutionException) {
                error.errorMsg?.let { callback.onFailure(it) } ?: kotlin.run { callback.onFailure("Неизвестная ошибка") }
            }

            override fun success(result: List<Group>) {
                GlobalScope.launch (Dispatchers.IO){
                    for (group in result) {
                        group.count = VK.executeSync(RequestSubscribersCount(group.id))
                        Log.d("TEST_GROUPS", "group.count = ${group.count}" )
                    }
                    withContext(Dispatchers.Main) {
                        callback.onReady(result as ArrayList<Group>)
                    }
                }
            }
        })
    }
}