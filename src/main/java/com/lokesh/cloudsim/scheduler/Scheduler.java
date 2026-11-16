package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.List;
import java.util.Map;

public interface Scheduler {

    Map<Long, Vm> schedule(
            List<Vm> vmList,
            List<Cloudlet> cloudletList
    );

    String getName();
}