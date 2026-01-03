package com.raushan.proxy.service;

import java.util.List;
import java.util.Set;

public class CachedYoutube implements ThirdPartyYoutubeLib {

    private final ThirdPartyYoutubeLib service;
    private Set<String> cachedVideos;
    private List<String> videoIds;
    private boolean needRefresh;

    public CachedYoutube(ThirdPartyYoutubeLib service) {
        this.service = service;
    }


    @Override
    public List<String> listVideos() {
        if (videoIds == null || needRefresh) {
            videoIds = service.listVideos();
        }
        return videoIds;
    }

    @Override
    public Set<String> getVideoInfo(String videoId) {
        if (cachedVideos == null || needRefresh) {
            cachedVideos = (Set<String>) service.getVideoInfo(videoId);
        }
        return cachedVideos;
    }

    @Override
    public void downloadVideo(String videoId) {
        if (!downloadExists(videoId) || needRefresh) {
            service.downloadVideo(videoId);
        }
    }

    private boolean downloadExists(String videoId) {
        return cachedVideos.contains(videoId);
    }
}
