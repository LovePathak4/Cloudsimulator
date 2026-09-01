package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.List;

public interface Scheduler {

    void schedule(List<Vm> vmList,
                  List<Cloudlet> cloudletList);

}