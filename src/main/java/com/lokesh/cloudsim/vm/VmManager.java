package com.lokesh.cloudsim.vm;

import com.lokesh.cloudsim.config.SimulationConfig;
import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;

import java.util.ArrayList;
import java.util.List;

public class VmManager {
    public List<Vm> createVms() {
        List<Vm> vmList = new ArrayList<>();

        for (int i = 0; i < SimulationConfig.VM_COUNT; i++) {
            Vm vm = new VmSimple(
                    i,
                    SimulationConfig.VM_MIPS,
                    SimulationConfig.VM_PES
            );

            vm.setCloudletScheduler(new org.cloudsimplus.schedulers.cloudlet.CloudletSchedulerSpaceShared());

            vm.setRam(SimulationConfig.VM_RAM)
              .setBw(SimulationConfig.VM_BW)
              .setSize(SimulationConfig.VM_SIZE);

            vmList.add(vm);
        }

        return vmList;
    }
}
