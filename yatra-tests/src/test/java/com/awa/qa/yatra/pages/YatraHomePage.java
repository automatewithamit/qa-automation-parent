package com.awa.qa.yatra.pages;

import com.awa.qa.framework.base.BasePage;
import com.awa.qa.framework.utils.Locator;

public class YatraHomePage extends BasePage {

    private final Locator fromCityInput        = Locator.byXpath("//div[contains(@aria-label,'Departure From') and @role = 'Combobox']");
    private final Locator toCityInput          = Locator.byXpath("//div[contains(@aria-label,'Going To') and @role = 'Combobox']");
    private final Locator departureDateInput   = Locator.byId("BE_flight_origin_date");
    private final Locator searchFlightsButton  = Locator.byXpath("//button[normalize-space(.)='Search']");
    private final Locator closeLoginPopupButton = Locator.byXpath("(//span[contains(@class,'cross')]/img[@alt='cross'])[1]");

    public YatraHomePage() {
        super();
    }

    public YatraHomePage enterFromCity(String from) {
        //click(fromCityInput);
        type(fromCityInput, from);
        return this;
    }
    public YatraHomePage closeLoginPopup() {
        click(closeLoginPopupButton);
        return this;
    }

    public YatraHomePage enterToCity(String to) {
        //click(toCityInput);
        type(toCityInput, to);
        return this;
    }

    public YatraHomePage selectDepartureDate(String dateText) {
        click(departureDateInput);
        // TODO: implement real date picker later
        return this;
    }

    public YatraResultsPage clickSearch() {
        click(searchFlightsButton);
        return new YatraResultsPage();
    }
}
