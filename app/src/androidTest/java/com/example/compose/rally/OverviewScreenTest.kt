package com.example.compose.rally

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.overview.OverviewBody
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

class OverviewScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun overviewScreen_AlertsDisplayed() {
        composeTestRule.setContent {
            RallyTheme {
                OverviewBody()
            }
        }
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")
        composeTestRule.onNodeWithText("Alerts").assertIsDisplayed()
    }

}