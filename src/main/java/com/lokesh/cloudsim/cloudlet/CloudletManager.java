package com.lokesh.cloudsim.cloudlet;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;

import java.util.ArrayList;
import java.util.List;

public class CloudletManager {

    public List<Cloudlet> createCloudlets() {

        List<Cloudlet> cloudletList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            Cloudlet cloudlet =
                    new CloudletSimple(10000, 1);

            cloudletList.add(cloudlet);

        }

        return cloudletList;
    }
}