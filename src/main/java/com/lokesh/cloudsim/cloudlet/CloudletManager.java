package com.lokesh.cloudsim.cloudlet;

import com.lokesh.cloudsim.config.SimulationConfig;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.utilizationmodels.UtilizationModelDynamic;

import java.util.ArrayList;
import java.util.List;

public class CloudletManager {

    public List<Cloudlet> createCloudlets() {

        List<Cloudlet> cloudlets =
                new ArrayList<>();

        for (int i = 0;
             i < SimulationConfig.CLOUDLET_LENGTHS.length;
             i++) {

            long length =
                    SimulationConfig.CLOUDLET_LENGTHS[i];

            Cloudlet cloudlet =
                    new CloudletSimple(
                            length,
                            SimulationConfig.CLOUDLET_PES
                    );

            // Assign a unique ID before scheduling.
            cloudlet.setId(i);
            cloudlet.setUtilizationModelCpu(new UtilizationModelDynamic(0.5));

            cloudlet.setSizes(1024);

            cloudlets.add(cloudlet);
        }
        return cloudlets;
    }
}