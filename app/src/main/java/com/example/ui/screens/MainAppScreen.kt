package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.data.*
import com.example.ui.*

// --- High Quality Image Constants ---
const val IMG_PROFILE = "https://lh3.googleusercontent.com/aida-public/AB6AXuAUZqsyY_SsUPofSyuovgX5kjmzKJNT89FA8WEkvokzlT2QiTlATuy9UzZ0gBd8qxCKK0-N4561PmJUXwhmFtNKtJXmenCtIiBHd7LBi3fQttk16gsVcJa0TXxHs7nfUUV_leNU1l1HhZMYQvN0-1F7WdOl0hpUOngsOLlNBknghC6yz6834t3JNmXdbPb03dVSG7ditM-Ih2bELE0OdPC-hLi_oq8fhnFo7qHaKzI7qMP_JEnkt1xqp7frVrTSN3OlzSsTK9Zz2BE"
const val IMG_CRIMINAL_LAW = "https://lh3.googleusercontent.com/aida-public/AB6AXuA_bGTFK6MhjB7Hcr1ZDZIQKVSl11U_5p8iP8x3YFKkNNH5_wYSsEK_l7nlbeIzHIRb8OYih_Cz6gHG_0SVSxkiSoolD4XQ3NlmDFKQkvL_dnpp1xYk0vO42N6WF38l1Q7c2Txur3a5ZycWvGUS9yC02x-zQ2o1uSu8Ex18VF7CO8Z-24pKnBSchXaHQ6ZUGmt4DfkBdYmueQeVq_JoWyp2L-mVU8Bhb2YDufCBd7TB6Lo-WlgJKX9Y7sU5hUgAkRxzs_7q5FRksNc"
const val IMG_WOMENS_RIGHTS = "https://lh3.googleusercontent.com/aida-public/AB6AXuAC2vmKWivKNMyk3FH_ioa6kof8UrgEWoiNOuySvHNkzort1YduT68SqWlH7VRJeC7YN47kEYXNu811p7RtwHM-63I7HOHoykb1l2tU__PGAC2oqBgpg896J0EoLIGL75R8EYhrZheHibcg5kq_YSbx7_a8EyqvDdgh66D-oEKQjiSb29XCEpgt8vt3egq1VN4BdsameB65c-rtGD00_yqgcKjdAOz5QQMUWDpO8mgZfxTrG2WKeCtcUwGGRMqQcYBAE_-mLJX0mgM"
const val IMG_CYBER_LAW = "https://lh3.googleusercontent.com/aida-public/AB6AXuCuxQ-hddES2vEXRshNVA0JchbB1OmGeYcBVFcB3bDEy6tTW6PgFzI5hBE-Q3kXp1bxB6cjf88JwE9NQtdyyFl8e_q7Et0_Ys7Ls3w_iuNA6JPxnkuo6LOomZdhK4zlhyY_RA-AcbxkD_IHymXQbWnyTvHZiMGZWUrgsElTvH7X1rxEAdf1oMXs5M3eOxIS1kxEsovSF37_OyMYqbVRYg5PDwny_lnOqj-AOZSIhZLLW_8FJW1Si7CaE-cK4BEdIykPfNu3oyL_144"
const val IMG_SCALES = "https://lh3.googleusercontent.com/aida-public/AB6AXuB08D5FovJv66HykDvqqwFjBPaf8L8Gao4QxsAomRZxDWvsjirOjLobbVtXT4gtGkcsgMSknYijAdbcdRQbIju-IezGYTH6SDsC62PGFG8UcRLC_YUEZ-oOHfYQCltmODtqhhToUyM7Ijjpvefz8xedwtYFp7VgLpgQIX6xQQYuSPQDM4Kd1yKllZ64Fa1No8WVSO0p2fppJbPiXWIKYHP66nV06cp6mxTcPv8ut-02DIhAGJV4EitAYqrbYfXAeUdkS4EWaHbFXFg"
const val IMG_OFFICE = "https://lh3.googleusercontent.com/aida-public/AB6AXuAXElUnK4XxsvF1WlmghP-AT4rx9o4Ut0erM9ovAVHjzNkf3IYjCq7KoteovciHpubkfFku0vteYH9StSMXCOxjwJ9I2aI7NM4NFUsAOXnfBYv3EbBGUYAhkzdJQTBYIUPlxYgdhShqdsjYdM8RHcFSfdtEpLpvviMQ1s6xhtA1CQ7Rg8c4fZh0b73T-XbTI319lfUIvUBpIFX1GipO68DQ09v94DFGzI55mtRL4OTQfKqzDcO2dHQPBrSHrViN-3wSJIGIsBc_sUI"
const val IMG_DOME = "https://lh3.googleusercontent.com/aida-public/AB6AXuCqSJUkFVcaUTmVOdp4-MuyOYsi1fwmTaA0TjuY2bHUAEjbc2nBd7WThRt0NzHWVRi8RS5gAOC7MO2jeR0u3gJ-7aM5jNdOlGtmk3eqTQM-v6csKQ5CqWUsl7P4V6m71NlDFQYAD3vqDuP7hl1yeBLZU84LYwkpOWnECeXdJzRwAy1uAzq1b8onarGJS4Y_-0n6JcBOT-cEh5iQvfKlgJI0yA471j5q8GsqP5SeAWp_8nWxmtdUHZ0SXO9N0XfjzHBiuEcS4am5eUQ"

// --- Translation Strings (English & Urdu) ---
object Translations {
    val en = mapOf(
        "app_title" to "PakLaw AI",
        "welcome" to "Welcome back,",
        "streak" to "7 Day Streak",
        "great_job" to "Great Job!",
        "level" to "Level 12",
        "scholar" to "Legal Scholar",
        "quiz_reward" to "+50 XP Reward",
        "quiz_banner_title" to "Start Today's Quiz",
        "play_now" to "Play Now",
        "law_of_the_day" to "Law of the Day",
        "section_144_desc" to "Section 144 of the Code of Criminal Procedure (CrPC) empowers a District Magistrate to issue orders in urgent cases of nuisance or apprehended danger. It is frequently used to prohibit public gatherings to maintain peace.",
        "read_full" to "Read Full Summary",
        "continue" to "Continue Learning",
        "modules" to "Modules",
        "view_all" to "View All",
        "search_hint" to "Search for any law, section or topic...",
        "milestones" to "Your Milestones",
        "continuing_courses" to "Continuing Courses",
        "challenges" to "Challenges",
        "explore" to "Explore Topics",
        "language_setting" to "App Language",
        "bookmarks" to "Bookmarks",
        "saved" to "Saved Conversations",
        "sign_out" to "Sign Out",
        "disclaimer" to "DISCLAIMER: PakLaw AI provides legal information and guidance based on Pakistani law. It is not a law firm and does not provide formal legal advice."
    )

    val ur = mapOf(
        "app_title" to "پاک لا اے آئی",
        "welcome" to "خوش آمدید،",
        "streak" to "7 دن کی مسلسل حاضری",
        "great_job" to "بہت خوب!",
        "level" to "لیول 12",
        "scholar" to "قانونی اسکالر",
        "quiz_reward" to "+50 XP انعام",
        "quiz_banner_title" to "آج کا کوئز شروع کریں",
        "play_now" to "کھیلیں",
        "law_of_the_day" to "آج کا قانون",
        "section_144_desc" to "مجموعہ ضابطہ فوجداری (CrPC) کی دفعہ 144 مقامی حکومت کو یہ اختیار دیتی ہے کہ وہ کسی خاص علاقے میں چار یا اس سے زیادہ افراد کے اکٹھے ہونے پر عارضی پابندی لگا دے۔ یہ عام طور پر امن و امان برقرار رکھنے کے لئے لگائی جاتی ہے۔",
        "read_full" to "مکمل خلاصہ پڑھیں",
        "continue" to "سیکھنا جاری رکھیں",
        "modules" to "ماڈیولز",
        "view_all" to "سب دیکھیں",
        "search_hint" to "کسی بھی قانون، دفعہ یا موضوع کو تلاش کریں...",
        "milestones" to "آپ کے سنگ میل",
        "continuing_courses" to "جاری کورسز",
        "challenges" to "چیلنجز",
        "explore" to "موضوعات دریافت کریں",
        "language_setting" to "ایپ کی زبان",
        "bookmarks" to "محفوظ شدہ قوانین",
        "saved" to "محفوظ گفتگو",
        "sign_out" to "سائن آؤٹ کریں",
        "disclaimer" to "تنبیہ: پاک لا اے آئی صرف معلوماتی رہنمائی فراہم کرتا ہے اور یہ قانونی مشورہ نہیں ہے۔ عدالت کے لیے ہمیشہ کسی لائسنس یافتہ وکیل سے رجوع کریں۔"
    )
}

@Composable
fun MainAppScreen(
    viewModel: PakLawViewModel,
    useDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val activeScreen by viewModel.activeScreen.collectAsStateWithLifecycle()

    var isUrdu by remember { mutableStateOf(false) }
    val strings = if (isUrdu) Translations.ur else Translations.en
    val layoutDir = if (isUrdu) LayoutDirection.Rtl else LayoutDirection.Ltr

    CompositionLocalProvider(LocalLayoutDirection provides layoutDir) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                bottomBar = {
                    if (activeScreen == AppScreen.Tabbed) {
                        BottomNavBar(
                            currentTab = currentTab,
                            onTabSelected = { viewModel.changeTab(it) }
                        )
                    }
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    when (val screen = activeScreen) {
                        is AppScreen.Tabbed -> {
                            TabbedContent(
                                currentTab = currentTab,
                                viewModel = viewModel,
                                strings = strings,
                                isUrdu = isUrdu,
                                onUrduToggle = { isUrdu = it },
                                useDarkTheme = useDarkTheme,
                                onThemeToggle = onThemeToggle
                            )
                        }
                        is AppScreen.Section144Details -> {
                            Section144DetailScreen(
                                viewModel = viewModel,
                                strings = strings,
                                onBack = { viewModel.navigateBack() }
                            )
                        }
                        is AppScreen.SectionDetails -> {
                            DynamicSectionDetailScreen(
                                viewModel = viewModel,
                                details = screen,
                                onBack = { viewModel.navigateBack() }
                            )
                        }
                        is AppScreen.Quiz -> {
                            QuizScreen(
                                viewModel = viewModel,
                                quizTitle = screen.title,
                                questions = screen.questions,
                                strings = strings,
                                onBack = { viewModel.navigateBack() }
                            )
                        }
                        is AppScreen.Login -> {
                            LoginScreen(
                                viewModel = viewModel,
                                onNavigateToSignUp = { viewModel.navigateTo(AppScreen.SignUp) }
                            )
                        }
                        is AppScreen.SignUp -> {
                            SignUpScreen(
                                viewModel = viewModel,
                                onNavigateToLogin = { viewModel.navigateBack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

// --- Bottom Tab Navigation ---
@Composable
fun BottomNavBar(currentTab: Tab, onTabSelected: (Tab) -> Unit) {
    NavigationBar(
        tonalElevation = 8.dp,
        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
        modifier = Modifier.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        val tabsList = listOf(
            Tab.HOME to Pair("Home", Icons.Default.Home),
            Tab.ASSISTANT to Pair("AI Assistant", Icons.Default.SmartToy),
            Tab.LEARN to Pair("Learn", Icons.Default.School),
            Tab.HELP to Pair("Help", Icons.Default.Shield),
            Tab.LIBRARY to Pair("Library", Icons.Default.LocalLibrary),
            Tab.PROFILE to Pair("Profile", Icons.Default.Person)
        )

        tabsList.forEach { (tab, details) ->
            NavigationBarItem(
                selected = currentTab == tab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = details.second,
                        contentDescription = details.first,
                        tint = if (currentTab == tab) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = { Text(details.first, fontSize = 10.sp) },
                alwaysShowLabel = true,
                modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
            )
        }
    }
}

@Composable
fun TabbedContent(
    currentTab: Tab,
    viewModel: PakLawViewModel,
    strings: Map<String, String>,
    isUrdu: Boolean,
    onUrduToggle: (Boolean) -> Unit,
    useDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    Crossfade(targetState = currentTab, label = "TabTransition") { tab ->
        when (tab) {
            Tab.HOME -> HomeTab(viewModel, strings)
            Tab.ASSISTANT -> AIAssistantTab(viewModel, strings)
            Tab.LEARN -> LearnTab(viewModel, strings)
            Tab.HELP -> EmergencyHelpTab(viewModel, strings)
            Tab.LIBRARY -> LibraryTab(viewModel, strings)
            Tab.PROFILE -> ProfileTab(viewModel, strings, isUrdu, onUrduToggle, useDarkTheme, onThemeToggle)
        }
    }
}

// ==================== TAB 1: HOME ====================
@Composable
fun HomeTab(viewModel: PakLawViewModel, strings: Map<String, String>) {
    val stats by viewModel.userStats.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("home_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
    ) {
        // Welcoming Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = if (stats?.avatarUrl.isNullOrEmpty()) IMG_PROFILE else stats?.avatarUrl,
                        contentDescription = "User profile photo",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = strings["welcome"] ?: "Welcome back,",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stats?.username ?: "Ahmed!",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                IconButton(
                    onClick = { Toast.makeText(context, "No new notifications", Toast.LENGTH_SHORT).show() },
                    modifier = Modifier
                        .size(44.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                ) {
                    Box {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(MaterialTheme.colorScheme.error, CircleShape)
                                .align(Alignment.TopEnd)
                        )
                    }
                }
            }
        }

        // Bento Stats Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Streak Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(130.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = "Streak Flame",
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${stats?.streak ?: 7} Day Streak",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(3.dp),
                                modifier = Modifier.padding(bottom = 2.dp)
                            ) {
                                repeat(7) { dayIdx ->
                                    val barHeight = when (dayIdx) {
                                        0 -> 10.dp; 1 -> 15.dp; 2 -> 20.dp; 3 -> 14.dp; 4 -> 18.dp; 5 -> 22.dp; else -> 26.dp
                                    }
                                    val active = dayIdx <= (stats?.streak ?: 7) - 1
                                    Box(
                                        modifier = Modifier
                                            .width(6.dp)
                                            .height(barHeight)
                                            .clip(RoundedCornerShape(3.dp))
                                            .background(
                                                if (active) MaterialTheme.colorScheme.secondary
                                                else MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f)
                                            )
                                    )
                                }
                            }
                            Text(
                                text = strings["great_job"] ?: "Great Job!",
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // XP progress Card
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(130.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.MilitaryTech,
                                    contentDescription = "Medal",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Level ${stats?.currentLevel ?: 12}",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Text(
                                text = strings["scholar"] ?: "Legal Scholar",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }

                        Column {
                            val activeXp = stats?.totalXp ?: 14200
                            val xpProgress = (activeXp % 1000).toFloat() / 1000f
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${activeXp % 1000}/1000 XP",
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = xpProgress,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Play Quiz Card
        item {
            Card(
                onClick = { viewModel.startNewQuiz("Daily Trivia", viewModel.section144Quiz) },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("start_daily_quiz_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = strings["quiz_reward"] ?: "+50 XP Reward",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = strings["quiz_banner_title"] ?: "Start Today's Quiz",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Button(
                            onClick = { viewModel.startNewQuiz("Daily Trivia", viewModel.section144Quiz) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Text(
                                text = strings["play_now"] ?: "Play Now",
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.Quiz,
                        contentDescription = "Quiz logo",
                        tint = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                        modifier = Modifier.size(80.dp)
                    )
                }
            }
        }

        // Law of the Day
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = strings["law_of_the_day"] ?: "Law of the Day",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = "AI Star",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(6.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Criminal Procedure",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                text = "3 min read",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Section 144",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = strings["section_144_desc"] ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = { viewModel.navigateTo(AppScreen.Section144Details) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("btn_read_full_summary"),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = strings["read_full"] ?: "Read Full Summary",
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "Go",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }

        // Continue Learning Title
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = strings["continue"] ?: "Continue Learning",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = { viewModel.changeTab(Tab.LEARN) }) {
                    Text(strings["view_all"] ?: "View All", color = MaterialTheme.colorScheme.primary)
                }
            }
        }

        // Continue Learning Carousel
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    CarouselCard(
                        title = "Criminal Law Basics",
                        completion = "60% Complete",
                        modulesCount = 12,
                        imgUrl = IMG_CRIMINAL_LAW,
                        onClick = { viewModel.startNewQuiz("Criminal Law", viewModel.section144Quiz) }
                    )
                }
                item {
                    CarouselCard(
                        title = "Women's Rights",
                        completion = "15% Complete",
                        modulesCount = 8,
                        imgUrl = IMG_WOMENS_RIGHTS,
                        onClick = { viewModel.startNewQuiz("Women's Rights", viewModel.generalLabourQuiz) }
                    )
                }
                item {
                    CarouselCard(
                        title = "Cyber Laws 2024",
                        completion = "Not Started",
                        modulesCount = 5,
                        imgUrl = IMG_CYBER_LAW,
                        onClick = { viewModel.startNewQuiz("Cyber Laws", viewModel.section144Quiz) }
                    )
                }
            }
        }
    }
}

@Composable
fun CarouselCard(
    title: String,
    completion: String,
    modulesCount: Int,
    imgUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
            ) {
                AsyncImage(
                    model = imgUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                            )
                        )
                )
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                        .align(Alignment.BottomStart)
                ) {
                    Text(completion, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text("$modulesCount Modules", style = MaterialTheme.typography.bodyMedium, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

// ==================== TAB 2: AI ASSISTANT ====================
@Composable
fun AIAssistantTab(viewModel: PakLawViewModel, strings: Map<String, String>) {
    val stats by viewModel.userStats.collectAsStateWithLifecycle()
    val conversations by viewModel.savedConversations.collectAsStateWithLifecycle()
    val isSending by viewModel.isSending.collectAsStateWithLifecycle()
    val pendingUserQuery by viewModel.pendingUserQuery.collectAsStateWithLifecycle()
    val chatInput by viewModel.chatInput.collectAsStateWithLifecycle()
    val queriesToday by viewModel.apiQueriesToday.collectAsStateWithLifecycle()
    val quotaExceeded by viewModel.apiQuotaExceeded.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val orderedConversations = remember(conversations) { conversations.reversed() }
    val listState = rememberLazyListState()

    // Smooth scroll to the bottom on new message or sending state change
    LaunchedEffect(orderedConversations.size, isSending, pendingUserQuery) {
        val totalItems = orderedConversations.size + (if (pendingUserQuery != null) 1 else 0) + 1
        if (totalItems > 0) {
            listState.animateScrollToItem(totalItems)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("ai_assistant_screen")
    ) {
        // App top header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = if (stats?.avatarUrl.isNullOrEmpty()) IMG_PROFILE else stats?.avatarUrl,
                    contentDescription = "User profile photo",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ask PakLaw AI",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = { viewModel.clearChatHistory() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Clear Chats",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        // Conversations scroll list
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp, top = 8.dp)
        ) {
            // Introductory message
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.SmartToy,
                            contentDescription = "PakLaw Assistant Bot",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Hello! I am your legal assistant. I can help you understand laws, draft documents, or guide you through procedures.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "How can I assist you today?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Display chat messages (User on Right, AI on Left)
            items(orderedConversations) { chat ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // --- User Message Bubble (Right alignment) ---
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp),
                            modifier = Modifier
                                .widthIn(max = 290.dp)
                                .testTag("user_message_bubble")
                        ) {
                            Text(
                                text = chat.userQuery,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    // --- AI Response Bubble (Left alignment with Icon and Analysis Card or text description) ---
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(MaterialTheme.colorScheme.tertiary, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.SmartToy,
                                        contentDescription = "AI",
                                        tint = Color.White,
                                        modifier = Modifier.size(12.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "PakLaw AI",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.tertiary
                                )
                            }

                            val analysis = remember(chat.aiResponse) {
                                GeminiClient.deserializeLegalAnalysis(chat.aiResponse)
                            }

                            if (analysis.relevantLaws.isNotEmpty() || analysis.evidenceChecklist.isNotEmpty()) {
                                // Full structured case analysis
                                AnalysisCard(analysis, viewModel)
                            } else {
                                // Simple unstructured text fallback bubble
                                Surface(
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                                    shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                                    modifier = Modifier.widthIn(max = 295.dp)
                                ) {
                                    Text(
                                        text = analysis.rightsInvolved,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Show active pending message query
            pendingUserQuery?.let { query ->
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Pending User Query on the Right
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Surface(
                                color = MaterialTheme.colorScheme.primaryContainer,
                                shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp),
                                modifier = Modifier.widthIn(max = 290.dp)
                            ) {
                                Text(
                                    text = query,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        }

                        // Thinking Indicator on the Left
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .background(MaterialTheme.colorScheme.tertiary, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.SmartToy,
                                            contentDescription = "AI",
                                            tint = Color.White,
                                            modifier = Modifier.size(12.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "PakLaw AI",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.tertiary
                                    )
                                }

                                Surface(
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.1f)),
                                    modifier = Modifier.widthIn(max = 290.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        CircularProgressIndicator(
                                            modifier = Modifier.size(16.dp),
                                            strokeWidth = 2.dp,
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                        Text(
                                            text = "Analyzing legal situation... (Warming up AI engine on startup standby)",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Suggested Actions
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text(
                "Suggested Actions",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(6.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val prompts = listOf(
                    "Someone is blackmailing me",
                    "Unpaid salary",
                    "Police misconduct",
                    "Property disputes"
                )
                items(prompts) { pText ->
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(20.dp))
                            .clickable { viewModel.sendChatMessage(pText) }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(pText, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }
        }

        // Disclaimer Footnote
        Text(
            text = strings["disclaimer"] ?: "",
            fontSize = 9.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )

        // Text input field area / Daily Quota enforcement Alert
        if (quotaExceeded) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error.copy(alpha = 0.5f))
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Warning icon limit reached",
                        tint = MaterialTheme.colorScheme.error
                    )
                    Column {
                        Text(
                            text = "Daily AI Limit Reached",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "You've successfully completed $queriesToday of ${viewModel.DAILY_QUOTA_LIMIT} query slots today. Read core statutes or search again tomorrow!",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onErrorContainer.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = chatInput,
                    onValueChange = { viewModel.updateChatInput(it) },
                    placeholder = { Text(strings["search_hint"] ?: "Describe your legal situation...") },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("ai_chat_input_field"),
                    shape = RoundedCornerShape(24.dp),
                    leadingIcon = {
                        IconButton(onClick = { viewModel.sendChatMessage("Someone is threatening me online") }) {
                            Icon(imageVector = Icons.Default.Mic, contentDescription = "Mic")
                        }
                    },
                    maxLines = 2,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = { viewModel.sendChatMessage() },
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colorScheme.tertiary, CircleShape)
                        .testTag("btn_send_chat_query")
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun AnalysisCard(analysis: LegalAnalysis, viewModel: PakLawViewModel) {
    val context = LocalContext.current
    val bookmarksMap = remember { mutableStateMapOf<String, Boolean>() }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("active_case_analysis_card"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Heading Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        "CASE ANALYSIS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text("Just now", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Relevant laws block
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Gavel,
                        contentDescription = "Laws",
                        tint = MaterialTheme.colorScheme.tertiary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Relevant Laws", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(6.dp))
                analysis.relevantLaws.forEach { lawText ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .background(MaterialTheme.colorScheme.surfaceContainerLow, RoundedCornerShape(6.dp))
                            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f))
                            .padding(8.dp)
                    ) {
                        Text(lawText, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Rights Involved block
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.VerifiedUser,
                        contentDescription = "shield",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Rights Involved", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = analysis.rightsInvolved,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Evidence Checklist block with toggleable checkboxes
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Checklist,
                        contentDescription = "list",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Evidence Checklist", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                analysis.evidenceChecklist.forEachIndexed { i, checklistItem ->
                    val checkedKey = "active_$i"
                    val checkedState = bookmarksMap[checkedKey] ?: false
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { bookmarksMap[checkedKey] = !checkedState }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = { bookmarksMap[checkedKey] = it },
                            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(checklistItem, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Bottom Actions: Share PDF & Bookmark Save
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    IconButton(
                        onClick = {
                            viewModel.toggleBookmark("analysis_${analysis.relevantLaws.firstOrNull()?.take(10) ?: "gen"}", "AI Notice Summary", "Analysis Report", "AI Assistance")
                            Toast.makeText(context, "Added to saved bookmarks", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainerLow, CircleShape)
                    ) {
                        Icon(imageVector = Icons.Default.BookmarkBorder, contentDescription = "Bookmark")
                    }
                }

                Button(
                    onClick = { Toast.makeText(context, "Shared Legal Notice PDF document successfully!", Toast.LENGTH_LONG).show() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share", modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Share Legal PDF", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// ==================== TAB 3: LEARN ====================
@Composable
fun LearnTab(viewModel: PakLawViewModel, strings: Map<String, String>) {
    val stats by viewModel.userStats.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var searchTxt by remember { mutableStateOf("") }
    var showLeaderboard by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("learn_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp)
    ) {
        // App top header
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = if (stats?.avatarUrl.isNullOrEmpty()) IMG_PROFILE else stats?.avatarUrl,
                    contentDescription = "User profile photo",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = strings["app_title"] ?: "PakLaw AI",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Search Law Input
        item {
            OutlinedTextField(
                value = searchTxt,
                onValueChange = { searchTxt = it },
                placeholder = { Text(strings["search_hint"] ?: "Search laws, articles or topics...") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary)
            )
        }

        // Milestones
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        strings["milestones"] ?: "Your Milestones",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = { Toast.makeText(context, "Completed 2/4 Milestones", Toast.LENGTH_SHORT).show() }) {
                        Text(strings["view_all"] ?: "View All", color = MaterialTheme.colorScheme.primary)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    item { MilestoneItem("First Law", Icons.Default.MilitaryTech, active = true) }
                    item { MilestoneItem("Quiz Master", Icons.Default.Quiz, active = true) }
                    item { MilestoneItem("Case Closer", Icons.Default.Gavel, active = false) }
                    item { MilestoneItem("Historian", Icons.Default.HistoryEdu, active = false) }
                }
            }
        }

        // Continuing courses
        item {
            Column {
                Text(
                    strings["continuing_courses"] ?: "Continuing Courses",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                CourseTaskItem("Labour Rights 101", "Labour Law", 0.6f) {
                    viewModel.startNewQuiz("Labour Rights", viewModel.generalLabourQuiz)
                }
                Spacer(modifier = Modifier.height(8.dp))
                CourseTaskItem("Property Basics", "Civil Law", 0.25f) {
                    viewModel.startNewQuiz("Property Law", viewModel.section144Quiz)
                }
            }
        }

        // Challenges Screen section
        item {
            Column {
                Text(
                    strings["challenges"] ?: "Challenges",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                // Daily Quiz Streak
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CalendarToday,
                                    contentDescription = "Calendar",
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Daily Streak", color = Color.White, fontWeight = FontWeight.Bold)
                                Text("Finish 1 quiz today for 50 XP", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                            }
                        }
                        Button(
                            onClick = { viewModel.startNewQuiz("Section 144 Quiz", viewModel.section144Quiz) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Text("Start", color = MaterialTheme.colorScheme.onSecondaryContainer, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Leaderboard
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiary)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Group,
                                    contentDescription = "Leaderboard Group",
                                    tint = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("Weekly Leaderboard", color = Color.White, fontWeight = FontWeight.Bold)
                                Text("Rank in Top 10 for Gold Badge", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                            }
                        }
                        Button(
                            onClick = { showLeaderboard = true },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                        ) {
                            Text("View", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Categories Grid
        item {
            Column {
                Text(
                    strings["explore"] ?: "Explore Topics",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ExploreItem(
                            title = "Criminal Law",
                            icon = Icons.Default.Balance,
                            tint = MaterialTheme.colorScheme.error,
                            modifier = Modifier.weight(1f)
                        ) { viewModel.startNewQuiz("Criminal Law", viewModel.section144Quiz) }
                        ExploreItem(
                            title = "Cyber Law",
                            icon = Icons.Default.Shield,
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.weight(1f)
                        ) { viewModel.startNewQuiz("Cyber Law Info", viewModel.section144Quiz) }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ExploreItem(
                            title = "Family Law",
                            icon = Icons.Default.FamilyRestroom,
                            tint = Color(0xFFE91E63),
                            modifier = Modifier.weight(1f)
                        ) { viewModel.startNewQuiz("Family Law Basics", viewModel.section144Quiz) }
                        ExploreItem(
                            title = "Labour Law",
                            icon = Icons.Default.Engineering,
                            tint = Color(0xFFFF9800),
                            modifier = Modifier.weight(1f)
                        ) { viewModel.startNewQuiz("Labour Rights", viewModel.generalLabourQuiz) }
                    }
                }
            }
        }
    }

    if (showLeaderboard) {
        AlertDialog(
            onDismissRequest = { showLeaderboard = false },
            title = { Text("Weekly Leaderboard 🏆") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    LeaderboardMember(1, "Kamran Javed", "15,800 XP")
                    LeaderboardMember(2, "Mehwish Riaz", "14,950 XP")
                    LeaderboardMember(3, "${stats?.username ?: "Ahmed Khan"} (You)", "${stats?.totalXp ?: "14,200"} XP", active = true)
                    LeaderboardMember(4, "Faisal Iqbal", "13,100 XP")
                }
            },
            confirmButton = {
                TextButton(onClick = { showLeaderboard = false }) {
                    Text("Awesome")
                }
            }
        )
    }
}

@Composable
fun MilestoneItem(title: String, icon: ImageVector, active: Boolean) {
    Card(
        modifier = Modifier
            .width(96.dp)
            .alpha(if (active) 1f else 0.4f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        if (active) MaterialTheme.colorScheme.secondaryContainer
                        else MaterialTheme.colorScheme.surfaceVariant,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (active) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.outline
                )
            }
            Text(title, fontSize = 10.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun CourseTaskItem(name: String, category: String, progress: Float, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(category, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(name, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                }
                Text("${(progress * 100).toInt()}%", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = MaterialTheme.colorScheme.secondaryContainer,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
        }
    }
}

@Composable
fun ExploreItem(title: String, icon: ImageVector, tint: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(tint.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = tint)
            }
            Text(title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun LeaderboardMember(rank: Int, name: String, xp: String, active: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (active) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("$rank.", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(12.dp))
            Text(name, fontWeight = if (active) FontWeight.Bold else FontWeight.Normal)
        }
        Text(xp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
    }
}

// ==================== TAB 4: HELP (EMERGENCY CENTER) ====================
@Composable
fun EmergencyHelpTab(viewModel: PakLawViewModel, strings: Map<String, String>) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("help_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(MaterialTheme.colorScheme.error, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Emergency Alert Icon",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Emergency Rights Center",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.error,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Warning Alert row container
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.error.copy(alpha = 0.3f))
            ) {
                Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Alert banner logo",
                        tint = MaterialTheme.colorScheme.onErrorContainer
                    )
                    Text(
                        text = "If you are in immediate danger or facing legal coercion, select a category below for rapid guidance and reporting steps.",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
        }

        // Emergency categories grid
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    EmergencyCategoryCard("Cybercrime", "Hacking, online fraud, or cyberstalking.", Icons.Default.GppMaybe, MaterialTheme.colorScheme.tertiary, Modifier.weight(1f)) {
                        viewModel.sendChatMessage("Outline immediate legal steps for Cybercrime victim in cyberstalking and fraud")
                    }
                    EmergencyCategoryCard("Blackmail", "Extortion involving private files/messages.", Icons.Default.PrivacyTip, MaterialTheme.colorScheme.error, Modifier.weight(1f)) {
                        viewModel.sendChatMessage("Provide action plan for online blackmail and sensitive photo leak threats")
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    EmergencyCategoryCard("Harassment", "Workplace intimidation or public incidents.", Icons.Default.Shield, MaterialTheme.colorScheme.secondary, Modifier.weight(1f)) {
                        viewModel.sendChatMessage("Describe safety laws for women and harassment at workplace")
                    }
                    EmergencyCategoryCard("Misconduct", "Police illegal arrest or warrantless search.", Icons.Default.Balance, MaterialTheme.colorScheme.primary, Modifier.weight(1f)) {
                        viewModel.sendChatMessage("What are safe citizen rights during illegal arrest or warrantless police misconduct search?")
                    }
                }
            }
        }

        // Talk to helper banner with picture
        item {
            Card(
                onClick = { viewModel.changeTab(Tab.ASSISTANT) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(modifier = Modifier.height(200.dp)) {
                    AsyncImage(
                        model = IMG_OFFICE,
                        contentDescription = "Support desk",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.7f)))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Talk to PakLaw AI Now", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("Our AI assistant can draft preliminary legal notices and guide you through FIR registration in real-time.", color = Color.White.copy(alpha = 0.82f), fontSize = 12.sp)
                        }
                        Button(
                            onClick = { viewModel.changeTab(Tab.ASSISTANT) },
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("Launch AI Assistant", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(imageVector = Icons.Default.SmartToy, contentDescription = "Launch")
                        }
                    }
                }
            }
        }

        // Helplines list with click support
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    HelplineDialer("Emergency Help (Police)", "15", Icons.Default.Call, MaterialTheme.colorScheme.primary)
                    Divider()
                    HelplineDialer("FIA Cybercrime Support", "1991", Icons.Default.SupportAgent, MaterialTheme.colorScheme.tertiary)
                    Divider()
                    HelplineDialer("Women Helpline Support Desk", "1094", Icons.Default.Woman, MaterialTheme.colorScheme.error)
                }
            }
        }

        // Immediate Tips Checklist
        item {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Assignment, contentDescription = "Tips", tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Immediate Protection Tips", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(10.dp))
                ProtectionTip("01", "Keep evidence: Screenshot all chats, record threatening calls, and keep backups of messages.")
                ProtectionTip("02", "Do not pay: In blackmail cases, paying rarely stops the harasser and leads to more demands.")
                ProtectionTip("03", "Stay Silent: If detained by misconduct, you have the right to remain silent until legal representation is present.")
                ProtectionTip("04", "Verify identity: Ask for official ID badges from any civilian or legal agent claiming inspector powers.")
            }
        }
    }
}

@Composable
fun EmergencyCategoryCard(title: String, desc: String, icon: ImageVector, tint: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(tint.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = title, tint = tint)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(desc, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun HelplineDialer(name: String, number: String, icon: ImageVector, tint: Color) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { Toast.makeText(context, "Dialing helpline support: $number", Toast.LENGTH_SHORT).show() }
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = tint)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(name, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(number, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            }
        }
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "Dial")
    }
}

@Composable
fun ProtectionTip(num: String, text: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
            Text(num, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, modifier = Modifier.width(28.dp))
            Text(text, fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}



// ==================== TAB 5: LIBRARY ====================
@Composable
fun LibraryTab(viewModel: PakLawViewModel, strings: Map<String, String>) {
    var activeSubTab by remember { mutableStateOf("Statutes") } // "Statutes" vs "Glossary"
    var query by remember { mutableStateOf("") }
    var glossaryQuery by remember { mutableStateOf("") }
    var selectedBook by remember { mutableStateOf<BookModel?>(null) }
    var selectedRefinement by remember { mutableStateOf("All") }
    val context = LocalContext.current

    val listBooks = StaticLibraryData.listBooks

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("library_screen")
    ) {
        // App Tab Selector Row (Core Statutes vs Legal Glossary)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(if (activeSubTab == "Statutes") MaterialTheme.colorScheme.primary else Color.Transparent, RoundedCornerShape(8.dp))
                    .clickable { activeSubTab = "Statutes" }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Core Statutes",
                    color = if (activeSubTab == "Statutes") Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(if (activeSubTab == "Glossary") MaterialTheme.colorScheme.primary else Color.Transparent, RoundedCornerShape(8.dp))
                    .clickable { activeSubTab = "Glossary" }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Legal Glossary",
                    color = if (activeSubTab == "Glossary") Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (activeSubTab == "Statutes") {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(top = 4.dp, bottom = 32.dp)
            ) {
                if (selectedBook == null) {
                    item {
                        Column {
                            Text("Digital Law Library", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
                            Text("Search and study Pakistan's complete legal statutes.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    // Library search bar
                    item {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            placeholder = { Text("Search sections, keywords, or acts...") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary)
                        )
                    }

                    // Refine Chip Row
                    item {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Refine Library", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                                TextButton(onClick = { Toast.makeText(context, "All Filters applied", Toast.LENGTH_SHORT).show() }) {
                                    Icon(imageVector = Icons.Default.FilterList, contentDescription = "Filters", modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("All Filters", color = MaterialTheme.colorScheme.primary)
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                val refinementList = listOf("All", "Criminal Law", "Civil Law", "Cyber Law")
                                items(refinementList) { refText ->
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                if (refText == selectedRefinement) MaterialTheme.colorScheme.primary
                                                else MaterialTheme.colorScheme.surfaceContainerHigh,
                                                RoundedCornerShape(20.dp)
                                            )
                                            .clickable { selectedRefinement = refText }
                                            .padding(horizontal = 12.dp, vertical = 6.dp)
                                    ) {
                                        Text(
                                            refText,
                                            color = if (refText == selectedRefinement) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                                            style = MaterialTheme.typography.labelMedium
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // If query is present, show matching sections directly, else show books
                    if (query.trim().isNotEmpty()) {
                        val matches = listBooks.flatMap { b -> b.sections.map { s -> b to s } }
                            .filter { (_, s) ->
                                s.title.contains(query, ignoreCase = true) ||
                                s.excerpt.contains(query, ignoreCase = true) ||
                                s.originalText.contains(query, ignoreCase = true)
                            }

                        if (matches.isEmpty()) {
                            item {
                                Text(
                                  "No statutory matches found for \"$query\". Find or query about this using the AI Legal Assistant!",
                                  style = MaterialTheme.typography.bodyMedium,
                                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                                  modifier = Modifier.padding(12.dp)
                                )
                            }
                        } else {
                            items(matches) { (book, section) ->
                                Card(
                                    onClick = {
                                        viewModel.navigateTo(
                                            AppScreen.SectionDetails(
                                                sectionId = section.sectionId,
                                                title = section.title,
                                                actName = book.name,
                                                category = book.category,
                                                originalText = section.originalText,
                                                simpleEnglish = section.simpleEnglish,
                                                simpleUrdu = section.simpleUrdu,
                                                readTime = section.readTime
                                            )
                                        )
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Text(book.category, fontSize = 10.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                                        Text(section.title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                        Text(section.excerpt, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(book.name, fontSize = 11.sp, color = MaterialTheme.colorScheme.secondary)
                                    }
                                }
                            }
                        }
                    } else {
                        // Categories list based on refinement
                        val filteredBooks = if (selectedRefinement == "All") listBooks else listBooks.filter { it.category == selectedRefinement }
                        item {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                filteredBooks.forEach { book ->
                                    Card(
                                        onClick = { selectedBook = book },
                                        modifier = Modifier.fillMaxWidth(),
                                        shape = RoundedCornerShape(14.dp),
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(14.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                                                AsyncImage(
                                                    model = book.imageUrl,
                                                    contentDescription = book.name,
                                                    modifier = Modifier
                                                        .size(48.dp)
                                                        .clip(RoundedCornerShape(8.dp)),
                                                    contentScale = ContentScale.Crop
                                                )
                                                Spacer(modifier = Modifier.width(12.dp))
                                                Column {
                                                    Text(book.category, fontSize = 10.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                                                    Text(book.name, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                                    Text(book.sectionsCount, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                                }
                                            }
                                            IconButton(
                                                onClick = { selectedBook = book },
                                                modifier = Modifier
                                                    .size(36.dp)
                                                    .background(MaterialTheme.colorScheme.secondary, CircleShape)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.ArrowForward,
                                                    contentDescription = "Forward Icon button",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Lesson Tracking Card
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
                        ) {
                            Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                                AsyncImage(
                                    model = StaticLibraryData.IMG_SCALES,
                                    contentDescription = "Scales",
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Continue Legal Literacy", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("You're 65% through 'Understanding Fundamental Rights'.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("Lesson 4 of 7", fontSize = 11.sp, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                                        Button(
                                            onClick = { viewModel.startNewQuiz("Fundamental Rights", viewModel.section144Quiz) },
                                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                                        ) {
                                            Text("Resume Course", fontSize = 11.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Recent Legislative updates
                    item {
                        Column {
                            Text("Recent Legislative Updates", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(10.dp))
                            UpdateItem("Punjab Defamation Act 2024", "Updated text including recent High Court amendments.", "2 hours ago")
                            Spacer(modifier = Modifier.height(8.dp))
                            UpdateItem("Finance Act 2024-25", "Complete tax slabs and corporate compliance updates.", "Yesterday")
                        }
                    }
                } else {
                    // Book detail explorer showing all dynamic sections within selected Book
                    val book = selectedBook!!
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { selectedBook = null }) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Return to library")
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Back to Books", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary, modifier = Modifier.clickable { selectedBook = null })
                        }
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                                AsyncImage(
                                    model = book.imageUrl,
                                    contentDescription = book.name,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(book.category, fontSize = 10.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                                    Text(book.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(book.description, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Statutes & Key Sections (${book.sections.size})",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    // Search within this book specifically
                    item {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            placeholder = { Text("Filter sections of this book...") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search") },
                            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary)
                        )
                    }

                    val filteredSections = book.sections.filter {
                        query.isEmpty() ||
                        it.title.contains(query, ignoreCase = true) ||
                        it.excerpt.contains(query, ignoreCase = true)
                    }

                    if (filteredSections.isEmpty()) {
                        item {
                            Text(
                                "No sections match filter. Clear search to view all core code structures.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    } else {
                        items(filteredSections) { sec ->
                            Card(
                                onClick = {
                                    viewModel.navigateTo(
                                        AppScreen.SectionDetails(
                                            sectionId = sec.sectionId,
                                            title = sec.title,
                                            actName = book.name,
                                            category = book.category,
                                            originalText = sec.originalText,
                                            simpleEnglish = sec.simpleEnglish,
                                            simpleUrdu = sec.simpleUrdu,
                                            readTime = sec.readTime
                                        )
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f)),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(sec.title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(sec.excerpt, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                    IconButton(
                                        onClick = {
                                            viewModel.navigateTo(
                                                AppScreen.SectionDetails(
                                                    sectionId = sec.sectionId,
                                                    title = sec.title,
                                                    actName = book.name,
                                                    category = book.category,
                                                    originalText = sec.originalText,
                                                    simpleEnglish = sec.simpleEnglish,
                                                    simpleUrdu = sec.simpleUrdu,
                                                    readTime = sec.readTime
                                                )
                                            )
                                        },
                                        modifier = Modifier
                                            .size(32.dp)
                                            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.ArrowForward,
                                            contentDescription = "Read statute",
                                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // RENDER REQUESTED LEGAL GLOSSARY SUB-TAB
            val filteredGlossary = StaticLibraryData.glossaryTerms.filter {
                glossaryQuery.isEmpty() ||
                it.term.contains(glossaryQuery, ignoreCase = true) ||
                it.definition.contains(glossaryQuery, ignoreCase = true)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 4.dp, bottom = 32.dp)
            ) {
                item {
                    Column {
                        Text("Legal Glossary", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
                        Text("Simple, everyday definitions of foundational Pakistani legal terminology.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                // Glossary Search field
                item {
                    OutlinedTextField(
                        value = glossaryQuery,
                        onValueChange = { glossaryQuery = it },
                        placeholder = { Text("Search legal terms (e.g. FIR, Bail)...") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search glossary") },
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = MaterialTheme.colorScheme.primary)
                    )
                }

                if (filteredGlossary.isEmpty()) {
                    item {
                        Text("No definitions found for \"$glossaryQuery\"", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(14.dp))
                    }
                } else {
                    items(filteredGlossary) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f)),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest)
                        ) {
                            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.term,
                                        style = MaterialTheme.typography.titleMedium,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Box(
                                        modifier = Modifier
                                            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(8.dp))
                                            .padding(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text(item.category, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSecondaryContainer, fontWeight = FontWeight.Bold)
                                    }
                                }
                                Text(
                                    text = item.definition,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun UpdateItem(title: String, desc: String, duration: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.12f))
    ) {
        Row(modifier = Modifier.padding(14.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.error.copy(alpha = 0.15f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.NewReleases, contentDescription = "Alert", tint = MaterialTheme.colorScheme.error)
            }
            Column {
                Text(title, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(2.dp))
                Text(desc, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(4.dp))
                Text(duration, fontSize = 10.sp, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ==================== TAB 6: PROFILE ====================
@Composable
fun ProfileTab(
    viewModel: PakLawViewModel,
    strings: Map<String, String>,
    isUrdu: Boolean,
    onUrduToggle: (Boolean) -> Unit,
    useDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    val stats by viewModel.userStats.collectAsStateWithLifecycle()
    val bookmarks by viewModel.bookmarkedItems.collectAsStateWithLifecycle()
    val firebaseUser by viewModel.firebaseUser.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var showEditDialog by remember { mutableStateOf(false) }

    val avatarsList = listOf(
        Pair("Default Scholar", IMG_PROFILE),
        Pair("Scales of Justice", IMG_SCALES),
        Pair("Cyber Expert", IMG_CYBER_LAW),
        Pair("Women's Rights", IMG_WOMENS_RIGHTS),
        Pair("Criminal Law", IMG_CRIMINAL_LAW)
    )

    if (showEditDialog) {
        var tempUsername by remember { mutableStateOf(stats?.username ?: "Ahmed Khan") }
        var tempEmail by remember { mutableStateOf(stats?.email ?: "itsmeshahid2001@gmail.com") }
        var tempBio by remember { mutableStateOf(stats?.bio ?: "Legal Tech Enthusiast & Law Student") }
        var tempAvatarUrl by remember { mutableStateOf(stats?.avatarUrl ?: "") }

        AlertDialog(
            onDismissRequest = { showEditDialog = false },
            title = {
                Text(
                    text = "Edit Profile Info",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    OutlinedTextField(
                        value = tempUsername,
                        onValueChange = { tempUsername = it },
                        label = { Text("Profile Name") },
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name") },
                        modifier = Modifier.fillMaxWidth().testTag("edit_username_input")
                    )

                    OutlinedTextField(
                        value = tempEmail,
                        onValueChange = { tempEmail = it },
                        label = { Text("Email Address") },
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
                        modifier = Modifier.fillMaxWidth().testTag("edit_email_input")
                    )

                    OutlinedTextField(
                        value = tempBio,
                        onValueChange = { tempBio = it },
                        label = { Text("Short Bio") },
                        minLines = 2,
                        maxLines = 4,
                        leadingIcon = { Icon(Icons.Default.Info, contentDescription = "Bio") },
                        modifier = Modifier.fillMaxWidth().testTag("edit_bio_input")
                    )

                    OutlinedTextField(
                        value = tempAvatarUrl,
                        onValueChange = { tempAvatarUrl = it },
                        label = { Text("Profile Photo URL (Custom)") },
                        leadingIcon = { Icon(Icons.Default.Link, contentDescription = "Link") },
                        modifier = Modifier.fillMaxWidth().testTag("edit_avatar_input")
                    )

                    Text(
                        text = "Or Choose from Presets:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        avatarsList.forEach { avatar ->
                            val isSelected = (tempAvatarUrl == avatar.second) || (avatar.second == IMG_PROFILE && tempAvatarUrl.isEmpty())
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = if (isSelected) 3.dp else 1.dp,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        tempAvatarUrl = if (avatar.second == IMG_PROFILE) "" else avatar.second
                                    }
                            ) {
                                AsyncImage(
                                    model = avatar.second,
                                    contentDescription = avatar.first,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
                        if (tempEmail.trim().isNotEmpty() && !tempEmail.matches(emailRegex)) {
                            Toast.makeText(context, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        viewModel.updateUserProfile(
                            username = tempUsername,
                            email = tempEmail,
                            bio = tempBio,
                            avatarUrl = tempAvatarUrl
                        ) { success ->
                            if (success) {
                                Toast.makeText(context, "Profile updated successfully!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Failed to update profile", Toast.LENGTH_SHORT).show()
                            }
                        }
                        showEditDialog = false
                    },
                    modifier = Modifier.testTag("save_profile_button")
                ) {
                    Text("Save Changes")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .testTag("profile_screen"),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp)
    ) {
        // App top title
        item {
            Text("Profile Panel", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
        }

        // Profile Avatar header
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box {
                    AsyncImage(
                        model = if (stats?.avatarUrl.isNullOrEmpty()) IMG_PROFILE else stats?.avatarUrl,
                        contentDescription = "Avatar profile illustration",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(3.dp, MaterialTheme.colorScheme.primary, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = { showEditDialog = true },
                        modifier = Modifier
                            .size(28.dp)
                            .background(MaterialTheme.colorScheme.secondary, CircleShape)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.White, modifier = Modifier.size(14.dp))
                    }
                }
                Text(stats?.username ?: "Ahmed Khan", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text(
                    text = "Level ${stats?.currentLevel ?: 12} Scholar".uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                if (!stats?.email.isNullOrEmpty()) {
                    Text(
                        text = stats?.email ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                if (!stats?.bio.isNullOrEmpty()) {
                    Text(
                        text = stats?.bio ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                // Level Progress bar
                val currentXp = stats?.totalXp ?: 14200
                val percent = ((currentXp % 1000).toFloat() / 1000f) * 100f
                val remainingXp = 1000 - (currentXp % 1000)
                Spacer(modifier = Modifier.height(4.dp))
                Column(modifier = Modifier.width(220.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Next Level: ${remainingXp} XP", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("${percent.toInt()}%", fontSize = 11.sp, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = percent / 100f,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }

        // Stats grid (Bento style)
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    BentoProfileStat("Streak", "${stats?.streak ?: 7} Days", Icons.Default.LocalFireDepartment, MaterialTheme.colorScheme.secondary, Modifier.weight(1f))
                    BentoProfileStat("Total XP", "${stats?.totalXp ?: 14200}", Icons.Default.MilitaryTech, MaterialTheme.colorScheme.primary, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    BentoProfileStat("Laws Logged", "${stats?.lawsLearned ?: 45}", Icons.Default.AutoStories, MaterialTheme.colorScheme.tertiary, Modifier.weight(1f))
                    BentoProfileStat("Quizzes", "${stats?.quizzesCompleted ?: 120}", Icons.Default.Quiz, MaterialTheme.colorScheme.error, Modifier.weight(1f))
                }
            }
        }

        // Bookmarks dropdown section
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
            ) {
                var expandedBook by remember { mutableStateOf(false) }
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expandedBook = !expandedBook }
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Bookmarks, contentDescription = "Bookmarks", tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Bookmarked Items (${bookmarks.size})", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                        }
                        Icon(
                            imageVector = if (expandedBook) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = "Expand"
                        )
                    }

                    if (expandedBook) {
                        if (bookmarks.isEmpty()) {
                            Text("No bookmarked items yet. Bookmark some section summaries in the catalog or AI chat to see them here.", fontSize = 12.sp, modifier = Modifier.padding(14.dp), color = MaterialTheme.colorScheme.onSurfaceVariant)
                        } else {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 14.dp)
                                    .padding(bottom = 14.dp)
                            ) {
                                bookmarks.forEach { b ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                if (b.id == "section_144") {
                                                    viewModel.navigateTo(AppScreen.Section144Details)
                                                } else {
                                                    val matchedBookAndSec = StaticLibraryData.listBooks.flatMap { book ->
                                                        book.sections.map { sec -> book to sec }
                                                    }.firstOrNull { it.second.sectionId == b.id }
                                                    if (matchedBookAndSec != null) {
                                                        val (book, sec) = matchedBookAndSec
                                                        viewModel.navigateTo(
                                                            AppScreen.SectionDetails(
                                                                sectionId = sec.sectionId,
                                                                title = sec.title,
                                                                actName = book.name,
                                                                category = book.category,
                                                                originalText = sec.originalText,
                                                                simpleEnglish = sec.simpleEnglish,
                                                                simpleUrdu = sec.simpleUrdu,
                                                                readTime = sec.readTime
                                                            )
                                                        )
                                                    } else {
                                                        viewModel.navigateTo(AppScreen.Section144Details)
                                                    }
                                                }
                                            }
                                            .padding(vertical = 6.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(b.title, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                            Text("${b.actName} - ${b.category}", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        }
                                        Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "open", modifier = Modifier.size(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Firebase Firestore Cloud Sync section
        item {
            val initialUserId = firebaseUser?.uid ?: "guest_paklaw_45405"
            var userId by remember(firebaseUser) { mutableStateOf(initialUserId) }
            var isSyncing by remember { mutableStateOf(false) }
            var isRestoring by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("firebase_sync_card"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Cloud,
                            contentDescription = "Cloud Icon",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Firebase Firestore Sync",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = "Synchronize your achievements, quiz levels, XP, and bookmarked law sections to cloud storage.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    OutlinedTextField(
                        value = userId,
                        onValueChange = { userId = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Cloud User Profile ID", fontSize = 11.sp) },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = "User Icon", modifier = Modifier.size(18.dp)) },
                        singleLine = true,
                        textStyle = MaterialTheme.typography.bodyMedium
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                if (userId.trim().isEmpty()) {
                                    Toast.makeText(context, "Please enter a Profile ID", Toast.LENGTH_SHORT).show()
                                    return@Button
                                }
                                isSyncing = true
                                viewModel.syncToFirestore(userId.trim()) { success ->
                                    isSyncing = false
                                    if (success) {
                                        Toast.makeText(context, "Backup synced successfully to Firestore!", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(context, "Error saving cloud data. Check your network.", Toast.LENGTH_LONG).show()
                                    }
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .testTag("cloud_backup_button"),
                            shape = RoundedCornerShape(8.dp),
                            enabled = !isSyncing && !isRestoring
                        ) {
                            if (isSyncing) {
                                CircularProgressIndicator(modifier = Modifier.size(16.dp), color = Color.White)
                            } else {
                                Icon(Icons.Default.CloudUpload, contentDescription = "Upload", modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Backup", fontSize = 12.sp)
                            }
                        }

                        OutlinedButton(
                            onClick = {
                                if (userId.trim().isEmpty()) {
                                    Toast.makeText(context, "Please enter a Profile ID", Toast.LENGTH_SHORT).show()
                                    return@OutlinedButton
                                }
                                isRestoring = true
                                viewModel.restoreFromFirestore(userId.trim()) { success ->
                                    isRestoring = false
                                    if (success) {
                                        Toast.makeText(context, "Achievements restored successfully from Firestore!", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(context, "Profile database records empty or not found.", Toast.LENGTH_LONG).show()
                                    }
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .testTag("cloud_restore_button"),
                            shape = RoundedCornerShape(8.dp),
                            enabled = !isSyncing && !isRestoring
                        ) {
                            if (isRestoring) {
                                CircularProgressIndicator(modifier = Modifier.size(16.dp), color = MaterialTheme.colorScheme.primary)
                            } else {
                                Icon(Icons.Default.CloudDownload, contentDescription = "Download", modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Restore", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        // Settings items
        item {
            Column {
                Text("Settings panel", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        // App Language toggle
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Language, contentDescription = "Lang", tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(strings["language_setting"] ?: "App Language", style = MaterialTheme.typography.labelMedium)
                            }
                            Row(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(8.dp))
                                    .padding(4.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(if (!isUrdu) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
                                        .clickable { onUrduToggle(false) }
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text("English", fontSize = 11.sp, fontWeight = if (!isUrdu) FontWeight.Bold else FontWeight.Normal)
                                }
                                Box(
                                    modifier = Modifier
                                        .background(if (isUrdu) Color.White else Color.Transparent, RoundedCornerShape(6.dp))
                                        .clickable { onUrduToggle(true) }
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text("اردو", fontSize = 11.sp, fontWeight = if (isUrdu) FontWeight.Bold else FontWeight.Normal)
                                }
                            }
                        }

                        Divider()

                        // Dark Mode Toggle
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.DarkMode, contentDescription = "DarkMode", tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text("Dark Mode", style = MaterialTheme.typography.labelMedium)
                            }
                            Switch(
                                checked = useDarkTheme,
                                onCheckedChange = { onThemeToggle(it) },
                                colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
                            )
                        }

                        Divider()

                        // Sign Out Option
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.logout {
                                        Toast.makeText(context, "Signed out successfully!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Logout, contentDescription = "logout", tint = MaterialTheme.colorScheme.error)
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(strings["sign_out"] ?: "Sign Out", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                            }
                            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "arrow", tint = MaterialTheme.colorScheme.error)
                        }
                    }
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("PakLaw AI Version 2.4.0", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Crafted for Legal Literacy and Awareness in Pakistan", fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}

@Composable
fun BentoProfileStat(label: String, value: String, icon: ImageVector, tint: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = tint)
            Spacer(modifier = Modifier.height(10.dp))
            Column {
                Text(label, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(value, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ==================== SECTION 144 DETAILS SCREEN ====================
@Composable
fun Section144DetailScreen(
    viewModel: PakLawViewModel,
    strings: Map<String, String>,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var bookmarked by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.isBookmarked("section_144") { bookmarked = it }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("section_details_screen")
    ) {
        // App top header bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back icon button")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text("Section Details", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }

            Row {
                IconButton(onClick = {
                    viewModel.toggleBookmark("section_144", "Section 144 Detail", "CrPC", "Criminal Procedure")
                    bookmarked = !bookmarked
                    Toast.makeText(context, if (bookmarked) "Added to Bookmarks" else "Removed from Bookmarks", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = if (bookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (bookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { Toast.makeText(context, "Shared Section 144 legal link", Toast.LENGTH_SHORT).show() }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                }
            }
        }

        // Main reading content scroll
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Act Identity
            item {
                Column {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text("Criminal Law", fontSize = 11.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Schedule, contentDescription = "time", modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("5 min read", fontSize = 11.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Section 144", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
                    Text("Code of Criminal Procedure (CrPC)", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            // Progress Bar
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Legal Literacy Progress", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("65%", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        LinearProgressIndicator(
                            progress = 0.65f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        )
                    }
                }
            }

            // Original Statute card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Original Statute", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            IconButton(onClick = { Toast.makeText(context, "Copied law text", Toast.LENGTH_SHORT).show() }, modifier = Modifier.size(24.dp)) {
                                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", modifier = Modifier.size(16.dp))
                            }
                        }
                        Text(
                            text = "\"Power to issue order absolute at once in urgent cases of nuisance or apprehended danger. In cases where, in the opinion of a District Magistrate, there is sufficient ground for proceeding under this section and responsibility demands immediate prevention...\"",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Simple English & Easy Urdu explanation block row
            item {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    // English
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Translate, contentDescription = "Translate", tint = MaterialTheme.colorScheme.tertiary)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Simple English", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
                            }
                            Text("Section 144 allows the local government to temporarily ban the gathering of four or more people in a specific area. It is used to prevent riots, maintain public peace, or during emergencies to ensure safety.", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "check", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Gatherings of 4+ people banned", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "check", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Prevents potential danger or unrest", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }

                    // Urdu translation (Force RTL)
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.HistoryEdu, contentDescription = "write", tint = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("آسان اردو", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                }
                                Text("دفعہ 144 مقامی حکومت کو یہ اختیار دیتی ہے کہ وہ کسی خاص علاقے میں چار یا اس سے زیادہ افراد کے اکٹھے ہونے پر عارضی پابندی لگا دے۔ یہ عام طور پر امن و امان برقرار رکھنے اور ہنگامہ آرائی روکنے کے لیے لگائی جاتی ہے۔", fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                Spacer(modifier = Modifier.height(4.dp))
                                Row {
                                    Box(
                                        modifier = Modifier
                                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                            .padding(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text("حکومتی احکامات", fontSize = 11.sp, color = MaterialTheme.colorScheme.primary)
                                    }
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Box(
                                        modifier = Modifier
                                            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                                            .padding(horizontal = 8.dp, vertical = 2.dp)
                                    ) {
                                        Text("امن و امان", fontSize = 11.sp, color = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Court dome context map
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = IMG_DOME,
                            contentDescription = "Court Dome",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.5f)))
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text("DID YOU KNOW?", color = MaterialTheme.colorScheme.secondaryContainer, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                            Text("Section 144 was first introduced in the late 19th century and remains a core public management tool.", color = Color.White, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        // Floating fixed button at the bottom
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .testTag("btn_take_section_144_quiz_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Button(
                onClick = { viewModel.startNewQuiz("Section 144 Quiz", viewModel.section144Quiz) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Icon(imageVector = Icons.Default.Quiz, contentDescription = "QuizIcon")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Take Section 144 Quiz", fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }
}

// ==================== QUIZ SCREEN ====================
@Composable
fun QuizScreen(
    viewModel: PakLawViewModel,
    quizTitle: String,
    questions: List<QuizQuestion>,
    strings: Map<String, String>,
    onBack: () -> Unit
) {
    val qIndex by viewModel.currentQuestionIndex.collectAsStateWithLifecycle()
    val selectedIndex by viewModel.selectedAnswerIndex.collectAsStateWithLifecycle()
    val checked by viewModel.answerChecked.collectAsStateWithLifecycle()
    val completed by viewModel.quizCompleted.collectAsStateWithLifecycle()
    val scoreXp by viewModel.earnedXp.collectAsStateWithLifecycle()

    val currentQuestion = if (qIndex < questions.size) questions[qIndex] else null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .testTag("quiz_screen_layout"),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Quiz Header row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Exit Quiz")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(quizTitle, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "$scoreXp XP",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        if (completed) {
            // Quiz completed summary screen
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Celebration,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text("Quiz Completed!", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                Text("Great step in enhancing your legal literacy!", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(14.dp))
                Text("You Earned Total: $scoreXp XP!", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onBack,
                    modifier = Modifier.fillMaxWidth(0.7f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Continue Learning", fontWeight = FontWeight.Bold)
                }
            }
        } else if (currentQuestion != null) {
            // Main Question player
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // Progress Bar of questions
                LinearProgressIndicator(
                    progress = (qIndex.toFloat()) / (questions.size.toFloat()),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                )

                Text(
                    text = "Question ${qIndex + 1} of ${questions.size}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = currentQuestion.text,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Options list
                currentQuestion.options.forEachIndexed { optIndex, optText ->
                    val isSelected = selectedIndex == optIndex
                    val isCorrect = currentQuestion.correctIndex == optIndex
                    val buttonColor = when {
                        checked && isCorrect -> Color(0xFFE8F5E9)   // Green tint for correct
                        checked && isSelected && !isCorrect -> Color(0xFFFFEBEE) // Red tint for wrong selected
                        isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                        else -> MaterialTheme.colorScheme.surfaceContainerLowest
                    }
                    val borderOutlineColor = when {
                        checked && isCorrect -> Color(0xFF4CAF50)
                        checked && isSelected && !isCorrect -> Color(0xFFE57373)
                        isSelected -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.25f)
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = { viewModel.selectQuizOption(optIndex) }
                            ).testTag("quiz_option_$optIndex"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = buttonColor),
                        border = BorderStroke(width = 1.5.dp, color = borderOutlineColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(optText, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                            if (checked && isCorrect) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = "Correct", tint = Color(0xFF4CAF50))
                            } else if (checked && isSelected && !isCorrect) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = "Wrong", tint = Color(0xFFF44336))
                            }
                        }
                    }
                }

                if (checked) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text("EXPLANATION", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, fontSize = 11.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(currentQuestion.explanation, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            // Checking actions button (Primary CTA)
            Button(
                onClick = {
                    if (!checked) {
                        viewModel.checkQuizAnswer(currentQuestion)
                    } else {
                        viewModel.nextQuizStep(questions)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("btn_submit_quiz_answer"),
                enabled = selectedIndex != null,
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = if (!checked) "Check Answer" else "Next Question",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
    }
}

@Composable
fun DynamicSectionDetailScreen(
    viewModel: PakLawViewModel,
    details: AppScreen.SectionDetails,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var bookmarked by remember { mutableStateOf(false) }

    LaunchedEffect(details.sectionId) {
        viewModel.isBookmarked(details.sectionId) { bookmarked = it }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag("dynamic_section_details_screen")
    ) {
        // App top header bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back icon button")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text("Legal Statute", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            }

            Row {
                IconButton(onClick = {
                    viewModel.toggleBookmark(details.sectionId, details.title, details.actName, details.category)
                    bookmarked = !bookmarked
                    Toast.makeText(context, if (bookmarked) "Added to Bookmarks" else "Removed from Bookmarks", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = if (bookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (bookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { Toast.makeText(context, "Shared link to ${details.title}", Toast.LENGTH_SHORT).show() }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
                }
            }
        }

        // Main reading content scroll
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Act Identity
            item {
                Column {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f), RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(details.category, fontSize = 11.sp, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
                        }
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Schedule, contentDescription = "time", modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(details.readTime, fontSize = 11.sp)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(details.title, style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.primary)
                    Text(details.actName, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            // Original Statute card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surfaceContainerLow)
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Original Legal Statute Code", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            IconButton(onClick = { Toast.makeText(context, "Copied law text", Toast.LENGTH_SHORT).show() }, modifier = Modifier.size(24.dp)) {
                                Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy", modifier = Modifier.size(16.dp))
                            }
                        }
                        Text(
                            text = details.originalText,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
                        )
                    }
                }
            }

            // Simple English & Easy Urdu explanation block row
            item {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    // English
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Translate, contentDescription = "Translate", tint = MaterialTheme.colorScheme.tertiary)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Simple English Explanation", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.tertiary)
                            }
                            Text(details.simpleEnglish, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }

                    // Urdu translation (Force RTL)
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(imageVector = Icons.Default.HistoryEdu, contentDescription = "write", tint = MaterialTheme.colorScheme.primary)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("آسان اردو قانونی تشریح", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                }
                                Text(details.simpleUrdu, fontSize = 15.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

