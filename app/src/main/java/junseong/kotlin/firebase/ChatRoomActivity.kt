package junseong.kotlin.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import junseong.kotlin.firebase.Adapter.ChatLeftYou
import junseong.kotlin.firebase.Adapter.ChatRightMe
import junseong.kotlin.firebase.Model.ChatModel
import junseong.kotlin.firebase.Model.ChatNewModel
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val TAG = ChatRoomActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        auth = FirebaseAuth.getInstance()
        val myuid = auth.uid
        val youruid = intent.getStringExtra("youruid")
        val name = intent.getStringExtra("name")

        val adapter = GroupAdapter<GroupieViewHolder>()

/* cloud Firestore방식임;; 바꾸자  Realtime으로바꾸려함.
val db = FirebaseFirestore.getInstance()

db.collection("message")
    .orderBy("time")
    .get()
    .addOnSuccessListener { result ->
        for (document in result) {
            Log.d(TAG, document.toString())
            val senderuid = document.get("myUid")
            val msg = document.get("message").toString()
            Log.e(TAG, senderuid.toString())
            Log.e(TAG, myuid)
            if (senderuid!!.equals(myuid)) {
                adapter.add(ChatRightMe(msg))
            } else {
                adapter.add(ChatLeftYou(msg))
            }
        }
        recyclerView_chat.adapter = adapter

    }
*/
// Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")
        val readRef=database.getReference("message").child(myuid.toString()).child(youruid)
        var childEventListener =object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
               // TODO("Not yet implemented")
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                //TODO("Not yet implemented")
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
               // TODO("Not yet implemented")
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                //TODO("Not yet implemented")
                Log.d(TAG,""+p0)
                val model=p0.getValue(ChatNewModel::class.java)
                val msg=model?.message.toString()
                val who =model?.who
                if(who=="me"){
                    adapter.add(ChatRightMe(msg))
                }else{
                    adapter.add(ChatLeftYou(msg))
                }
                //Log.d(TAG,"이건 메세지"+msg)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                //TODO("Not yet implemented")
            }

        }
        recyclerView_chat.adapter = adapter

        readRef.addChildEventListener(childEventListener)
        val myRef_list =database.getReference("message-user-list")
        button.setOnClickListener {
            val message = editText.text.toString()

            val chat =
                ChatNewModel(myuid.toString(), youruid, message, System.currentTimeMillis(), "me")
            myRef.child(myuid.toString()).child(youruid).push().setValue(chat)
            val chat_get =
                ChatNewModel(youruid, myuid.toString(), message, System.currentTimeMillis(), "you")
            myRef.child(youruid).child(myuid.toString()).push().setValue(chat_get)
            myRef_list.child(myuid.toString()).child(youruid).setValue(chat)
            editText.setText("")
        }
    }
}

