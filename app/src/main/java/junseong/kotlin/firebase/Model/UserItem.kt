package junseong.kotlin.firebase.Model

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import junseong.kotlin.firebase.R

class UserItem : Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.message_listitem
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    }

}