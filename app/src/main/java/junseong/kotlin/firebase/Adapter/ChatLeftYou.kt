package junseong.kotlin.firebase.Adapter

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import junseong.kotlin.firebase.R

class ChatLeftYou : Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.chat_left_you
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

}