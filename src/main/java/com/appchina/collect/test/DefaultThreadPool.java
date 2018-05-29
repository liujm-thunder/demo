package com.appchina.collect.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DefaultThreadPool <Job extends Runnable> implements ThreadPool{

    private static final int MAX_WORKER_NUMBERS=10;
    private static final int DEFAULT_WORKER_NUMBERS=5;
    private static final int MIN_WORKER_NUMBERS=1;

    private final LinkedList<Job> jobs = new LinkedList<Job>();

    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private int workNum = DEFAULT_WORKER_NUMBERS;



    @Override
    public void execute(Runnable runnable) {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public void addWokers(int num) {

    }

    @Override
    public void removeWorker(int num) {

    }

    @Override
    public int getJobSize() {
        return 0;
    }


    class Worker implements Runnable{
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        }catch (InterruptedException e){
                            Thread.currentThread().interrupt();
                            return;
                        }
                        job = jobs.removeFirst();
                    }

                    if (job!=null){
                        try {
                            job.run();
                        }catch (Exception e){

                        }
                    }
                }
            }
        }
    }
}
