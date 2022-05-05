package com.company.followingAccessories;

import java.util.Collections;
import java.util.List;

public class Layout {
    public static List<String> methodToDisconnect(String followingStreamers, List<String> listOfFollowingStreamers) {
        listOfFollowingStreamers.clear();
        if (!followingStreamers.isEmpty()) {
            String[] streamers = followingStreamers.split(";");
            Collections.addAll(listOfFollowingStreamers, streamers);
        }

        return listOfFollowingStreamers;
    }

    public static String methodToConnect(List<String> listOfFollowingStreamers) {
        String followingStreamers = "";

        for (String streamer : listOfFollowingStreamers) {
            if (!followingStreamers.isEmpty()) {
                followingStreamers = followingStreamers + ";" + streamer;
            } else {
                followingStreamers = streamer;
            }
        }

        return followingStreamers;
    }
}
