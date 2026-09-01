package com.lokesh.cloudsim.metrics;

import org.cloudsimplus.cloudlets.Cloudlet;

import java.util.List;

public class MetricsManager {

    public void printMetrics(List<Cloudlet> cloudletList) {

        System.out.println("\n========== Simulation Results ==========");

        System.out.printf(
                "%-10s %-8s %-12s %-12s %-15s%n",
                "Cloudlet",
                "VM",
                "Start",
                "Finish",
                "Execution"
        );

        double makespan = 0;
        double totalExecution = 0;

        for (Cloudlet cloudlet : cloudletList) {

            System.out.printf(
                    "%-10d %-8d %-12.2f %-12.2f %-15.2f%n",

                    cloudlet.getId(),

                    cloudlet.getVm().getId(),



                   cloudlet.getStartTime(),


                    cloudlet.getFinishTime(),


                    cloudlet.getTotalExecutionTime()
            );

            if (cloudlet.getFinishTime() > makespan) {

                makespan = cloudlet.getFinishTime();

            }

            totalExecution += cloudlet.getTotalExecutionTime();

        }

        double averageExecution =
                totalExecution / cloudletList.size();

        double throughput =
                cloudletList.size() / makespan;

        System.out.println();

        System.out.printf("Makespan          : %.2f%n", makespan);

        System.out.printf("Average Execution : %.2f%n", averageExecution);

        System.out.printf("Throughput        : %.4f Cloudlets/sec%n", throughput);

    }
}