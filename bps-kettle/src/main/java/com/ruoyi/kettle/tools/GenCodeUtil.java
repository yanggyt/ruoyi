package com.ruoyi.kettle.tools;


import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

public class GenCodeUtil {
    private static final long TWEPOCH = 1288834974657L;
    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 5L;
    private static final long MAX_WORKER_ID = 31L;
    private static final long MAX_DATACENTER_ID = 31L;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = 12L;
    private static final long DATACENTER_ID_SHIFT = 17L;
    private static final long TIMESTAMP_LEFT_SHIFT = 22L;
    private static final long SEQUENCE_MASK = 4095L;
    private static long lastTimestamp = -1L;
    private static long sequence = 0L;
    private static long workerId;
    private static long datacenterId;

    public GenCodeUtil() {
        datacenterId = getDatacenterId(31L);
        workerId = getMaxWorkerId(datacenterId, 31L);
    }

    public GenCodeUtil(long workerId, long datacenterId) {
        if (workerId <= 31L && workerId >= 0L) {
            if (datacenterId <= 31L && datacenterId >= 0L) {
                GenCodeUtil.workerId = workerId;
                GenCodeUtil.datacenterId = datacenterId;
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }

    public static synchronized String nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        } else {
            if (lastTimestamp == timestamp) {
                sequence = sequence + 1L & 4095L;
                if (sequence == 0L) {
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;
            long nextId = timestamp - 1288834974657L << 22 | datacenterId << 17 | workerId << 12 | sequence;
            return String.valueOf(nextId);
        }
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for(timestamp = timeGen(); timestamp <= lastTimestamp; timestamp = timeGen()) {
        }

        return timestamp;
    }

    private static long timeGen() {
        return System.currentTimeMillis();
    }

    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            mpid.append(name.split("@")[0]);
        }

        return (long)(mpid.toString().hashCode() & '\uffff') % (maxWorkerId + 1L);
    }

    protected static long getDatacenterId(long maxDatacenterId) {
        long id = 0L;

        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = (255L & (long)mac[mac.length - 1] | 65280L & (long)mac[mac.length - 2] << 8) >> 6;
                id %= maxDatacenterId + 1L;
            }
        } catch (Exception var7) {
            System.out.println(" getDatacenterId: " + var7.getMessage());
        }

        return id;
    }

    public static void main(String[] args) {
        System.out.println(nextId());
    }
}
