package com.raushan.proxy;

import com.raushan.proxy.service.CachedYoutube;
import com.raushan.proxy.service.ThirdPartyYoutube;
import com.raushan.proxy.service.ThirdPartyYoutubeLib;

public class Application {
    public static void main(String[] args) {
        ThirdPartyYoutubeLib service = new ThirdPartyYoutube();
        ThirdPartyYoutubeLib proxyService = new CachedYoutube(service);
        var manager = new YoutubeManager(proxyService);
        manager.reactOnUserInput("Tarak Mehta Ka Ooltah Chashmah");
    }
}
