package com.awa.qa.yatra.tests;

import com.awa.qa.yatra.base.YatraBaseTest;
import com.awa.qa.yatra.pages.YatraHomePage;
import com.awa.qa.yatra.pages.YatraResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YatraSearchTest extends YatraBaseTest {

    @Test(groups = {"smoke"})
    public void searchOneWayFlight_ShouldOpenResultsPage() {

        YatraHomePage homePage = new YatraHomePage();

        YatraResultsPage resultsPage = homePage
                .enterFromCity("Mumbai")
                .enterToCity("Delhi")
                .selectDepartureDate("25-12-2025")
                .clickSearch();

        Assert.assertTrue(
                resultsPage.isOnResultsPage(),
                "Expected to be on Yatra flight results page."
        );
    }
}
