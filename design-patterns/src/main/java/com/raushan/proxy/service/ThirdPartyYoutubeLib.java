package com.raushan.proxy.service;

import java.util.List;
import java.util.Set;

// This interface is implemented by both Proxy and Original Class
public interface ThirdPartyYoutubeLib {

    List<String> listVideos();
    Set<String> getVideoInfo(String videoId);
    void downloadVideo(String videoId);
}
