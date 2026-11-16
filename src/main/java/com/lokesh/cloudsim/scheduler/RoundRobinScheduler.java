package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundRobinScheduler implements Scheduler {

    @Override
    public Map<Long, Vm> schedule(
            List<Vm> vmList,
            List<Cloudlet> cloudletList) {

        validate(vmList, cloudletList);

        Map<Long, Vm> assignments = new HashMap<>();

        for (int i = 0; i < cloudletList.size(); i++) {

            Cloudlet cloudlet = cloudletList.get(i);

            Vm vm = vmList.get(
                    i % vmList.size()
            );

            assignments.put(
                    cloudlet.getId(),
                    vm
            );

            cloudlet.setVm(vm);
        }

        return assignments;
    }

    @Override
    public String getName() {
        return "Round Robin";
    }

    private void validate(
            List<Vm> vmList,
            List<Cloudlet> cloudletList) {

        if (vmList == null || vmList.isEmpty()) {
            throw new IllegalArgumentException(
                    "No VMs available."
            );
        }

        if (cloudletList == null || cloudletList.isEmpty()) {
            throw new IllegalArgumentException(
                    "No Cloudlets available."
            );
        }
    }
}