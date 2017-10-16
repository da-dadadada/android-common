package osp.leobert.android.common.domain.event;

import osp.leobert.android.common.domain.trigger.Trigger;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.event </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> TriggerHolderEvent </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/10/16.
 */

public class TriggerHolderEvent<T extends Trigger> {
    protected T trigger;

    public T getTrigger() {
        return trigger;
    }

    public void setTrigger(T trigger) {
        this.trigger = trigger;
    }
}