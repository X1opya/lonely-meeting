package dev.x1opya.lonely_meeting.groups_screen
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.x1opya.lonely_meeting.databinding.GroupItemBinding
import dev.x1opya.lonely_meeting.models.Group

class GroupAdapter(private var mList: ArrayList<Group>) : RecyclerView.Adapter<GroupAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GroupItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mList[position])
    }

    fun replaceData(list: java.util.ArrayList<Group>) {
        mList = list
        notifyDataSetChanged()
    }



    class Holder(private var mBinder: GroupItemBinding) : RecyclerView.ViewHolder(mBinder.root) {
        fun bind(item: Group) {
            mBinder.group = item
            mBinder.executePendingBindings()
        }
    }
}