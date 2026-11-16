package com.lokesh.cloudsim.host;

import com.lokesh.cloudsim.config.SimulationConfig;
import org.cloudsimplus.hosts.Host;
import org.cloudsimplus.hosts.HostSimple;
import org.cloudsimplus.resources.Pe;
import org.cloudsimplus.resources.PeSimple;

import java.util.ArrayList;
import java.util.List;

public class HostManager {
    public List<Host> createHosts() {
        List<Host> hosts = new ArrayList<>();

        for (int hostId = 0; hostId < SimulationConfig.HOST_COUNT; hostId++) {
            List<Pe> peList = new ArrayList<>();
            for (int pe = 0; pe < SimulationConfig.HOST_PES; pe++) {
                peList.add(new PeSimple(SimulationConfig.HOST_MIPS));
            }

            hosts.add(new HostSimple(
                    SimulationConfig.HOST_RAM,
                    SimulationConfig.HOST_BW,
                    SimulationConfig.HOST_STORAGE,
                    peList
            ));
        }

        return hosts;
    }
}
