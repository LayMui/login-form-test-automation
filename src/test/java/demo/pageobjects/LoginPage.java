package demo.pageobjects;


import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

public class LoginPage extends PageObject {
    public static final Target EMAIL = Target.the("email").
            locatedBy("css:input[data-qa='text']");

    public static final Target PASSWORD = Target.the("password").
            locatedBy("css:input[data-qa='password']");
  
    public static final Target LOGIN = Target.the("login button").
            locatedBy("css:button[data-qa='submit']");
}

