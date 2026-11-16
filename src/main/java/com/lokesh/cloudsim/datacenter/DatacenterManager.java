package com.lokesh.cloudsim.datacenter;

import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.Datacenter;
import org.cloudsimplus.datacenters.DatacenterSimple;
import org.cloudsimplus.hosts.Host;

import java.util.List;

public class DatacenterManager {

    public Datacenter createDatacenter(
            CloudSimPlus simulation,
            List<Host> hostList
    ) {

        if (simulation == null) {
            throw new IllegalArgumentException(
                    "Simulation instance cannot be null."
            );
        }

        if (hostList == null || hostList.isEmpty()) {
            throw new IllegalArgumentException(
                    "Host list cannot be null or empty."
            );
        }

        DatacenterSimple datacenter =
                new DatacenterSimple(simulation, hostList);

        /*
         * Controls how frequently the Datacenter
         * checks and updates running Cloudlets.
         *
         * Smaller value = more frequent scheduling.
         */
        datacenter.setSchedulingInterval(1.0);

        return datacenter;
    }
}