package com.kipalog.mobile.api.firebase

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Single
import javax.inject.Inject

class FIRFirestore @Inject constructor(context: Context) {
    var db = FirebaseFirestore.getInstance()
    fun addDataToCollection(collection : String,  data : HashMap<String, Any>) : Single<DocumentReference>{
        return Single.create { singleEmitter ->
            db.collection(collection)
                    .add(data)
                    .addOnSuccessListener { singleEmitter.onSuccess(it) }
                    .addOnFailureListener { singleEmitter.onError(it) }
        }
    }

    fun readDataByCollection(collection: String) : Single<QuerySnapshot> {

        return Single.create { singleEmitter ->
            db.collection(collection)
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            singleEmitter.onSuccess(it.result)
                        }else {
                            singleEmitter.onError(it.exception!!)
                        }
                    }

        }
    }

    fun read(collection: String ) : Single<Task<QuerySnapshot>> {
        return Single.create { singleEmitter ->
            db.collection(collection)
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            singleEmitter.onSuccess(it)
                        } else {
                            singleEmitter.onError(it.exception!!)
                        }
                    }
        }
    }
    fun read(collection: String, field : String, value : Any) : Single<Task<QuerySnapshot>> {
        return Single.create { singleEmitter ->
            db.collection(collection)
                    .whereEqualTo(field,value)
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            singleEmitter.onSuccess(it)
                        } else {
                            singleEmitter.onError(it.exception!!)
                        }
                    }
        }
    }


}