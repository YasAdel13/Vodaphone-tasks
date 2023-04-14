package StepDefinition;

import Com.Vodafone.Base.Base;
import io.cucumber.java.After;

import java.io.IOException;

public class Hooks extends Base {

    @After
    public void runsAfterAnyScenario(){
        driver.quit();
    }
}
