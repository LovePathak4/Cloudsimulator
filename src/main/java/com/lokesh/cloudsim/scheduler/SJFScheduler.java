package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJFScheduler implements Scheduler {

    @Override
    public void schedule(
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

        System.out.println("\n===== SJF Scheduling =====");

        /*
         * Create a copy so that we don't change
         * the original Cloudlet list.
         */
        List<Cloudlet> sortedCloudlets =
                new ArrayList<>(cloudletList);

        /*
         * Sort Cloudlets according to their
         * length in ascending order.
         *
         * Shortest Cloudlet → First
         */
        sortedCloudlets.sort(
                Comparator.comparingLong(
                        Cloudlet::getLength
                )
        );


        /*
         * Assign sorted Cloudlets to VMs
         * using Round-Robin VM selection.
         */
        for (int i = 0; i < sortedCloudlets.size(); i++) {

            Cloudlet cloudlet =
                    sortedCloudlets.get(i);

            Vm vm =
                    vmList.get(i % vmList.size());

            cloudlet.setVm(vm);

            System.out.println(
                    "Cloudlet Length = " +
                            cloudlet.getLength() +
                            " MI" +
                            "  --> VM " +
                            (i % vmList.size())
            );
        }
    }
}