package com.islam.masjid_e_basheer.model.firestore

import android.util.Log
import com.google.firebase.firestore.*
import com.islam.masjid_e_basheer.model.Constants
import com.islam.masjid_e_basheer.model.Constants.Companion.TAG

class FirestoreRepository(private val mFireStoreCallback: FirestoreCallback)
    {

    private var mFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        mFirestore
            .collection(Constants.FIRESTORE_DOC_ANNOUNCEMENT)
            .get()
            .addOnSuccessListener {
                mFireStoreCallback.announcementsReceived(it)
            }
            .addOnFailureListener{
                Log.d(TAG, "announcement fail: $it")
            }

        mFirestore
            .collection(Constants.FIRESTORE_DOC_SIMPLE_PRAYER)
            .get()
            .addOnSuccessListener {
                mFireStoreCallback.simplePrayersReceived(it)
            }
            .addOnFailureListener{
                Log.d(TAG, "simpleprayer fail: $it")
            }
    }

    interface FirestoreCallback {
        fun announcementsReceived(snapshot: QuerySnapshot)
        fun simplePrayersReceived(snapshot: QuerySnapshot)
    }
}