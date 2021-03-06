package com.example.festus.resttuitorial.util;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Festus Kiambi on 11/27/18.
 */
public class SchedulerProvider implements BaseSchedulerProvider {

    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();    }

    @Override
    public Scheduler getComputerScheduler() {
        return Schedulers.trampoline();    }

    @Override
    public Scheduler getUiScheduler() {
        return Schedulers.trampoline();    }
}
