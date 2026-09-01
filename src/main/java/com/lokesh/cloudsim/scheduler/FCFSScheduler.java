package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.List;

public class FCFSScheduler implements Scheduler {

    @Override
    public void schedule(List<Vm> vmList,
                         List<Cloudlet> cloudletList) {

        if (vmList.isEmpty()) {
            throw new IllegalArgumentException("No VMs available.");
        }

        Vm firstVm = vmList.get(0);

        System.out.println("\n===== FCFS Scheduling =====");

        for (Cloudlet cloudlet : cloudletList) {

            cloudlet.setVm(firstVm);

            System.out.println(
                    "Cloudlet "
                            + cloudlet.getId()
                            + " assigned to VM "
                            + firstVm.getId()
            );
        }
    }

}