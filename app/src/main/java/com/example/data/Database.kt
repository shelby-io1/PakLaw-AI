package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class UserStats(
    val id: Int = 1,
    val username: String = "Ahmed Khan",
    val email: String = "itsmeshahid2001@gmail.com",
    val bio: String = "Legal Tech Enthusiast & Law Student",
    val avatarUrl: String = "",
    val currentLevel: Int = 12,
    val totalXp: Int = 14200,
    val streak: Int = 7,
    val lawsLearned: Int = 45,
    val quizzesCompleted: Int = 120,
    val nextLevelXp: Int = 1000
)

data class Bookmark(
    val id: String,
    val title: String,
    val actName: String,
    val category: String,
    val timestamp: Long = System.currentTimeMillis()
)

data class SavedConversation(
    val id: Int = 0,
    val userQuery: String,
    val aiResponse: String,
    val timestamp: Long = System.currentTimeMillis()
)

class LawRepository {
    private val _stats = MutableStateFlow<UserStats?>(UserStats())
    val stats: Flow<UserStats?> = _stats.asStateFlow()

    private val _bookmarks = MutableStateFlow<List<Bookmark>>(emptyList())
    val bookmarks: Flow<List<Bookmark>> = _bookmarks.asStateFlow()

    private val _conversations = MutableStateFlow<List<SavedConversation>>(emptyList())
    val conversations: Flow<List<SavedConversation>> = _conversations.asStateFlow()

    suspend fun getStatsDirect(): UserStats {
        return _stats.value ?: UserStats().also { _stats.value = it }
    }

    suspend fun updateStats(updated: UserStats) {
        _stats.value = updated
    }

    suspend fun isBookmarked(sectionId: String): Boolean {
        return _bookmarks.value.any { it.id == sectionId }
    }

    suspend fun toggleBookmark(sectionId: String, title: String, act: String, category: String) {
        val current = _bookmarks.value.toMutableList()
        val index = current.indexOfFirst { it.id == sectionId }
        if (index >= 0) {
            current.removeAt(index)
        } else {
            current.add(Bookmark(id = sectionId, title = title, actName = act, category = category))
        }
        _bookmarks.value = current
    }

    suspend fun saveChat(query: String, answer: String): SavedConversation {
        val nextId = _conversations.value.size + 1
        val conv = SavedConversation(id = nextId, userQuery = query, aiResponse = answer)
        _conversations.value = listOf(conv) + _conversations.value
        return conv
    }

    suspend fun saveConversation(chat: SavedConversation) {
        val current = _conversations.value.toMutableList()
        if (current.none { it.id == chat.id }) {
            current.add(chat)
            _conversations.value = current
        }
    }

    suspend fun addBookmarkDirect(bookmark: Bookmark) {
        val current = _bookmarks.value.toMutableList()
        if (current.none { it.id == bookmark.id }) {
            current.add(bookmark)
            _bookmarks.value = current
        }
    }

    suspend fun clearConversations() {
        _conversations.value = emptyList()
    }

    suspend fun ensureStatsInitialized() {
        if (_stats.value == null) {
            _stats.value = UserStats()
        }
    }
}
