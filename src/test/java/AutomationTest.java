import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import hexacta.pageObjects.ComplicatedPage;
import hexacta.pageObjects.Home;

public class AutomationTest extends BaseTest {

    @Test
    public void EachLinkNavigatesToItsPage() {
        Home home = new Home();
        int linksCount = home.getLinks().size();

        for (int i = 0; i < linksCount; i++) {
            home.getLinks().get(i).click();
            assertEquals(driver.getTitle(), this.GetExpectedHomeTitlesList().get(i));
            this.goBack();

        }
    }

    private List<String> GetExpectedHomeTitlesList() {

        List<String> titles = new ArrayList<>();
        titles.add("Complicated Page | Ultimate QA");
        titles.add("Fake landing page | Ultimate QA");
        titles.add("Fake pricing page | Ultimate QA");
        titles.add("Filling Out Forms | Ultimate QA");
        titles.add("Sample Application Lifecycle - Sprint 1 | Ultimate QA");
        titles.add("Ultimate QA");
        titles.add("Simple HTML Elements For Automation | Ultimate QA");

        return titles;
    }

    @Test
    public void SectionHeadersAreCorrect() {
        Home home = new Home();
        ComplicatedPage complicatedPage = new ComplicatedPage();
        home.navigateByText("Big page with many elements");
        assertEquals("Section of Buttons", complicatedPage.getHeaderOfSectionButtons());
        assertEquals("Section of Social Media Follows", complicatedPage.getHeaderOfSectionSocialMedia());
        assertEquals("Section of Random Stuff", complicatedPage.getHeaderOfSectionRandomStuff());

    }

    @Test
    public void GetButtonsAndPerformClick() throws InterruptedException {
        Home home = new Home();
        ComplicatedPage complicatedPage = new ComplicatedPage();

        home.navigateByText("Big page with many elements");
        int buttonsCount = complicatedPage.getButtons().size();
        for (int i = 0; i < buttonsCount; i++) {
            complicatedPage.getButtons().get(i).click();

        }

    }

    @Test
    public void TextInsideToggleIsCorrect() {
        Home home = new Home();
        ComplicatedPage complicatedPage = new ComplicatedPage();
        home.navigateByText("Big page with many elements");

        complicatedPage.clickToggleButton();

        assertEquals("Inside of toggle", complicatedPage.getTextInsideToggle());

    }

    @Test
    public void NavigateToEachSocialMediaLink() throws InterruptedException {
        Home home = new Home();
        ComplicatedPage complicatedPage = new ComplicatedPage();

        home.navigateByText("Big page with many elements");

        int linksCount = complicatedPage.getSocialMediaElements().size();

        for (int i = 0; i < linksCount; i++) {
            complicatedPage.getSocialMediaElements().get(i).click();
            this.goBack();

        }
    }

    @Test
    public void CompletingForm() throws InterruptedException {
        Home home = new Home();
        ComplicatedPage complicatedPage = new ComplicatedPage();

        home.navigateByText("Big page with many elements");

        complicatedPage.setDataInContactFormByIndex(0, "Pedro Test", "pedro@test.com",
                "Hola, este es un mensaje de prueba.");

        String message = complicatedPage.getExpectedMessageByIndexOfForm(0);

        assertEquals("Thanks for contacting us", message);

    }

}
