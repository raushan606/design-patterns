package com.raushan.proxy;


import com.raushan.proxy.service.ThirdPartyYoutubeLib;

// The GUI class, which used to work directly with a service
// object, stays unchanged as long as it works with the service
// object through an interface. We can safely pass a proxy
// object instead of a real service object since they both
// implement the same interface.
public class YoutubeManager {
    protected ThirdPartyYoutubeLib youtubeLib;

    public YoutubeManager(ThirdPartyYoutubeLib youtubeLib) {
        this.youtubeLib = youtubeLib;
    }

    public void renderVideoPage(String id) {
        var info = youtubeLib.getVideoInfo(id);
    }

    public void renderListPanel() {
        var list = youtubeLib.listVideos();
    }

    public void reactOnUserInput(String id) {
        renderVideoPage(id);
        renderListPanel();
    }
}
