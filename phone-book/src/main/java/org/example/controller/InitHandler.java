package org.example.controller;

import org.example.init.HashTableInit;
import org.example.util.TimeUtil;

import java.util.List;
import java.util.Map;

class InitHandler {

    private long initRunTime;

    InitHandler() {}

    Map<String, String> runHashTableInitializer(List<String> contactList) {
        long start = TimeUtil.timer();
        Map<String, String> contactMap = HashTableInit.hashTableInit(contactList);
        long end = TimeUtil.timer();
        this.initRunTime = TimeUtil.calculateTotalRunTime(start, end);
        return contactMap;
    }

    long getInitRunTime() {
        return initRunTime;
    }
}
