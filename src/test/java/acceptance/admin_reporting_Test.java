package acceptance;
import io.cucumber.java.en.*;
import sweetSys.Listing;
import sweetSys.MyApp;

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
        assertTrue(Listing.generateFinanialReports());
    }

    @Then("identify the best-selling product in each store")
    public void identifyTheBestSellingProductInEachStore() {
        assertTrue(Listing.listingBestSellingProduct());
    }

    @Then("statistics on regular users, gathered by city")
    public void statisticsOnRegularUsersGatheredByCity() {
        assertTrue(Listing.statisticsOnUsersByCity());
    }

}
