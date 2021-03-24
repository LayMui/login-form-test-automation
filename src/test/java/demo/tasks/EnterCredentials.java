package demo.tasks;

import demo.pageobjects.LoginPage;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.matchers.statematchers.IsVisibleMatcher;
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class EnterCredentials {

    
    public static Performable forUser(String username, String password) {
        return Task.where(
                "{0} attempts to enter #username and #password",
               
                Clear.field(LoginPage.EMAIL),
                Enter.theValue(username).into(LoginPage.EMAIL),
                Clear.field(LoginPage.PASSWORD),
                Enter.theValue(password).into(LoginPage.PASSWORD),
                Click.on(LoginPage.LOGIN))
            .with("username")
            .of(username)
            .with("password")
            .of(password);
      }

}
