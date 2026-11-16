package com.lokesh.cloudsim.simulation;

import com.lokesh.cloudsim.broker.BrokerManager;
import com.lokesh.cloudsim.cloudlet.CloudletManager;
import com.lokesh.cloudsim.datacenter.DatacenterManager;
import com.lokesh.cloudsim.host.HostManager;
import com.lokesh.cloudsim.metrics.MetricsManager;
import com.lokesh.cloudsim.metrics.SimulationResult;
import com.lokesh.cloudsim.scheduler.Scheduler;
import com.lokesh.cloudsim.vm.VmManager;

import org.cloudsimplus.brokers.DatacenterBroker;
import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.vms.Vm;

import java.util.List;
import java.util.Map;

public class SimulationManager {

    public SimulationResult run(Scheduler scheduler) {

        CloudSimPlus simulation =
                new CloudSimPlus();

        // ==========================================
        // DATACENTER
        // ==========================================

        List<Host> hosts =
                new HostManager().createHosts();

        new DatacenterManager()
                .createDatacenter(simulation, hosts);


        // ==========================================
        // BROKER
        // ==========================================

        DatacenterBroker broker =
                new BrokerManager()
                        .createBroker(simulation);


        // ==========================================
        // VMs
        // ==========================================

        List<Vm> vms =
                new VmManager().createVms();


        // ==========================================
        // CLOUDLETS
        // ==========================================

        List<Cloudlet> cloudlets =
                new CloudletManager()
                        .createCloudlets();


        // ==========================================
        // SCHEDULER
        // ==========================================

        Map<Long, Vm> assignments =
                scheduler.schedule(
                        vms,
                        cloudlets
                );


        // ==========================================
        // VALIDATE
        // ==========================================

        if (assignments.size() != cloudlets.size()) {

            throw new IllegalStateException(
                    "Scheduler did not assign every Cloudlet. "
                            + "Expected: "
                            + cloudlets.size()
                            + ", Actual: "
                            + assignments.size()
            );
        }


        // ==========================================
        // DISPLAY SCHEDULING
        // ==========================================

        System.out.println(
                "\n--- "
                        + scheduler.getName()
                        + " Scheduling ---"
        );

        for (Cloudlet cloudlet : cloudlets) {

            Vm vm =
                    assignments.get(
                            cloudlet.getId()
                    );

            System.out.printf(
                    "Cloudlet %d -> VM %d%n",
                    cloudlet.getId(),
                    vm.getId()
            );
        }


        // ==========================================
        // BROKER MAPPER
        // ==========================================

        broker.setVmMapper(
                cloudlet -> assignments.getOrDefault(
                        cloudlet.getId(),
                        Vm.NULL
                )
        );


        // ==========================================
        // SUBMIT VMs
        // ==========================================

        broker.submitVmList(vms);


        // ==========================================
        // SUBMIT CLOUDLETS
        // ==========================================

        broker.submitCloudletList(cloudlets);


        // ==========================================
        // START
        // ==========================================

        simulation.start();


        // ==========================================
        // FINISHED CLOUDLETS
        // ==========================================

        List<Cloudlet> finished =
                broker.getCloudletFinishedList();


        // ==========================================
        // METRICS
        // ==========================================

        return new MetricsManager().calculate(
                scheduler.getName(),
                finished,
                vms
        );
    }
}