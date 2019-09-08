package dev.x1opya.lonely_meeting.list_screen

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.x1opya.lonely_meeting.databinding.PostItemBinding
import dev.x1opya.lonely_meeting.models.Post
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

open class PostAdapter(list: ArrayList<Post>) : RecyclerView.Adapter<Holder>() {
    private var mList = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mList[position])
    }

    fun replaceData(list: java.util.ArrayList<Post>) {
        mList = list
        notifyDataSetChanged()
    }

}

@BindingAdapter("imageUrl")
fun loadImages(view: ImageView, imageUrl: String) {
    Picasso.get().load(Uri.parse(imageUrl)).into(view)
}

@BindingAdapter("android:setText")
fun setText(textView: TextView, date: Long){
//    val calendar = Calendar.getInstance().apply {
//        timeInMillis = date
//    }
    val date = Date(date * 1000)
    var formatter: SimpleDateFormat
    formatter = if(date.year == Date().year)
        SimpleDateFormat("dd MMM в HH:mm")
    else SimpleDateFormat("dd MMM yyyy в hh:MM")
    textView.text = formatter.format(date )
}

class Holder(private var mBinding: PostItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
    fun bind(item: Post) {
//        view.postTitle.text = item
//        mBinding.postText.text
        mBinding.post = item
        mBinding.executePendingBindings()
    }
}