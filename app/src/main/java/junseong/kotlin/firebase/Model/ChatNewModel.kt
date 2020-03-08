package junseong.kotlin.firebase.Model

class ChatNewModel(val myUid:String,val yourUid:String, val message:String, val time:Long,val who:String){
    constructor():this("","","",0,"")
}