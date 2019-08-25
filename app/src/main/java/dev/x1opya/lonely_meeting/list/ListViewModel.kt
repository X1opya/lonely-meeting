package dev.x1opya.lonely_meeting.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.x1opya.lonely_meeting.list.PostModel.*
import dev.x1opya.lonely_meeting.models.Post

class ListViewModel : ViewModel() {
    var  posts = MutableLiveData<ArrayList<Post>>()
    var  postModel: PostModel = PostModel()

    fun updatePosts(){
        postModel.getPosts(object : onPostsReadyCallback{
            override fun postsReady(list: ArrayList<Post>) {
                posts.value = list
            }
        })
    }



}
