package com.lokesh.cloudsim.metrics;

public record SimulationResult(

        // Scheduling algorithm name
        String algorithm,

        // Number of completed cloudlets
        int cloudlets,

        // Total time taken by the workload
        double makespan,

        // Average execution time of cloudlets
        double averageExecutionTime,

        // Average waiting time before execution
        double averageWaitingTime,

        // Average turnaround time
        double averageTurnaroundTime,

        // Completed cloudlets per unit of simulation time
        double throughput,

        // Overall VM utilization percentage
        double vmUtilization

) {
}