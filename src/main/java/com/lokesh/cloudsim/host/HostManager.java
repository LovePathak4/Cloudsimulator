package com.lokesh.cloudsim.host;

import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.Pe;
import org.cloudsimplus.resources.PeSimple;

import java.util.ArrayList;
import java.util.List;

public class HostManager {

    public Host createHost() {

        List<Pe> peList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            peList.add(new PeSimple(1000));
        }

        Host host = new HostSimple(
                8192,
                10000,
                1000000,
                peList
        );

        return host;
    }
}