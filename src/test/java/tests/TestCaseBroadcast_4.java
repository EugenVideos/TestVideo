package tests;

import core.*;
import model.TestBot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestCaseBroadcast_4 extends TestBase {

    public static String id1;
    public static String id2;

    @Before
    public void PreCondition(){
        init();
        new LoginMainPage(driver).doLogin(new TestBot("+79111026260", "2206851qwe"));
        new MainPage(driver).clickVideoOnToolbar();
        VideoPage videoPage = new VideoPage(driver);
        videoPage.clickBroadcastButton();
        videoPage.settingBroadcast();
        videoPage.startPreviewBroadcast();
        BroadcastPage broadcastPage = new BroadcastPage(driver);
        broadcastPage.StartBroadcast();
        broadcastPage.StopBroadcast();
        Promise promise = new BroadcastPage(driver).restartBroadcast().Error();
        broadcastPage.closeBroadcastWindow();
        new Verification(driver).CheckBroadcastPresence();
        videoPage.buttonPreCreateChannalMenuVideo();
        videoPage.nameChannal();
        videoPage.buttonCreateChannal();
        new MainPage(driver).QuitMT();
    }

    @Test

    public void testAddVideoInChannel(){
        new LoginMainPage(driver).doLogin(new TestBot("+79111026260", "2206851qwe"));
        new MainPage(driver).clickVideoOnToolbar();
        VideoPage videoPage = new VideoPage(driver);
        videoPage.ButtonMyVideo();
        Assert.assertTrue("Не открывается сабпанель видео", isElementPresent(VideoPage.SUBPANEL_VIDEO));
        videoPage.ButtonMyLives();
        Assert.assertTrue("Не открылось окно трансляций", isElementPresent(VideoPage.BROADCAST_LAYER));
        videoPage.editBroadcast();
        Assert.assertTrue("Не появляется меню редактирования", isElementPresent(VideoPage.MENU_SETTING_BROADCAST));
        videoPage.chooseYourChannal();
        videoPage.saveSettingBroadcast();
        id1 = driver.findElement(Verification.GET_ID_2).getAttribute("data-id");
        videoPage.buttonChannal();
        id2 = driver.findElement(By.xpath(("//*[@class = 'js-loader-container clearfix ui-sortable']/descendant::div[1]"))).getAttribute("data-id");
        new Verification(driver).CheckAddRecordToChannel();
    }

    @After
    public void tearDown(){
        VideoPage videoPage = new VideoPage(driver);
        driver.findElement(VideoPage.DELETE_CHANNEL).click();
        driver.findElement(VideoPage.DELETE_CHANNEL_ACCEPT).click();
        videoPage.ButtonMyLives();
        videoPage.navigateToBroadcast();
        driver.findElement(VideoPage.DELETE_RECORD).click();
        driver.findElement(VideoPage.DELETE_RECORD_ACCEPT).click();
        stop();
    }
}
