package com.example.festus.resttuitorial.util;

import io.reactivex.Scheduler;

/**
 * Created by Festus Kiambi on 11/24/18.
 */
public interface BaseSchedulerProvider {

    Scheduler getIOScheduler();

    Scheduler getComputerScheduler();

    Scheduler getUiScheduler();
}

