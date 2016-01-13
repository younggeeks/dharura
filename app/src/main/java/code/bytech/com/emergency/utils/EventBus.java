package code.bytech.com.emergency.utils;

import com.squareup.otto.Bus;

/**
 * Created by samjunior on 8/17/15.
 */
public class EventBus extends Bus {


    private static final EventBus bus=new EventBus();

    public static Bus getInstance(){return bus; }
}
