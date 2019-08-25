package dev.x1opya.lonely_meeting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.x1opya.lonely_meeting.databinding.PostItemBinding
import dev.x1opya.lonely_meeting.models.Post

open class RecyclerAdapter(list: ArrayList<Post>) : RecyclerView.Adapter<Holder>() {
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

class Holder(private var mBinding: PostItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
    fun bind(item: Post) {
//        view.postTitle.text = item
//        mBinding.postText.text
        mBinding.post = item
        mBinding.executePendingBindings()
    }
}