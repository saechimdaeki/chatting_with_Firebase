package junseong.kotlin.firebase.Adapter

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import junseong.kotlin.firebase.R

class ChatRightMe : Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.chat_right_me
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

}