package com.example.data

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreManager {
    private fun getFirestoreSafe(): FirebaseFirestore? {
        return try {
            FirebaseFirestore.getInstance()
        } catch (e: Throwable) {
            android.util.Log.e("FirestoreManager", "FirebaseFirestore is not initialized: ${e.message}")
            null
        }
    }

    /**
     * Saves user stats in a cloud-based 'users' collection mapped by a unique userID.
     */
    fun saveUserStats(userId: String, stats: UserStats, onComplete: (Boolean) -> Unit) {
        val firestore = getFirestoreSafe()
        if (firestore == null) {
            onComplete(false)
            return
        }
        val data = hashMapOf(
            "username" to stats.username,
            "email" to stats.email,
            "bio" to stats.bio,
            "avatarUrl" to stats.avatarUrl,
            "currentLevel" to stats.currentLevel,
            "totalXp" to stats.totalXp,
            "streak" to stats.streak,
            "lawsLearned" to stats.lawsLearned,
            "quizzesCompleted" to stats.quizzesCompleted
        )
        firestore.collection("users").document(userId)
            .set(data)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    /**
     * Saves a bookmarked law section to Firestore in a subcollection under the userID document.
     */
    fun addBookmarkToCloud(userId: String, bookmark: Bookmark, onComplete: (Boolean) -> Unit) {
        val firestore = getFirestoreSafe()
        if (firestore == null) {
            onComplete(false)
            return
        }
        val data = hashMapOf(
            "id" to bookmark.id,
            "title" to bookmark.title,
            "actName" to bookmark.actName,
            "category" to bookmark.category,
            "timestamp" to bookmark.timestamp
        )
        firestore.collection("users").document(userId)
            .collection("bookmarks").document(bookmark.id)
            .set(data)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    /**
     * Removes a bookmarked law section from cloud storage.
     */
    fun removeBookmarkFromCloud(userId: String, bookmarkId: String, onComplete: (Boolean) -> Unit) {
        val firestore = getFirestoreSafe()
        if (firestore == null) {
            onComplete(false)
            return
        }
        firestore.collection("users").document(userId)
            .collection("bookmarks").document(bookmarkId)
            .delete()
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    /**
     * Retrieves all saved bookmarks from Firestore for a given userID.
     */
    fun fetchBookmarksFromCloud(userId: String, onSuccess: (List<Bookmark>) -> Unit, onFailure: (Exception) -> Unit) {
        val firestore = getFirestoreSafe()
        if (firestore == null) {
            onFailure(Exception("FirebaseFirestore not initialized"))
            return
        }
        firestore.collection("users").document(userId)
            .collection("bookmarks")
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<Bookmark>()
                for (doc in result) {
                    val id = doc.getString("id") ?: ""
                    val title = doc.getString("title") ?: ""
                    val actName = doc.getString("actName") ?: ""
                    val category = doc.getString("category") ?: ""
                    val timestamp = doc.getLong("timestamp") ?: System.currentTimeMillis()
                    list.add(Bookmark(id, title, actName, category, timestamp))
                }
                onSuccess(list)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    /**
     * Saves a high-fidelity AI chat conversation query-response pair to Firestore under user document.
     */
    fun saveChatToCloud(userId: String, conversation: SavedConversation, onComplete: (Boolean) -> Unit) {
        val firestore = getFirestoreSafe()
        if (firestore == null) {
            onComplete(false)
            return
        }
        val data = hashMapOf(
            "id" to conversation.id,
            "userQuery" to conversation.userQuery,
            "aiResponse" to conversation.aiResponse,
            "timestamp" to conversation.timestamp
        )
        // Store using its local ID to maintain consistency
        firestore.collection("users").document(userId)
            .collection("chats").document(conversation.id.toString())
            .set(data)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }

    /**
     * Retreives all saved AI helper conversations from Firestore for a given userID.
     */
    fun fetchChatsFromCloud(userId: String, onSuccess: (List<SavedConversation>) -> Unit, onFailure: (Exception) -> Unit) {
        val firestore = getFirestoreSafe()
        if (firestore == null) {
            onFailure(Exception("FirebaseFirestore not initialized"))
            return
        }
        firestore.collection("users").document(userId)
            .collection("chats")
            .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                val list = mutableListOf<SavedConversation>()
                for (doc in result) {
                    val idVal = doc.getLong("id")?.toInt() ?: 0
                    val userQuery = doc.getString("userQuery") ?: ""
                    val aiResponse = doc.getString("aiResponse") ?: ""
                    val timestamp = doc.getLong("timestamp") ?: System.currentTimeMillis()
                    list.add(SavedConversation(id = idVal, userQuery = userQuery, aiResponse = aiResponse, timestamp = timestamp))
                }
                onSuccess(list)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}
