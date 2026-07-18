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

        Datacenter datacenter =
                new DatacenterSimple(simulation, hostList);

        return datacenter;
    }
}