package com.lokesh.cloudsim.broker;

import org.cloudsimplus.brokers.DatacenterBroker;
import org.cloudsimplus.brokers.DatacenterBrokerSimple;
import org.cloudsimplus.core.CloudSimPlus;

public class BrokerManager {

    public DatacenterBroker createBroker(
            CloudSimPlus simulation
    ) {
        if (simulation == null) {
            throw new IllegalArgumentException(
                    "Simulation instance cannot be null."
            );
        }

        
        DatacenterBrokerSimple broker =
                new DatacenterBrokerSimple(simulation);

        broker.setVmDestructionDelay(1000.0);

        return broker;
    }
}
