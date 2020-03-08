package junseong.kotlin.firebase.Model

import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import junseong.kotlin.firebase.R
import kotlinx.android.synthetic.main.message_listitem.view.*

class UserItem(val name:String) : Item<GroupieViewHolder>(){
    override fun getLayout(): Int {
        return R.layout.message_listitem
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.name.text=name
    }

}