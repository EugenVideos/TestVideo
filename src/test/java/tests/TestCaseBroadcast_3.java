package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestCaseBroadcast_3 extends TestBase {

    @Before
    public void PreCondition(){
        init();
    }

    @Test

    public void testChannelCreation(){
        new LoginMainPage(driver).doLogin(new TestBot("+79111026260", "2206851qwe"));
        new MainPage(driver).clickVideoOnToolbar();
        VideoPage videoPage = new VideoPage(driver);
        videoPage.ButtonMyVideo();
        Assert.assertTrue("Не открывается сабпанель видео", isElementPresent(VideoPage.SUBPANEL_VIDEO));
        videoPage.buttonPreCreateChannalMenuVideo();
        Assert.assertTrue("Окно создание канала не появилось", isElementPresent(VideoPage.WINDOW_CREAT_CHANNAL));
        videoPage.nameChannal();
        videoPage.buttonCreateChannal();
        new Verification(driver).checkCreateBroadcast();
    }

    @After
    public void tearDown(){
        driver.findElement(VideoPage.DELETE_CHANNEL).click();
        driver.findElement(VideoPage.DELETE_CHANNEL_ACCEPT).click();
        stop();
    }
}