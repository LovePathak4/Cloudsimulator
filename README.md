# Cloud Resource Scheduling Simulator

A Java-based cloud scheduling simulator built with **CloudSim Plus**. The project models hosts, virtual machines and cloudlets, then compares multiple scheduling strategies using reproducible workloads and performance metrics.

## Features

- Multi-host cloud datacenter simulation
- Configurable VM and Cloudlet workloads
- FCFS scheduling
- Round Robin scheduling
- Shortest Job First (SJF) scheduling
- Longest Job First (LJF) scheduling
- Makespan measurement
- Average execution time
- Average waiting time
- Average turnaround time
- Throughput
- Aggregate VM utilization
- JUnit scheduler tests

## Architecture

```text
Main
  |
  v
SimulationManager
  |
  +--> DatacenterManager --> HostManager
  +--> BrokerManager
  +--> VmManager
  +--> CloudletManager
  +--> Scheduler
  |      +--> FCFS
  |      +--> Round Robin
  |      +--> SJF
  |      +--> LJF
  |
  +--> MetricsManager --> SimulationResult
```

## Technology Stack

- Java 24
- Maven
- CloudSim Plus 8.5.7
- JUnit 5

## Run

### Option 1: Using Maven Wrapper (No local Maven install needed)

**Run tests:**
```bash
# Windows
.\mvnw.cmd test

# Linux/macOS
./mvnw test
```

**Run simulation directly:**
```bash
# Windows
.\mvnw.cmd compile exec:java

# Linux/macOS
./mvnw compile exec:java
```

### Option 2: Build & Run Executable Fat JAR
```bash
# Build shaded JAR
.\mvnw.cmd clean package

# Run standalone JAR
java -jar target/cloud-resource-scheduling-simulator-1.0.0.jar
```

### Option 3: Run from IDE
Execute `main()` in `com.lokesh.cloudsim.simulation.Main` directly in IntelliJ IDEA or your preferred Java IDE.

## Metrics

The simulator evaluates each algorithm on the same deterministic workload so the results can be compared using makespan, waiting time, turnaround time, execution time, throughput and aggregate VM utilization.

## Resume Value

This project demonstrates Java OOP, interfaces, algorithm design, cloud-computing concepts, simulation, performance analysis, modular architecture and automated testing.
