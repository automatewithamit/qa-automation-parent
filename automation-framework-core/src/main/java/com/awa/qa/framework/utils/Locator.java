package com.awa.qa.framework.utils;

public final class Locator {

    private final LocatorType type;
    private final String value;

    private Locator(LocatorType type, String value) {
        this.type = type;
        this.value = value;
    }

    public LocatorType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    // Factory methods – this is what pages will call

    public static Locator byId(String id) {
        return new Locator(LocatorType.ID, id);
    }

    public static Locator byName(String name) {
        return new Locator(LocatorType.NAME, name);
    }

    public static Locator byCss(String css) {
        return new Locator(LocatorType.CSS, css);
    }

    public static Locator byXpath(String xpath) {
        return new Locator(LocatorType.XPATH, xpath);
    }

    public static Locator byClassName(String className) {
        return new Locator(LocatorType.CLASS_NAME, className);
    }

    public static Locator byTagName(String tagName) {
        return new Locator(LocatorType.TAG_NAME, tagName);
    }

    public static Locator byLinkText(String text) {
        return new Locator(LocatorType.LINK_TEXT, text);
    }

    public static Locator byPartialLinkText(String text) {
        return new Locator(LocatorType.PARTIAL_LINK_TEXT, text);
    }
}
