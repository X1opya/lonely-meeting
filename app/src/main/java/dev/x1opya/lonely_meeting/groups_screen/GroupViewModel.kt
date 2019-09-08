package dev.x1opya.lonely_meeting.groups_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.x1opya.lonely_meeting.ArrayCallback
import dev.x1opya.lonely_meeting.models.Group
import javax.inject.Inject

class GroupViewModel : ViewModel() {

    private val groupModel: GroupModel = GroupModel()
    val groups = MutableLiveData<ArrayList<Group>>()

    fun updatePosts() {
        groupModel.getGroups(object : ArrayCallback<Group>{
            override fun onReady(list: ArrayList<Group>) {
                groups.value = list
            }

            override fun onFailure(msg: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


}