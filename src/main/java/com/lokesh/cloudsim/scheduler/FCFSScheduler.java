package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FCFSScheduler implements Scheduler {

    @Override
    public Map<Long, Vm> schedule(
            List<Vm> vmList,
            List<Cloudlet> cloudletList) {

        validate(vmList, cloudletList);

        Map<Long, Vm> assignments = new HashMap<>();
        Map<Vm, Double> estimatedLoad = initializeLoad(vmList);

        for (Cloudlet cloudlet : cloudletList) {

            Vm selectedVm = vmList.stream()
                    .min((a, b) -> Double.compare(
                            estimatedLoad.get(a),
                            estimatedLoad.get(b)
                    ))
                    .orElseThrow();

            assignments.put(
                    cloudlet.getId(),
                    selectedVm
            );

            cloudlet.setVm(selectedVm);

            estimatedLoad.put(
                    selectedVm,
                    estimatedLoad.get(selectedVm)
                            + (double) cloudlet.getLength()
                            / selectedVm.getMips()
            );
        }

        return assignments;
    }

    @Override
    public String getName() {
        return "FCFS";
    }

    private Map<Vm, Double> initializeLoad(List<Vm> vmList) {

        Map<Vm, Double> load = new HashMap<>();

        vmList.forEach(vm -> load.put(vm, 0.0));

        return load;
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