package com.example.compose.rally

import android.os.Build
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import androidx.test.filters.SdkSuppress
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest() {
        val allScreens = RallyScreen.entries //mock the screens
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = RallyScreen.Accounts
                )
            }
        }

        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.entries //mock the screens
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = {},
                    currentScreen = RallyScreen.Accounts
                )
            }
        }
        // When printing the semantics tree, the property MergeDescendants = 'true'
        // is telling us that this node had descendants, but they have been merged into it.
        // In tests we oftentimes need to access all nodes.
        // In order to verify whether the Text inside the tab is displayed or not,
        // we can query the unmerged Semantics tree passing useUnmergedTree = true to the onRoot finder.
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        // Here we look for a node that has text = OVERVIEW and
        // has a parent node with ContentDescription = Overview (as seen in the semantics tree)
        // We don't necessarily need to add the hasParent matcher, but this way we avoid having a
        // broader match (eg with another node including the same text)
        composeTestRule.onNode(
            hasText(RallyScreen.Accounts.name.uppercase()) and
                    hasParent(hasContentDescription(RallyScreen.Accounts.name))
            , useUnmergedTree = true
        )
            .assertExists()
    }
}