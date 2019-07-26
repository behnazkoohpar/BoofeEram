package com.farzam.rest.utils.rx;

import io.reactivex.Scheduler;

/**
 * behnaz on 10/29/2017.
 */

public interface SchedulerProvider  {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
