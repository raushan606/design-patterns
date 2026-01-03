package com.raushan.proxy.service;

import java.util.List;
import java.util.Set;

// The concrete implementation of a service connector. Methods
// of this class can request information from YouTube. The speed
// of the request depends on a user's internet connection as
// well as YouTube's. The application will slow down if a lot of
// requests are fired at the same time, even if they all request
// the same information.
public class ThirdPartyYoutube implements ThirdPartyYoutubeLib{
    @Override
    public List<String> listVideos() {
        return List.of();
    }

    @Override
    public Set<String> getVideoInfo(String videoId) {
        return null;
    }

    @Override
    public void downloadVideo(String videoId) {
        System.out.println("Downloading video:");
    }
}
