package dev.x1opya.lonely_meeting.groups_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.x1opya.lonely_meeting.R
import dev.x1opya.lonely_meeting.databinding.GroupFragmentBinding
import dev.x1opya.lonely_meeting.models.Group

class GroupFragment : Fragment() {
    private lateinit var mBinder: GroupFragmentBinding
    private lateinit var viewModel: GroupViewModel

    companion object {
        fun newInstance() = GroupFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("TEST_GROUPS", "onCreateView")
        mBinder = DataBindingUtil.inflate(inflater, R.layout.group_fragment, container, false)
        return mBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        mBinder.viewModel = viewModel
        mBinder.executePendingBindings()
        mBinder.rvGroups.layoutManager = LinearLayoutManager(activity)
        val adapter = GroupAdapter(arrayListOf())
        mBinder.rvGroups.adapter = adapter
        viewModel.groups.observe(this, Observer<ArrayList<Group>> {it?.let { adapter.replaceData(it) }})
        viewModel.updatePosts()
    }

}