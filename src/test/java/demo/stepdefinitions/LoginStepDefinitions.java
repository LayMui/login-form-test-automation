package demo.stepdefinitions;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.serenitybdd.core.Serenity;
import demo.tasks.EnterCredentials;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import demo.pageobjects.LoginPage;

import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import net.thucydides.core.annotations.Steps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinitions {

    private final Logger log = LoggerFactory.getLogger(LoginStepDefinitions.class);

/*
**   Notice how we did not need to instantiate the Steps class NavigationPage
*    When you annotated a member variable of this class with the @Steps annotation,
*    Serenity BDD will automatically instantiate it for you.
*/
    @Steps
    LoginPage loginPage;


    VisualGridRunner runner;
    Eyes eyes;

    private static void setUp(Eyes eyes) {
        // Initialize the eyes configuration.
        Configuration config = new Configuration();
        // Add this configuration if your tested page includes fixed elements.
        //config.setStitchMode(StitchMode.CSS);
        // You can get your api key from the Applitools dashboard
        // https://applitools.com/docs/api/eyes-sdk/classes-gen/class_eyes/method-eyes-setapikey-selenium-java.html
        // set new batch
        config.setBatch(new BatchInfo("Login batch"));
       // // Add browsers with different viewports
       
        config.addBrowser(800, 600, BrowserType.CHROME);
        // config.addBrowser(700, 500, BrowserType.FIREFOX);
        config.addBrowser(800, 600, BrowserType.IE_11);
        // config.addBrowser(1024, 768, BrowserType.EDGE_CHROMIUM);
        // config.addBrowser(800, 600, BrowserType.SAFARI);
        // // Add mobile emulation devices in Portrait mode
        // config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
        // config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
        
        // // set the configuration to eyes
        eyes.setConfiguration(config);
      }

    @Before()
    public void setTheStage() {

    if (System.getenv("APPLITOOLS_API_KEY") != null) {
        runner = new VisualGridRunner(1);
        // Initialize the eyes SDK
        eyes = new Eyes(runner);
        setUp(eyes);
        //eyes.setLogHandler(new StdoutLogHandler(true));
        eyes.setLogHandler(new FileLogger("file.log", true, true));
   }

        OnStage.setTheStage(new OnlineCast());
    }

    @After()
    public void drawTheCurtain() {
        if (System.getenv("APPLITOOLS_API_KEY") != null) {
            eyes.closeAsync();
            // Get test results from Eyes
            TestResultsSummary myTestResults = runner.getAllTestResults(false);
            // Push test results into Serenity report
             Serenity.recordReportData().withTitle("Applitools Report").andContents(myTestResults.toString());
        }
        OnStage.drawTheCurtain();
    }


    @Given("^(?:.*) is at the login form of sunflower field$") 
    public void maryIsAtTheLoginFormOfSunflowerField() {
        theActorCalled("mary").attemptsTo(Open.browserOn().the(loginPage));
        if (System.getenv("APPLITOOLS_API_KEY") != null) {
            eyes.open(((WebDriverFacade) loginPage.getDriver()).getProxiedDriver(),
             "Login", "Login Ultra Grid");
            eyes.check(Target.window().fully().withName("Login Page"));
        }
    }
    
    @When("^(?:.*) login with email \"([^\"]*)\" and \"([^\"]*)\"")
    public void maryLoginWithEmailAnd(String username, String password) {
        
        theActorInTheSpotlight().attemptsTo(EnterCredentials.forUser(username, password));
        
    }
    
    
    @Then("she is able to see the sunflower farm")
    public void sheIsAbleToSeeTheSunflowerFarm() {
    if (System.getenv("APPLITOOLS_API_KEY") != null)
            eyes.check(Target.window().fully().withName("Dashboard"));

// Example: theActorInTheSpotlight().attemptsTo(Check.whether(displayText(DashboardPage.TEXT_HELLO), Matchers.containsString("Hello")))
 
    }
}
