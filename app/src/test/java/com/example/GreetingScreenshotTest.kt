package com.example

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ApplicationProvider
import com.example.ui.PakLawViewModel
import com.example.ui.Tab
import com.example.ui.screens.BottomNavBar
import com.example.ui.screens.CarouselCard
import com.example.ui.screens.MainAppScreen
import com.example.ui.theme.MyApplicationTheme
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = RobolectricDeviceQualifiers.Pixel8, sdk = [36])
class GreetingScreenshotTest {

  @get:Rule val composeTestRule = createComposeRule()

  @Test
  fun testUiComponents_screenshot() {
    composeTestRule.setContent {
      MyApplicationTheme {
        Column(modifier = Modifier.padding(16.dp)) {
          CarouselCard(
            title = "Criminal Law Basics",
            completion = "60% Complete",
            modulesCount = 12,
            imgUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuA_bGTFK6MhjB7Hcr1ZDZIQKVSl11U_5p8iP8x3YFKkNNH5_wYSsEK_l7nlbeIzHIRb8OYih_Cz6gHG_0SVSxkiSoolD4XQ3NlmDFKQkvL_dnpp1xYk0vO42N6WF38l1Q7c2Txur3a5ZycWvGUS9yC02x-zQ2o1uSu8Ex18VF7CO8Z-24pKnBSchXaHQ6ZUGmt4DfkBdYmueQeVq_JoWyp2L-mVU8Bhb2YDufCBd7TB6Lo-WlgJKX9Y7sU5hUgAkRxzs_7q5FRksNc",
            onClick = {}
          )
          Spacer(modifier = Modifier.height(20.dp))
          BottomNavBar(
            currentTab = Tab.HOME,
            onTabSelected = {}
          )
        }
      }
    }

    composeTestRule.waitForIdle()
    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/ui_components.png")
  }

  @Test
  fun testFullMainAppScreen_screenshot() {
    val app = ApplicationProvider.getApplicationContext<Application>()
    val viewModel = PakLawViewModel(app)

    composeTestRule.setContent {
      MyApplicationTheme {
        MainAppScreen(
          viewModel = viewModel,
          useDarkTheme = false,
          onThemeToggle = {}
        )
      }
    }

    composeTestRule.waitForIdle()
    composeTestRule.onRoot().captureRoboImage(filePath = "src/test/screenshots/main_app_screen_dark.png")
  }
}
