package dev.x1opya.lonely_meeting.list_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dev.x1opya.lonely_meeting.R
import dev.x1opya.lonely_meeting.databinding.MainFragmentBinding
import dev.x1opya.lonely_meeting.models.Post
import java.util.ArrayList


class ListFragment : Fragment() {

    private lateinit var mBinder: MainFragmentBinding
    private lateinit var viewModel: ListViewModel

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinder = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        return mBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        mBinder.viewModel = viewModel
        mBinder.executePendingBindings()
        mBinder.recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = PostAdapter(arrayListOf())
        mBinder.recyclerView.adapter = adapter
        viewModel.posts.observe(this,
            Observer< List<Post>> { it?.let{ adapter.replaceData(it as ArrayList<Post>)} })
        viewModel.updatePosts()
    }

}
