package com.example.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class Tab {
    HOME, ASSISTANT, LEARN, LIBRARY, PROFILE, HELP
}

sealed interface AppScreen {
    object Tabbed : AppScreen
    object Section144Details : AppScreen
    data class SectionDetails(
        val sectionId: String,
        val title: String,
        val actName: String,
        val category: String,
        val originalText: String,
        val simpleEnglish: String,
        val simpleUrdu: String,
        val readTime: String = "5 min read"
    ) : AppScreen
    data class Quiz(val title: String, val questions: List<QuizQuestion>) : AppScreen
    object Login : AppScreen
    object SignUp : AppScreen
}

data class QuizQuestion(
    val text: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

class PakLawViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = LawRepository()

    // UI States
    private val _currentTab = MutableStateFlow(Tab.HOME)
    val currentTab: StateFlow<Tab> = _currentTab.asStateFlow()

    private val _activeScreen = MutableStateFlow<AppScreen>(AppScreen.Tabbed)
    val activeScreen: StateFlow<AppScreen> = _activeScreen.asStateFlow()

    // Screen navigation stack
    private val screenStack = mutableListOf<AppScreen>()

    // Live Database flows
    val userStats: StateFlow<UserStats?> = repo.stats.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    val bookmarkedItems: StateFlow<List<Bookmark>> = repo.bookmarks.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val savedConversations: StateFlow<List<SavedConversation>> = repo.conversations.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // API Quota Tracker
    private val PREFS_NAME = "paklaw_quota_prefs"
    private val KEY_QUOTA_COUNT = "api_quota_count"
    private val KEY_QUOTA_DATE = "api_quota_date"
    val DAILY_QUOTA_LIMIT = 5

    private val _apiQueriesToday = MutableStateFlow(0)
    val apiQueriesToday: StateFlow<Int> = _apiQueriesToday.asStateFlow()

    private val _apiQuotaExceeded = MutableStateFlow(false)
    val apiQuotaExceeded: StateFlow<Boolean> = _apiQuotaExceeded.asStateFlow()

    private fun getTodayDateString(): String {
        val sdf = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.US)
        return sdf.format(java.util.Date())
    }

    fun loadQuota() {
        val prefs = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val today = getTodayDateString()
        val savedDate = prefs.getString(KEY_QUOTA_DATE, "")
        if (savedDate == today) {
            val count = prefs.getInt(KEY_QUOTA_COUNT, 0)
            _apiQueriesToday.value = count
            _apiQuotaExceeded.value = count >= DAILY_QUOTA_LIMIT
        } else {
            prefs.edit().putString(KEY_QUOTA_DATE, today).putInt(KEY_QUOTA_COUNT, 0).apply()
            _apiQueriesToday.value = 0
            _apiQuotaExceeded.value = false
        }
    }

    private fun incrementQuota() {
        val prefs = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val today = getTodayDateString()
        val savedDate = prefs.getString(KEY_QUOTA_DATE, "")
        val count = if (savedDate == today) prefs.getInt(KEY_QUOTA_COUNT, 0) + 1 else 1

        prefs.edit().putString(KEY_QUOTA_DATE, today).putInt(KEY_QUOTA_COUNT, count).apply()
        _apiQueriesToday.value = count
        _apiQuotaExceeded.value = count >= DAILY_QUOTA_LIMIT
    }

    // Chat assistance states
    private val _chatInput = MutableStateFlow("")
    val chatInput: StateFlow<String> = _chatInput.asStateFlow()

    private val _isSending = MutableStateFlow(false)
    val isSending: StateFlow<Boolean> = _isSending.asStateFlow()

    private val _pendingUserQuery = MutableStateFlow<String?>(null)
    val pendingUserQuery: StateFlow<String?> = _pendingUserQuery.asStateFlow()

    private val _lastAnalysis = MutableStateFlow<LegalAnalysis?>(null)
    val lastAnalysis: StateFlow<LegalAnalysis?> = _lastAnalysis.asStateFlow()

    // Quiz states
    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    private val _answerChecked = MutableStateFlow(false)
    val answerChecked: StateFlow<Boolean> = _answerChecked.asStateFlow()

    private val _quizCompleted = MutableStateFlow(false)
    val quizCompleted: StateFlow<Boolean> = _quizCompleted.asStateFlow()

    private val _earnedXp = MutableStateFlow(0)
    val earnedXp: StateFlow<Int> = _earnedXp.asStateFlow()

    // Preset Quizzes
    val section144Quiz = listOf(
        QuizQuestion(
            text = "Which authority is empowered under Section 144 to issue emergency prohibition orders?",
            options = listOf("District Magistrate", "Police SHO", "Law Minister", "Private Security Guard"),
            correctIndex = 0,
            explanation = "Section 144 CrPC empowers a District Magistrate to issue temporary orders in urgent cases of nuisance or apprehended danger."
        ),
        QuizQuestion(
            text = "What is the maximum standard limit of gathering banned temporarily under Section 144?",
            options = listOf("2 or more people", "3 or more people", "4 or more people", "Exactly 10 people"),
            correctIndex = 2,
            explanation = "Section 144 is frequently deployed to forbid the gathering of 4 or more people in public areas to protect public safety."
        )
    )

    val generalLabourQuiz = listOf(
        QuizQuestion(
            text = "Which statute mandates prompt and regular payment of earned wages to employees in Pakistan?",
            options = listOf("Standing Orders Act", "Payment of Wages Act 1936", "Factories Act 1934", "Industrial Relations Act"),
            correctIndex = 1,
            explanation = "The Payment of Wages Act 1936 regulates the payment of wages to certain classes of persons employed in industry."
        ),
        QuizQuestion(
            text = "Which article of the Pakistani Constitution prohibits all forms of forced labor?",
            options = listOf("Article 11", "Article 15", "Article 19", "Article 25"),
            correctIndex = 0,
            explanation = "Article 11 clearly prohibits slavery, forced labor, and child labor in any industry/factory."
        )
    )

    // Firebase Auth user state
    private fun getAuthSafe(): com.google.firebase.auth.FirebaseAuth? {
        return try {
            com.google.firebase.auth.FirebaseAuth.getInstance()
        } catch (e: Throwable) {
            android.util.Log.e("PakLawViewModel", "FirebaseAuth initialization failed: ${e.message}")
            null
        }
    }

    private fun getFirestoreSafe(): com.google.firebase.firestore.FirebaseFirestore? {
        return try {
            com.google.firebase.firestore.FirebaseFirestore.getInstance()
        } catch (e: Throwable) {
            android.util.Log.e("PakLawViewModel", "FirebaseFirestore initialization failed: ${e.message}")
            null
        }
    }

    private val _firebaseUser = MutableStateFlow<com.google.firebase.auth.FirebaseUser?>(
        getAuthSafe()?.currentUser
    )
    val firebaseUser: StateFlow<com.google.firebase.auth.FirebaseUser?> = _firebaseUser.asStateFlow()

    init {
        loadQuota()
        viewModelScope.launch {
            repo.ensureStatsInitialized()
        }
        val currentUser = getAuthSafe()?.currentUser
        if (currentUser != null) {
            _activeScreen.value = AppScreen.Tabbed
            syncWithCloud(currentUser.uid)
        } else {
            _activeScreen.value = AppScreen.Login
        }
    }

    fun syncWithCloud(userId: String) {
        viewModelScope.launch {
            // Restore statistics from Firestore Database when logging in if they exist
            val firestore = getFirestoreSafe()
            firestore?.collection("users")
                ?.document(userId)
                ?.get()
                ?.addOnSuccessListener { doc ->
                    if (doc.exists()) {
                        val uName = doc.getString("username") ?: "User"
                        val emailVal = doc.getString("email") ?: "itsmeshahid2001@gmail.com"
                        val bioVal = doc.getString("bio") ?: "Legal Tech Enthusiast & Law Student"
                        val avaUrl = doc.getString("avatarUrl") ?: ""
                        val cLevel = doc.getLong("currentLevel")?.toInt() ?: 1
                        val tXp = doc.getLong("totalXp")?.toInt() ?: 0
                        val bStreak = doc.getLong("streak")?.toInt() ?: 0
                        val lLearned = doc.getLong("lawsLearned")?.toInt() ?: 0
                        val qCompleted = doc.getLong("quizzesCompleted")?.toInt() ?: 0
                        viewModelScope.launch {
                            repo.updateStats(UserStats(
                                id = 1,
                                username = uName,
                                email = emailVal,
                                bio = bioVal,
                                avatarUrl = avaUrl,
                                currentLevel = cLevel,
                                totalXp = tXp,
                                streak = bStreak,
                                lawsLearned = lLearned,
                                quizzesCompleted = qCompleted
                            ))
                        }
                    }
                }

            // Sync Bookmarks from Firestore to local Database
            FirestoreManager.fetchBookmarksFromCloud(userId, { cloudBkList ->
                viewModelScope.launch {
                    for (bm in cloudBkList) {
                        repo.addBookmarkDirect(bm)
                    }
                }
            }, {})

            // Sync Chats from Firestore to local Database
            FirestoreManager.fetchChatsFromCloud(userId, { cloudChatList ->
                viewModelScope.launch {
                    for (chat in cloudChatList) {
                        repo.saveConversation(chat)
                    }
                }
            }, {})
        }
    }

    fun signInWithGoogle(idToken: String, onResult: (Boolean, String?) -> Unit) {
        val auth = getAuthSafe()
        if (auth == null) {
            onResult(false, "Authentication service is currently unavailable.")
            return
        }
        val credential = com.google.firebase.auth.GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    _firebaseUser.value = user
                    _activeScreen.value = AppScreen.Tabbed
                    
                    if (user != null) {
                        syncWithCloud(user.uid)
                    }
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Failed to authenticate with Google.")
                }
            }
    }

    fun loginWithEmail(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val auth = getAuthSafe()
        if (auth == null) {
            onResult(false, "Authentication service is currently unavailable.")
            return
        }
        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    _firebaseUser.value = user
                    _activeScreen.value = AppScreen.Tabbed
                    
                    if (user != null) {
                        syncWithCloud(user.uid)
                    }
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Failed to log in.")
                }
            }
    }

    fun signUpWithEmail(email: String, password: String, username: String, onResult: (Boolean, String?) -> Unit) {
        val auth = getAuthSafe()
        if (auth == null) {
            onResult(false, "Authentication service is currently unavailable.")
            return
        }
        auth.createUserWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    _firebaseUser.value = user
                    _activeScreen.value = AppScreen.Tabbed
                    
                    // Initialize secure user statistics Profile locally & in Firestore
                    viewModelScope.launch {
                        val initialStats = UserStats(
                            id = 1,
                            username = username.trim(),
                            currentLevel = 1,
                            totalXp = 0,
                            streak = 1,
                            lawsLearned = 0,
                            quizzesCompleted = 0
                        )
                        repo.updateStats(initialStats)
                        user?.let { u ->
                            FirestoreManager.saveUserStats(u.uid, initialStats) {}
                        }
                    }
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Failed to sign up.")
                }
            }
    }

    fun logout(onResult: () -> Unit) {
        val auth = getAuthSafe()
        if (auth != null) {
            auth.signOut()
        }
        _firebaseUser.value = null
        _activeScreen.value = AppScreen.Login
        // Reset stats back to a generic name for Guest Mode
        viewModelScope.launch {
            repo.updateStats(UserStats(
                id = 1,
                username = "Guest Profile",
                currentLevel = 1,
                totalXp = 0,
                streak = 1,
                lawsLearned = 0,
                quizzesCompleted = 0
            ))
            repo.clearConversations()
        }
        onResult()
    }

    fun updateUserProfile(username: String, email: String, bio: String, avatarUrl: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val current = repo.getStatsDirect()
            val updated = current.copy(
                username = username,
                email = email,
                bio = bio,
                avatarUrl = avatarUrl
            )
            repo.updateStats(updated)
            val user = _firebaseUser.value
            if (user != null) {
                FirestoreManager.saveUserStats(user.uid, updated) { success ->
                    onResult(success)
                }
            } else {
                onResult(true)
            }
        }
    }

    fun continueAsGuest() {
        _activeScreen.value = AppScreen.Tabbed
    }

    // Navigation triggers
    fun changeTab(tab: Tab) {
        _currentTab.value = tab
        _activeScreen.value = AppScreen.Tabbed
        screenStack.clear()
    }

    fun navigateTo(screen: AppScreen) {
        screenStack.add(_activeScreen.value)
        _activeScreen.value = screen
    }

    fun navigateBack() {
        if (screenStack.isNotEmpty()) {
            _activeScreen.value = screenStack.removeAt(screenStack.size - 1)
        } else {
            _activeScreen.value = AppScreen.Tabbed
        }
    }

    // Bookmark Interaction
    fun toggleBookmark(sectionId: String, title: String, act: String, category: String) {
        viewModelScope.launch {
            repo.toggleBookmark(sectionId, title, act, category)
        }
    }

    fun isBookmarked(sectionId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            onResult(repo.isBookmarked(sectionId))
        }
    }

    // Chat Actions
    fun updateChatInput(input: String) {
        _chatInput.value = input
    }

    fun sendChatMessage(input: String? = null) {
        val prompt = input ?: _chatInput.value
        if (prompt.trim().isEmpty()) return

        loadQuota()
        if (_apiQueriesToday.value >= DAILY_QUOTA_LIMIT) {
            _apiQuotaExceeded.value = true
            return
        }

        incrementQuota()

        _pendingUserQuery.value = prompt
        _isSending.value = true
        _chatInput.value = ""

        viewModelScope.launch {
            val analysis = GeminiClient.getLegalAnalysis(prompt)
            _lastAnalysis.value = analysis

            // Save chat history
            val serializedObject = GeminiClient.serializeLegalAnalysis(analysis)
            val savedConv = repo.saveChat(prompt, serializedObject)

            val currentUser = getAuthSafe()?.currentUser
            if (currentUser != null) {
                FirestoreManager.saveChatToCloud(currentUser.uid, savedConv) { success ->
                    if (success) {
                        android.util.Log.d("PakLawViewModel", "Chat saved to Firestore successfully")
                    } else {
                        android.util.Log.e("PakLawViewModel", "Failed to save chat to Firestore")
                    }
                }
            }

            // Grant XP for consulting the Assistant
            val current = repo.getStatsDirect()
            val newXp = current.totalXp + 15
            val computedLevel = (newXp / 1000) + 1
            repo.updateStats(current.copy(totalXp = newXp, currentLevel = computedLevel))

            _pendingUserQuery.value = null
            _isSending.value = false
            _currentTab.value = Tab.ASSISTANT // ensure we show the chat assistant
            _activeScreen.value = AppScreen.Tabbed
        }
    }

    fun clearChatHistory() {
        viewModelScope.launch {
            repo.clearConversations()
            _lastAnalysis.value = null
        }
    }

    // Quiz Processing Actions
    fun startNewQuiz(title: String, questions: List<QuizQuestion>) {
        _currentQuestionIndex.value = 0
        _selectedAnswerIndex.value = null
        _answerChecked.value = false
        _quizCompleted.value = false
        _earnedXp.value = 0
        navigateTo(AppScreen.Quiz(title, questions))
    }

    fun selectQuizOption(index: Int) {
        if (!_answerChecked.value) {
            _selectedAnswerIndex.value = index
        }
    }

    fun checkQuizAnswer(question: QuizQuestion) {
        if (_selectedAnswerIndex.value == null) return
        _answerChecked.value = true
        if (_selectedAnswerIndex.value == question.correctIndex) {
            _earnedXp.value += 50
        }
    }

    fun nextQuizStep(questions: List<QuizQuestion>) {
        val nextIdx = _currentQuestionIndex.value + 1
        if (nextIdx < questions.size) {
            _currentQuestionIndex.value = nextIdx
            _selectedAnswerIndex.value = null
            _answerChecked.value = false
        } else {
            // Quiz completed! Save XP to Database
            _quizCompleted.value = true
            viewModelScope.launch {
                val current = repo.getStatsDirect()
                val totalAddedXp = _earnedXp.value
                val newXp = current.totalXp + totalAddedXp
                val computedLvl = (newXp / 1000) + 1
                val completedCount = current.quizzesCompleted + 1
                
                repo.updateStats(current.copy(
                    totalXp = newXp, 
                    currentLevel = computedLvl, 
                    quizzesCompleted = completedCount,
                    streak = current.streak + 1
                ))
            }
        }
    }

    // Cloud Synchronization Actions
    fun syncToFirestore(userId: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            val stats = repo.getStatsDirect()
            FirestoreManager.saveUserStats(userId, stats) { success ->
                if (success) {
                    val bookmarksList = bookmarkedItems.value
                    if (bookmarksList.isEmpty()) {
                        onComplete(true)
                    } else {
                        var bookmarksSuccess = true
                        var syncCount = 0
                        for (b in bookmarksList) {
                            FirestoreManager.addBookmarkToCloud(userId, b) { bSuccess ->
                                if (!bSuccess) bookmarksSuccess = false
                                syncCount++
                                if (syncCount == bookmarksList.size) {
                                    onComplete(bookmarksSuccess)
                                }
                            }
                        }
                    }
                } else {
                    onComplete(false)
                }
            }
        }
    }

    fun restoreFromFirestore(userId: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            FirestoreManager.fetchBookmarksFromCloud(userId, onSuccess = { bookmarksList ->
                viewModelScope.launch {
                    for (b in bookmarksList) {
                        repo.addBookmarkDirect(b)
                    }
                    onComplete(true)
                }
            }, onFailure = {
                onComplete(false)
            })
        }
    }
}
