package junseong.kotlin.firebase

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import junseong.kotlin.firebase.Model.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth// ...
    private val Tag:String =MainActivity::class.java.simpleName
// Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        join.setOnClickListener {
            val email=email_area.text.toString()
            val password=password_area.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(Tag,"성공")
                        val uid=FirebaseAuth.getInstance().uid ?: ""
                        val user= User(uid,username.text.toString())
                        val db = FirebaseFirestore.getInstance().collection("users")
                        db.document(uid)
                            .set(user)
                            .addOnSuccessListener {
                                Log.d(Tag,"데이터베이스 성공")
                            }
                            .addOnFailureListener {
                                Log.d(Tag,"데이터베이스 실패")
                            }
                        val intent=Intent(this,ChatlistActivity::class.java)
                        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {
                        Log.d(Tag,"실패")
                    }

                    // ...
                }
        }

        login.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent);
        }
    }
}
