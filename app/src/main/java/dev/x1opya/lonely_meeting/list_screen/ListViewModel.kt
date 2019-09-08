package dev.x1opya.lonely_meeting.list_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.x1opya.lonely_meeting.ArrayCallback
import dev.x1opya.lonely_meeting.models.Post

class ListViewModel : ViewModel() {
    var  posts = MutableLiveData<List<Post>>()
    private var  postModel: PostModel = PostModel()

    fun updatePosts(){
        postModel.getPosts(object : ArrayCallback<Post>{
            override fun onFailure(msg: String) {
                Log.d("TEST_ERROR", "error - $msg")
            }

            override fun onReady(list: ArrayList<Post>) {
                posts.value = list
            }
        })
    }
}
