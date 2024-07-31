package acceptance;
import io.cucumber.java.en.*;
import sweetSys.MyApp;

public class admin_reporting {
    static MyApp myApp;

    public admin_reporting(MyApp myApp) {
        super();
        this.myApp = myApp;
    }


    @When("choose monitoring and reporting from the list")
    public void chooseMonitoringAndReportingFromTheList() {

    }

    @Then("generate financial reports for both owners and suppliers")
    public void generateFinancialReportsForBothOwnersAndSuppliers() {

    }

    @Then("identify the best-selling product in each store")
    public void identifyTheBestSellingProductInEachStore() {

    }

    @Then("statistics on regular users, gathered by city")
    public void statisticsOnRegularUsersGatheredByCity() {

    }

}
