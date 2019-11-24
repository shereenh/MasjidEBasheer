package com.islam.masjid_e_basheer.model.firestore

import android.util.Log
import com.google.firebase.firestore.*
import com.islam.masjid_e_basheer.model.Constants
import com.islam.masjid_e_basheer.model.Constants.Companion.TAG

class FirestoreRepository(private val mFireStoreCallback: FirestoreCallback)
    {

    var mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        mFirestore
            .collection(Constants.FIRESTORE_DOC_ANNOUNCEMENT)
            .get()
            .addOnSuccessListener {
                mFireStoreCallback.announcementsReceived(it)
            }
            .addOnFailureListener{
                Log.d(TAG, "fail: $it")
            }

    }

    interface FirestoreCallback {
        fun announcementsReceived(snapshot: QuerySnapshot)
    }
}