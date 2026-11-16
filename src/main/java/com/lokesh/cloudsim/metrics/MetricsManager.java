package com.lokesh.cloudsim.metrics;

import org.cloudsimplus.cloudlets.Cloudlet;
import org.cloudsimplus.vms.Vm;

import java.util.List;

public class MetricsManager {

    /**
     * Calculates performance metrics for a scheduling algorithm.
     *
     * @param algorithm name of the scheduling algorithm
     * @param cloudlets completed cloudlets
     * @param vmList list of VMs used in the simulation
     * @return calculated simulation result
     */
    public SimulationResult calculate(
            String algorithm,
            List<Cloudlet> cloudlets,
            List<Vm> vmList
    ) {

        if (algorithm == null || algorithm.isBlank()) {
            throw new IllegalArgumentException(
                    "Algorithm name cannot be empty."
            );
        }

        if (cloudlets == null || cloudlets.isEmpty()) {
            throw new IllegalArgumentException(
                    "No finished cloudlets available."
            );
        }

        if (vmList == null || vmList.isEmpty()) {
            throw new IllegalArgumentException(
                    "No VMs available."
            );
        }


        // -------------------------------------------------
        // 1. Makespan
        // -------------------------------------------------

        double makespan = cloudlets.stream()
                .mapToDouble(Cloudlet::getFinishTime)
                .max()
                .orElse(0.0);


        // -------------------------------------------------
        // 2. Total execution time
        // -------------------------------------------------

        double totalExecutionTime = cloudlets.stream()
                .mapToDouble(Cloudlet::getTotalExecutionTime)
                .sum();


        // -------------------------------------------------
        // 3. Total waiting time
        // -------------------------------------------------

        /*
         * For this simulation, Cloudlets are submitted at
         * time 0, therefore their start time represents
         * the time spent waiting before execution.
         */
        double totalWaitingTime = cloudlets.stream()
                .mapToDouble(cloudlet ->
                        Math.max(0.0, cloudlet.getStartTime())
                )
                .sum();


        // -------------------------------------------------
        // 4. Total turnaround time
        // -------------------------------------------------

        double totalTurnaroundTime = cloudlets.stream()
                .mapToDouble(Cloudlet::getFinishTime)
                .sum();


        // -------------------------------------------------
        // 5. Average metrics
        // -------------------------------------------------

        int cloudletCount = cloudlets.size();

        double averageExecutionTime =
                totalExecutionTime / cloudletCount;

        double averageWaitingTime =
                totalWaitingTime / cloudletCount;

        double averageTurnaroundTime =
                totalTurnaroundTime / cloudletCount;


        // -------------------------------------------------
        // 6. Throughput
        // -------------------------------------------------

        /*
         * Throughput = Number of completed cloudlets /
         *               total simulation time
         */
        double throughput = makespan > 0
                ? cloudletCount / makespan
                : 0.0;


        // -------------------------------------------------
        // 7. VM utilization
        // -------------------------------------------------

        /*
         * Overall VM utilization:
         *
         * Total execution time /
         * (Makespan × Number of VMs) × 100
         */
        double vmUtilization = makespan > 0
                ? (totalExecutionTime
                / (makespan * vmList.size())) * 100.0
                : 0.0;


        return new SimulationResult(
                algorithm,
                cloudletCount,
                makespan,
                averageExecutionTime,
                averageWaitingTime,
                averageTurnaroundTime,
                throughput,
                vmUtilization
        );
    }


    /**
     * Prints execution details of every completed Cloudlet.
     */
    public void printCloudletDetails(
            List<Cloudlet> cloudlets
    ) {

        if (cloudlets == null || cloudlets.isEmpty()) {
            System.out.println(
                    "No cloudlet details available."
            );
            return;
        }

        System.out.println(
                "\n================ CLOUDLET DETAILS ================"
        );

        System.out.printf(
                "%-10s %-8s %-12s %-12s %-15s%n",
                "Cloudlet",
                "VM",
                "Start",
                "Finish",
                "Execution"
        );

        System.out.println(
                "---------------------------------------------------------------"
        );

        for (Cloudlet cloudlet : cloudlets) {

            System.out.printf(
                    "%-10d %-8d %-12.2f %-12.2f %-15.2f%n",
                    cloudlet.getId(),
                    cloudlet.getVm().getId(),
                    cloudlet.getStartTime(),
                    cloudlet.getFinishTime(),
                    cloudlet.getTotalExecutionTime()
            );
        }
    }


    /**
     * Prints performance comparison of all scheduling algorithms.
     */
    public void printComparison(
            List<SimulationResult> results
    ) {

        if (results == null || results.isEmpty()) {
            System.out.println(
                    "No simulation results available."
            );
            return;
        }

        System.out.println(
                "\n================ ALGORITHM COMPARISON ================"
        );

        System.out.printf(
                "%-14s %-12s %-12s %-12s %-12s %-14s %-12s%n",
                "Algorithm",
                "Makespan",
                "Avg Wait",
                "Avg Turn",
                "Avg Exec",
                "Throughput",
                "VM Util %"
        );

        System.out.println(
                "--------------------------------------------------------------------------------"
        );

        for (SimulationResult result : results) {

            System.out.printf(
                    "%-14s %-12.2f %-12.2f %-12.2f %-12.2f %-14.4f %-12.2f%n",
                    result.algorithm(),
                    result.makespan(),
                    result.averageWaitingTime(),
                    result.averageTurnaroundTime(),
                    result.averageExecutionTime(),
                    result.throughput(),
                    result.vmUtilization()
            );
        }

        System.out.println(
                "--------------------------------------------------------------------------------"
        );
    }
}