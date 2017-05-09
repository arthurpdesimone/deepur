package com.ruiriot.deepur;

import com.google.common.eventbus.EventBus;

/**
 * Created by ruiri on 09-May-17.
 */

final class Event {

    private static EventBus eventBus = new EventBus( );

    public static void register(Object o){
        eventBus.register(o);
    }

    public static void unregister(Object o){
        eventBus.unregister(o);
    }

    public static void post(Object o){
        eventBus.post(o);
    }
}
