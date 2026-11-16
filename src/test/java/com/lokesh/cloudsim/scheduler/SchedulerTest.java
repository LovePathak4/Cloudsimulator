package com.lokesh.cloudsim.scheduler;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.cloudlets.CloudletSimple;
import org.cloudsimplus.vms.Vm;
import org.cloudsimplus.vms.VmSimple;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerTest {
    private List<Vm> vms() {
        return List.of(new VmSimple(0, 1000, 2), new VmSimple(1, 1000, 2));
    }

    private List<Cloudlet> cloudlets() {
        List<Cloudlet> list = List.of(
                new CloudletSimple(30000, 2),
                new CloudletSimple(5000, 2),
                new CloudletSimple(10000, 2),
                new CloudletSimple(20000, 2)
        );
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i);
        }
        return list;
    }

    @Test
    void roundRobinShouldDistributeCloudletsAcrossVms() {
        List<Vm> vms = vms();
        List<Cloudlet> cloudlets = cloudlets();

        new RoundRobinScheduler().schedule(vms, cloudlets);

        assertSame(vms.get(0), cloudlets.get(0).getVm());
        assertSame(vms.get(1), cloudlets.get(1).getVm());
        assertSame(vms.get(0), cloudlets.get(2).getVm());
        assertSame(vms.get(1), cloudlets.get(3).getVm());
    }

    @Test
    void sjfShouldAssignShortestJobsFirst() {
        List<Vm> vms = vms();
        List<Cloudlet> cloudlets = cloudlets();

        new SJFScheduler().schedule(vms, cloudlets);

        assertSame(vms.get(0), cloudlets.get(1).getVm());
        assertSame(vms.get(1), cloudlets.get(2).getVm());
        assertSame(vms.get(0), cloudlets.get(3).getVm());
        assertSame(vms.get(1), cloudlets.get(0).getVm());
    }

    @Test
    void fcfsShouldAssignToLeastLoadedVmInOrder() {
        List<Vm> vms = vms();
        List<Cloudlet> cloudlets = cloudlets();

        new FCFSScheduler().schedule(vms, cloudlets);

        assertSame(vms.get(0), cloudlets.get(0).getVm());
        assertSame(vms.get(1), cloudlets.get(1).getVm());
        assertSame(vms.get(1), cloudlets.get(2).getVm());
        assertSame(vms.get(1), cloudlets.get(3).getVm());
    }

    @Test
    void ljfShouldAssignLongestJobsFirst() {
        List<Vm> vms = vms();
        List<Cloudlet> cloudlets = cloudlets();

        new LJFScheduler().schedule(vms, cloudlets);

        assertSame(vms.get(0), cloudlets.get(0).getVm());
        assertSame(vms.get(1), cloudlets.get(3).getVm());
        assertSame(vms.get(1), cloudlets.get(2).getVm());
        assertSame(vms.get(0), cloudlets.get(1).getVm());
    }

    @Test
    void schedulersShouldThrowOnEmptyInputs() {
        List<Scheduler> schedulers = List.of(
                new FCFSScheduler(),
                new RoundRobinScheduler(),
                new SJFScheduler(),
                new LJFScheduler()
        );

        List<Vm> vms = vms();
        List<Cloudlet> cloudlets = cloudlets();

        for (Scheduler scheduler : schedulers) {
            assertThrows(IllegalArgumentException.class, () -> scheduler.schedule(null, cloudlets));
            assertThrows(IllegalArgumentException.class, () -> scheduler.schedule(List.of(), cloudlets));
            assertThrows(IllegalArgumentException.class, () -> scheduler.schedule(vms, null));
            assertThrows(IllegalArgumentException.class, () -> scheduler.schedule(vms, List.of()));
        }
    }
}
