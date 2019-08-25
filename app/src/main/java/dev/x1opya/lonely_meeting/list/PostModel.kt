package dev.x1opya.lonely_meeting.list
import dev.x1opya.lonely_meeting.models.Post

class PostModel {
     fun getPosts(callback: onPostsReadyCallback) {
         var posts = ArrayList<Post>()
         posts.add(Post("Пост", "Описание"))
         posts.add(Post("Пост2", "Описание2"))
         posts.add(Post("Пост3", "Описание3"))
         posts.add(Post("Пост4", "Описание4"))
         callback.postsReady(posts)
     }

    interface onPostsReadyCallback {
        fun postsReady(posts: ArrayList<Post>)
    }
}