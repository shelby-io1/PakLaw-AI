package com.example

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.ui.AppScreen
import com.example.ui.PakLawViewModel
import com.example.ui.Tab
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun testAppName() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("PakLaw AI", appName)
    }

    @Test
    fun testViewModelTabNavigation() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = PakLawViewModel(app)

        // Since no user is logged in, the initial screen is Login
        assertEquals(AppScreen.Login, viewModel.activeScreen.value)

        // Continue as guest to enter tabbed content
        viewModel.continueAsGuest()
        assertEquals(Tab.HOME, viewModel.currentTab.value)
        assertEquals(AppScreen.Tabbed, viewModel.activeScreen.value)

        // Change tab to ASSISTANT
        viewModel.changeTab(Tab.ASSISTANT)
        assertEquals(Tab.ASSISTANT, viewModel.currentTab.value)
        assertEquals(AppScreen.Tabbed, viewModel.activeScreen.value)

        // Go to some specific screen and check stack return
        viewModel.navigateTo(AppScreen.Section144Details)
        assertEquals(AppScreen.Section144Details, viewModel.activeScreen.value)

        viewModel.navigateBack()
        assertEquals(AppScreen.Tabbed, viewModel.activeScreen.value)
    }

    @Test
    fun testViewModelQuizFlow() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = PakLawViewModel(app)

        val questions = viewModel.section144Quiz
        assertNotNull(questions)
        assertTrue(questions.isNotEmpty())

        // Start Quiz
        viewModel.startNewQuiz("Section 144 Quiz", questions)
        val activeScreen = viewModel.activeScreen.value
        assertTrue(activeScreen is AppScreen.Quiz)
        assertEquals("Section 144 Quiz", (activeScreen as AppScreen.Quiz).title)

        assertEquals(0, viewModel.currentQuestionIndex.value)
        assertNull(viewModel.selectedAnswerIndex.value)
        assertFalse(viewModel.answerChecked.value)
        assertFalse(viewModel.quizCompleted.value)
        assertEquals(0, viewModel.earnedXp.value)

        // Select an option
        viewModel.selectQuizOption(0)
        assertEquals(0, viewModel.selectedAnswerIndex.value)

        // Check answer
        viewModel.checkQuizAnswer(questions[0])
        assertTrue(viewModel.answerChecked.value)
        // Correct answer for index 0 is 0, so earnedXp should be 50
        assertEquals(50, viewModel.earnedXp.value)

        // Proceed to next question
        viewModel.nextQuizStep(questions)
        assertEquals(1, viewModel.currentQuestionIndex.value)
        assertNull(viewModel.selectedAnswerIndex.value)
        assertFalse(viewModel.answerChecked.value)

        // Select incorrect option for second question
        viewModel.selectQuizOption(0) // correct is 2
        viewModel.checkQuizAnswer(questions[1])
        assertTrue(viewModel.answerChecked.value)
        // Earned XP shouldn't increase
        assertEquals(50, viewModel.earnedXp.value)

        // Complete the quiz
        viewModel.nextQuizStep(questions)
        assertTrue(viewModel.quizCompleted.value)
    }

    @Test
    fun testViewModelChatInputState() {
        val app = ApplicationProvider.getApplicationContext<Application>()
        val viewModel = PakLawViewModel(app)

        assertEquals("", viewModel.chatInput.value)
        viewModel.updateChatInput("Unpaid wages question")
        assertEquals("Unpaid wages question", viewModel.chatInput.value)
    }
}
