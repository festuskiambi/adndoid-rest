package com.festus.resttutorial.util;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements BaseSchedulerProvider {
    @Override
    public Scheduler getIOScheduler() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler getComputerScheduler() {
        return Schedulers.trampoline();    }

    @Override
    public Scheduler getUiScheduler() {
        return Schedulers.trampoline();    }
}