package com.awa.qa.yatra.base;

import com.awa.qa.framework.base.BaseTest;

/**
 * Application-specific base test for Yatra.
 * Extends the generic BaseTest from the core framework.
 */
public abstract class YatraBaseTest extends BaseTest {

    @Override
    protected String getBaseUrl() {
        // For now hard-coded; later we can move this to config if needed
        return "https://www.yatra.com/";
    }
}
