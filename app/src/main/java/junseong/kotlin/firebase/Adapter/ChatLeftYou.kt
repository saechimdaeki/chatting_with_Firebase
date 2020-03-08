package junseong.kotlin.firebase.Adapter

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import junseong.kotlin.firebase.R
import kotlinx.android.synthetic.main.chat_left_you.view.*

class ChatLeftYou(val msg:String) : Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.chat_left_you
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
    viewHolder.itemView.left_msg.text=msg
    }

}