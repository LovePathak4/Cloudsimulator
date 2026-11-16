package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SJFScheduler implements Scheduler {

    @Override
    public Map<Long, Vm> schedule(
            List<Vm> vmList,
            List<Cloudlet> cloudletList) {

        validate(vmList, cloudletList);

        Map<Long, Vm> assignments = new HashMap<>();

        List<Cloudlet> sorted =
                new ArrayList<>(cloudletList);

        sorted.sort(
                Comparator.comparingLong(
                        Cloudlet::getLength
                )
        );

        Map<Vm, Double> estimatedLoad = new HashMap<>();

        vmList.forEach(vm ->
                estimatedLoad.put(vm, 0.0)
        );

        for (Cloudlet cloudlet : sorted) {

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
        return "SJF";
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