package dev.x1opya.lonely_meeting.vk_api

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class RequestSubscribersCount(groupId: Int) : VKRequest<Long>("groups.getMembers"){

    init {
        addParam("group_id", groupId)
        addParam("count", 0)
    }

    override fun parse(r: JSONObject): Long {
        return r.getJSONObject("response").getLong("count")
    }
}