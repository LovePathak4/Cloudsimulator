package com.lokesh.cloudsim.config;

public final class SimulationConfig {

    private SimulationConfig() {}

    // ================= HOST CONFIGURATION =================

    public static final int HOST_COUNT = 2;

    public static final int HOST_PES = 4;

    public static final double HOST_MIPS = 1000;

    public static final long HOST_RAM = 16_384;      // 16 GB

    public static final long HOST_BW = 20_000;

    public static final long HOST_STORAGE = 1_000_000;


    // ================= VM CONFIGURATION =================

    public static final int VM_COUNT = 4;

    public static final double VM_MIPS = 1000;

    public static final int VM_PES = 2;

    public static final long VM_RAM = 4096;          // 4 GB

    public static final long VM_BW = 2000;

    public static final long VM_SIZE = 10_000;


    // ================= CLOUDLET CONFIGURATION =================

    public static final int CLOUDLET_PES = 2;

    public static final long[] CLOUDLET_LENGTHS = {
            30_000,
            5_000,
            40_000,
            10_000,
            20_000,
            15_000,
            50_000,
            25_000,
            35_000,
            8_000,
            12_000,
            45_000,
            18_000,
            28_000,
            6_000,
            22_000,
            38_000,
            14_000,
            32_000,
            9_000
    };
}