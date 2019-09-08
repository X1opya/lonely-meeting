package dev.x1opya.lonely_meeting.vk_api

import android.util.Log
import com.google.gson.Gson
import com.vk.api.sdk.requests.VKRequest
import dev.x1opya.lonely_meeting.models.Group

class RequestGroup(private val listId: ArrayList<Int>) : VKRequest<List<Group>>("groups.getById") {

    init {
        addParam("group_ids", listId)
    }

    override fun parse(r: String): List<Group> {
        Log.d("TEST_VK", "success $r")
        return Gson().fromJson(r, Response::class.java).response
    }
    class Response {
        var response: List<Group> = ArrayList()
    }
}