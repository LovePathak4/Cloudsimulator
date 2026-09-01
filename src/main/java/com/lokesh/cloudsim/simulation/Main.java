package com.lokesh.cloudsim.simulation;

import com.lokesh.cloudsim.datacenter.DatacenterManager;
import com.lokesh.cloudsim.metrics.MetricsManager;
import com.lokesh.cloudsim.host.HostManager;
import com.lokesh.cloudsim.scheduler.RoundRobinScheduler;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.Datacenter;
import org.cloudsimplus.hosts.Host;
import com.lokesh.cloudsim.broker.BrokerManager;
import org.cloudsimplus.brokers.DatacenterBroker;
import com.lokesh.cloudsim.vm.VmManager;
import com.lokesh.cloudsim.cloudlet.CloudletManager;
import com.lokesh.cloudsim.scheduler.RoundRobinScheduler;
import com.lokesh.cloudsim.scheduler.SJFScheduler;


import com.lokesh.cloudsim.scheduler.Scheduler;
import com.lokesh.cloudsim.scheduler.FCFSScheduler;

import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.cloudlets.Cloudlet;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CloudSimPlus simulation = new CloudSimPlus();

        HostManager hostManager = new HostManager();

        Host host = hostManager.createHost();

        List<Host> hostList = new ArrayList<>();

        hostList.add(host);

        DatacenterManager datacenterManager = new DatacenterManager();

        Datacenter datacenter =
                datacenterManager.createDatacenter(
                        simulation,
                        hostList
                );


        BrokerManager brokerManager = new BrokerManager();

        DatacenterBroker broker =
                brokerManager.createBroker(simulation);

        VmManager vmManager = new VmManager();
        List<Vm> vmList = vmManager.createVms();

        CloudletManager cloudletManager =
                new CloudletManager();


        List<Cloudlet> cloudletList =
                cloudletManager.createCloudlets();

         // Scheduler scheduler = new FCFSScheduler();


        //Scheduler scheduler = new RoundRobinScheduler();

        Scheduler scheduler = new SJFScheduler();



        scheduler.schedule(vmList, cloudletList);


        broker.submitVmList(vmList);

        broker.submitCloudletList(cloudletList);






        System.out.println("Simulation Created Successfully");

        System.out.println("Hosts : " + hostList.size());

        System.out.println("Datacenter : " + datacenter);

        System.out.println("Broker : " + broker);


        simulation.start();


        List<Cloudlet> finishedCloudlets =
                broker.getCloudletFinishedList();




        MetricsManager metricsManager =
                new MetricsManager();

        metricsManager.printMetrics(finishedCloudlets);
    }
}