package hexacta.pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import hexacta.controls.Button;
import hexacta.controls.Link;

public class ComplicatedPage extends BasePageObject {

    private By titleOfSectionButtons;
    private By titleOfSectionSocialMedia;
    private By titleOfSectionRandomStuff;
    private By nameFields;
    private By emailFields;
    private By msgFields;
    private By captchaFields;
    private By captchaValues;
    private By submitButtons;
    private By succesMessageText;
    private By toggle;
    private By textInsideToggle;

    private By socialMediaElements;

    public ComplicatedPage() {
        super(By.id("page-container"));

        titleOfSectionButtons = By.id("Section_of_Buttons");
        titleOfSectionSocialMedia = By.id("Section_of_Social_Media_Follows");
        titleOfSectionRandomStuff = By.id("Section_of_Random_Stuff");
        nameFields = By.cssSelector("input.input[data-original_id=name]");
        emailFields = By.cssSelector("input.input[data-original_id=email]");
        msgFields = By.cssSelector("textarea.input[data-original_id=message]");
        captchaFields = By.cssSelector("input.et_pb_contact_captcha");
        submitButtons = By.cssSelector("button.et_pb_contact_submit");
        captchaValues = By.cssSelector("span.et_pb_contact_captcha_question");
        succesMessageText = By.cssSelector("div.et-pb-contact-message");
        toggle = By.id("A_toggle");
        textInsideToggle = By.cssSelector("div.et_pb_toggle_content");
        
        socialMediaElements=By.cssSelector("a.icon.et_pb_with_border");
    }

    public String getHeaderOfSectionButtons() {
        try {
            return new Link(titleOfSectionButtons).getText();

        } catch (Exception e) {
            return null;
        }
    }

    public String getHeaderOfSectionSocialMedia() {
        try {
            return new Link(titleOfSectionSocialMedia).getText();

        } catch (Exception e) {
            return null;
        }
    }

    public String getHeaderOfSectionRandomStuff() {
        try {
            return new Link(titleOfSectionRandomStuff).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Link> getButtons() {
        String css = "a.et_pb_button.et_pb_button";
        By locator = By.cssSelector(css);
        int count = this.driver.findElements(locator).size();
        List<Link> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String expression = css + "_" + i;
            result.add(new Link(By.cssSelector(expression)));

        }
        return result;

    }

    public List<WebElement> getCaptchaFields() {
        List<WebElement> elements = driver.findElements(captchaFields);

        return elements;
    }

    public List<WebElement> getCaptchaValues() {
        List<WebElement> elements = driver.findElements(captchaValues);

        return elements;
    }

    public List<WebElement> getNameFields() {
        List<WebElement> elements = driver.findElements(nameFields);

        return elements;
    }

    public List<WebElement> getEmailFields() {
        List<WebElement> elements = driver.findElements(emailFields);

        return elements;
    }

    public List<WebElement> getMessageFields() {
        List<WebElement> elements = driver.findElements(msgFields);

        return elements;
    }

    public List<WebElement> getSubmitButtons() {
        List<WebElement> elements = driver.findElements(submitButtons);

        return elements;
    }

    public List<WebElement> getSuccesMessages() {
        List<WebElement> elements = driver.findElements(succesMessageText);

        return elements;
    }

    public List<WebElement> getSocialMediaElements() {
        List<WebElement> elements = driver.findElements(socialMediaElements);

        return elements;
    }
/*
    public List<Link> getSocialMediaElements() {

        String css = "a.icon.et_pb_with_border";
        By locator = By.cssSelector(css);
        int count = this.driver.findElements(locator).size();
        List<Link> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            result.add(new Link(By.cssSelector(css)));

        }
        return result;

    }
*/


    public void setDataInContactFormByIndex(int index, String name, String email, String message)
            throws InterruptedException {
        this.getNameFields().get(index).sendKeys(name);
        this.getEmailFields().get(index).sendKeys(email);
        this.getMessageFields().get(index).sendKeys(message);
        this.getCaptchaFields().get(index).sendKeys(this.getCaptchaValueSelectingFormByIndex(index));

        this.getSubmitButtons().get(index).click();

    }

    public String getCaptchaValueSelectingFormByIndex(int index) {
        String captchaString = getCaptchaValues().get(index).getText().replaceAll("[\\$ .]", "");

        String[] captchaSplit = captchaString.split("\\+");

        int number1 = Integer.parseInt(captchaSplit[0]);
        int number2 = Integer.parseInt(captchaSplit[1]);

        int captchaNumber = number1 + number2;
        String captcha = String.valueOf(captchaNumber);

        return captcha;

    }

    public String getExpectedMessageByIndexOfForm(int index) {
        String message = this.getSuccesMessages().get(index).getText();

        return message;

    }

    public boolean clickToggleIfDisplayed() {
        boolean result = false;

        if (new Button(toggle).isDisplayed()) {

            new Button(toggle).click();
            if (new Link(textInsideToggle).isDisplayed()) {
                result = true;

            }
        }
        return result;

    }

    public String getTextInsideToggle() {

        try {

            return new Link(textInsideToggle).getText();

        } catch (Exception e) {

            return null;
        }
    }

    public void clickToggleButton() {

        waitUntil(() -> this.clickToggleIfDisplayed());

    }

}
