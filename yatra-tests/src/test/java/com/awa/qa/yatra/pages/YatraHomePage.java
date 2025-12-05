package com.awa.qa.yatra.pages;

import com.awa.qa.framework.base.BasePage;
import com.awa.qa.framework.utils.Locator;

public class YatraHomePage extends BasePage {

    private final Locator fromCityInput        = Locator.byId("BE_flight_origin_city");
    private final Locator toCityInput          = Locator.byId("BE_flight_arrival_city");
    private final Locator departureDateInput   = Locator.byId("BE_flight_origin_date");
    private final Locator searchFlightsButton  = Locator.byId("BE_flight_flsearch_btn");

    public YatraHomePage() {
        super();
    }

    public YatraHomePage enterFromCity(String from) {
        type(fromCityInput, from);
        return this;
    }

    public YatraHomePage enterToCity(String to) {
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
