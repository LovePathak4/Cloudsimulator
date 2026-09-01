package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.List;

public class RoundRobinScheduler implements Scheduler {

    @Override
    public void schedule(List<Vm> vmList, List<Cloudlet> cloudletList) {

        if (vmList.isEmpty()) {
            throw new IllegalArgumentException("No VMs available.");
        }

        System.out.println("\n===== Round Robin Scheduling =====");

        for (int i = 0; i < cloudletList.size(); i++) {

            Cloudlet cloudlet = cloudletList.get(i);

            Vm vm = vmList.get(i % vmList.size());

            cloudlet.setVm(vm);

            System.out.println(
                    "Task " + (i + 1) +
                            " assigned to VM " + (i % vmList.size())
            );
        }
    }
}