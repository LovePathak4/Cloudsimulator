package com.lokesh.cloudsim.broker;

import org.cloudsimplus.brokers.DatacenterBroker;
import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.core.CloudSimPlus;

public class BrokerManager {

    public DatacenterBroker createBroker(
            CloudSimPlus simulation
    ) {

        DatacenterBroker broker =
                new DatacenterBrokerSimple(simulation);

        return broker;
    }
}