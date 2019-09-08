package dev.x1opya.lonely_meeting.vk_api

import android.util.Log
import com.google.gson.Gson
import com.vk.api.sdk.requests.VKRequest
import dev.x1opya.lonely_meeting.models.Post
import org.json.JSONObject

class PostRequest(private val key: String = "редкие кадры одмена"): VKRequest<List<Post>>("newsfeed.search") {

    init {
        addParam("q", key)
        addParam("count", 3)
    }

    override fun parse(r: JSONObject): List<Post> {
        Log.d("TEST_VK", "success $r")
        return Gson().fromJson(r.getString("response"), Response::class.java).items
    }

    class Response {
        var items: List<Post> = ArrayList()
    }
}