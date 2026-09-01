package com.lokesh.cloudsim.cloudlet;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.utilizationmodels.UtilizationModelDynamic;

import java.util.ArrayList;
import java.util.List;

public class CloudletManager {

    public List<Cloudlet> createCloudlets() {

        List<Cloudlet> cloudletList = new ArrayList<>();

        // Different Cloudlet lengths (Million Instructions)(fcfs, rr)
//        long[] cloudletLengths = {
//                5000,
//                10000,
//                15000,
//                20000,
//                25000,
//                30000,
//                35000,
//                40000,
//                45000,
//                50000
//        };

        //for the SJF Scheduler
        long[] cloudletLengths = {
                30000,
                5000,
                40000,
                10000,
                20000,
                15000,
                50000,
                25000,
                35000,
                8000
        };




        int pes = 2;

        UtilizationModelDynamic utilization =
                new UtilizationModelDynamic(0.5);

        System.out.println("\n========== Creating Cloudlets ==========");

        for (int i = 0; i < cloudletLengths.length; i++) {

            Cloudlet cloudlet = new CloudletSimple(
                    cloudletLengths[i],
                    pes,
                    utilization
            );

            cloudlet.setSizes(1024);

            cloudletList.add(cloudlet);

            System.out.printf(
                    "Cloudlet %2d  Length = %6d MI%n",
                    i,
                    cloudletLengths[i]
            );
        }

        return cloudletList;
    }
}