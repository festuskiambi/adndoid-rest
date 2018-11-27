package com.example.festus.resttuitorial.util;

import io.reactivex.Scheduler;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public class SchedulerProvider implements BaseSchedulerProvider {

    @Override
    public Scheduler getIOScheduler() {
        return null;
    }

    @Override
    public Scheduler getComputerScheduler() {
        return null;
    }

    @Override
    public Scheduler getUiScheduler() {
        return null;
    }
}
