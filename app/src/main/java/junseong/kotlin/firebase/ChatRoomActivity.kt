package junseong.kotlin.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import junseong.kotlin.firebase.Model.ChatLeftYou
import junseong.kotlin.firebase.Model.ChatRightMe
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)


        val adapter= GroupAdapter<GroupieViewHolder>()

        adapter.add(ChatLeftYou())
        adapter.add(ChatLeftYou())
        adapter.add(ChatRightMe())
        adapter.add(ChatLeftYou())
        adapter.add(ChatRightMe())
        adapter.add(ChatLeftYou())
        adapter.add(ChatLeftYou())

        recyclerView_chat.adapter=adapter

    }
}
