package com.lokesh.cloudsim.vm;

import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class VmManager {

    public List<Vm> createVms() {

        List<Vm> vmList = new ArrayList<>();

        Vm vm1 = new VmSimple(1000, 2);

        vm1.setRam(2048)
                .setBw(1000)
                .setSize(10000);

        Vm vm2 = new VmSimple(1000, 2);

        vm2.setRam(2048)
                .setBw(1000)
                .setSize(10000);

        vmList.add(vm1);
        vmList.add(vm2);

        return vmList;
    }
}