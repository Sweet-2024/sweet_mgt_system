package acceptance;
import io.cucumber.java.en.*;
import sweet_system.Listing;
import sweet_system.MyApp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class admin_reporting_Test {
    static MyApp myApp;

    public admin_reporting_Test(MyApp myApp) {
        super();
        this.myApp = myApp;
    }


    @When("choose monitoring and reporting from the list")
    public void chooseMonitoringAndReportingFromTheList() {

    }

    @Then("generate financial reports for both owners and suppliers")
    public void generateFinancialReportsForBothOwnersAndSuppliers() {
        MyApp.userType = 1;
        assertTrue(Listing.generateFinancialReports());

        MyApp.userType = 7;
        assertFalse(Listing.generateFinancialReports());
    }

    @Then("identify the best-selling product in each store")
    public void identifyTheBestSellingProductInEachStore() {
        MyApp.userType = 1;
        assertTrue(Listing.listingBestSellingProduct());
    }

    @Then("statistics on regular users, gathered by city")
    public void statisticsOnRegularUsersGatheredByCity() {
        assertTrue(Listing.statisticsOnUsersByCity());
    }

}
