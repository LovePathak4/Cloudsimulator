package com.lokesh.cloudsim.simulation;

import com.lokesh.cloudsim.metrics.MetricsManager;
import com.lokesh.cloudsim.metrics.SimulationResult;
import com.lokesh.cloudsim.scheduler.FCFSScheduler;
import com.lokesh.cloudsim.scheduler.LJFScheduler;
import com.lokesh.cloudsim.scheduler.RoundRobinScheduler;
import com.lokesh.cloudsim.scheduler.SJFScheduler;
import com.lokesh.cloudsim.scheduler.Scheduler;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Scheduler> schedulers = List.of(
                new FCFSScheduler(),
                new RoundRobinScheduler(),
                new SJFScheduler(),
                new LJFScheduler()
        );

        SimulationManager simulationManager = new SimulationManager();
        MetricsManager metricsManager = new MetricsManager();

        System.out.println("====================================================");
        System.out.println("        CLOUD RESOURCE SCHEDULING SIMULATOR        ");
        System.out.println("====================================================");

        List<SimulationResult> results = schedulers.stream()
                .map(simulationManager::run)
                .toList();

        metricsManager.printComparison(results);

        System.out.println("\nSimulation completed successfully.");
    }
}
