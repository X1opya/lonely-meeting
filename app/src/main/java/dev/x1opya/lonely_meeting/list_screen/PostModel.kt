package dev.x1opya.lonely_meeting.list_screen
import android.util.Log
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import dev.x1opya.lonely_meeting.ArrayCallback
import dev.x1opya.lonely_meeting.models.Group
import dev.x1opya.lonely_meeting.vk_api.PostRequest
import dev.x1opya.lonely_meeting.models.Post
import dev.x1opya.lonely_meeting.vk_api.RequestGroup
import kotlin.math.abs

class PostModel {
     fun getPosts(callback: ArrayCallback<Post>) {
         postRequest(callback)
     }

    private fun postRequest(callback: ArrayCallback<Post>) = VK.execute(PostRequest(), object: VKApiCallback<List<Post>> {
        override fun fail(error: VKApiExecutionException) {
            callback.onFailure(error.localizedMessage)
        }

        override fun success(result: List<Post>) {
            postTitleRequest(result, callback)
        }
    })

    fun postTitleRequest(posts: List<Post>, callback: ArrayCallback<Post>) {
        var ids: ArrayList<Int> = ArrayList()
        for (item in posts) {
            ids.add(abs(item.owner_id))
        }
        ids.distinct()
        VK.execute(RequestGroup(ids), object : VKApiCallback<List<Group>> {
            override fun fail(error: VKApiExecutionException) {
                callback.onFailure(error.localizedMessage)
            }

            override fun success(result: List<Group>) {
                for (post in posts) {
                    for (group in result) {
                        if(abs(post.owner_id) == group.id) {
                            post.title = group.name
                            post.image = group.photo_50
                            continue
                        }
                    }
                    callback.onReady(posts as ArrayList<Post>)

                }
            }
        })
    }
}