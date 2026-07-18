package com.lokesh.cloudsim.simulation;

import com.lokesh.cloudsim.datacenter.DatacenterManager;
import com.lokesh.cloudsim.host.HostManager;
import org.cloudsimplus.core.CloudSimPlus;
import org.cloudsimplus.datacenters.Datacenter;
import org.cloudsimplus.hosts.Host;
import com.lokesh.cloudsim.broker.BrokerManager;
import org.cloudsimplus.brokers.DatacenterBroker;
import com.lokesh.cloudsim.vm.VmManager;
import com.lokesh.cloudsim.cloudlet.CloudletManager;

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


        broker.submitVmList(vmList);

        broker.submitCloudletList(cloudletList);


        simulation.start();


        List<Cloudlet> finishedCloudlets =
                broker.getCloudletFinishedList();




        System.out.println("Simulation Created Successfully");

        System.out.println("Hosts : " + hostList.size());

        System.out.println("Datacenter : " + datacenter);

        System.out.println("Broker : " + broker);



        System.out.println("\nCompleted Cloudlets:");

        for (Cloudlet cloudlet : finishedCloudlets) {

            System.out.println(
                    "Cloudlet ID : " +
                            cloudlet.getId()
            );

        }
    }
}