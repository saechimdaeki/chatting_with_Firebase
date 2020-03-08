package junseong.kotlin.firebase.Adapter

import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import junseong.kotlin.firebase.R
import kotlinx.android.synthetic.main.chat_right_me.view.*

class ChatRightMe(val msg:String): Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.chat_right_me
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.right_msg.text=msg
    }

}