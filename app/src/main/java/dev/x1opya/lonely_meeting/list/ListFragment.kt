package dev.x1opya.lonely_meeting.list

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
import dev.x1opya.lonely_meeting.RecyclerAdapter
import dev.x1opya.lonely_meeting.databinding.MainFragmentBinding
import dev.x1opya.lonely_meeting.models.Post


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        mBinder.viewModel = viewModel
        mBinder.executePendingBindings()
        mBinder.recyclerView.layoutManager = LinearLayoutManager(activity)
        val adapter = RecyclerAdapter(arrayListOf())
        mBinder.recyclerView.adapter = adapter
            viewModel.posts.observe(this,
                Observer<ArrayList<Post>> { it?.let{ adapter.replaceData(it)} })
        viewModel.updatePosts()
    }

}
