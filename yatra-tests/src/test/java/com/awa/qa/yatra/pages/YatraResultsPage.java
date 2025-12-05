package com.awa.qa.yatra.pages;

import com.awa.qa.framework.base.BasePage;
import com.awa.qa.framework.utils.Locator;

public class YatraResultsPage extends BasePage {

    // You can refine this locator later based on actual DOM.
    private final Locator resultsContainer = Locator.byCss("div.result-wrapp"); // placeholder

    public YatraResultsPage() {
        super();
    }

    public boolean isOnResultsPage() {
        // Option 1: via title (simple & robust enough for demo)
        String title = getPageTitle().toLowerCase();
        return title.contains("flight") || title.contains("yatra");

        // Option 2 (later): use presence of a results element:
        // return isVisible(resultsContainer);
    }
}
