package dev.x1opya.lonely_meeting

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.x1opya.lonely_meeting.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    lateinit var mBinder: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
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
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mBinder.viewModel = viewModel
        mBinder.executePendingBindings()
    }

}
