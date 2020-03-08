package junseong.kotlin.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import junseong.kotlin.firebase.Adapter.ChatLeftYou
import junseong.kotlin.firebase.Adapter.ChatRightMe
import junseong.kotlin.firebase.Model.ChatModel
import kotlinx.android.synthetic.main.activity_chat_room.*

class ChatRoomActivity : AppCompatActivity() {
    private lateinit var  auth:FirebaseAuth
    private val TAG =ChatRoomActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        auth=FirebaseAuth.getInstance()
        val myuid=auth.uid
        val youruid =intent.getStringExtra("youruid")
        val name=intent.getStringExtra("name")

        val adapter= GroupAdapter<GroupieViewHolder>()



        recyclerView_chat.adapter=adapter
        val db=FirebaseFirestore.getInstance()
        db.collection("message")
            .get()
            .addOnSuccessListener { result->
                for (document in result){
                    Log.d(TAG,document.toString())
                }
            }
        button.setOnClickListener {
            val message=editText.text.toString()
            editText.setText("")
            val chat=ChatModel(myuid.toString(),youruid,message)
        db.collection("message")
            .add(chat)
            .addOnSuccessListener {
                Log.d(TAG,"성공")
            }
            .addOnFailureListener {
                Log.d(TAG,"실패")

            }
        }

    }
}
