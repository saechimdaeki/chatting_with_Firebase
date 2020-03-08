package junseong.kotlin.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import junseong.kotlin.firebase.Model.User
import junseong.kotlin.firebase.Model.UserItem
import kotlinx.android.synthetic.main.activity_chatlist.*

class ChatlistActivity : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()
    private val TAG= ChatlistActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlist)
        val adapter=GroupAdapter<GroupieViewHolder>()

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    adapter.add(UserItem(document.get("username").toString()))
                    Log.d(TAG,document.get("username").toString())
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                recyclerview_list.adapter=adapter
            }

            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        adapter.setOnItemClickListener { item, view ->
            val intent= Intent(this,ChatRoomActivity::class.java)
            startActivity(intent)
        }
    }
}
