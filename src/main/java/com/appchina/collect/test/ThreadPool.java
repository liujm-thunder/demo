package com.appchina.collect.test;

public interface ThreadPool <Job extends Runnable>{

    void execute(Job job);

    void shutdown();

    void addWokers(int num);

    void removeWorker(int num);

    int getJobSize();
}
