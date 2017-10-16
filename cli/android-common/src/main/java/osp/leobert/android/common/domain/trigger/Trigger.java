package osp.leobert.android.common.domain.trigger;

import java.io.Serializable;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.trigger </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> Trigger </p>
 * <p><b>Description:</b>  事件触发器对比接口，事件使用枚举定义。
 * 定义该接口是因为对象的序列化和反序列化导致无法直接使用 == operate无法得到预想的结果
 * Created by leobert on 2017/10/16.
 */

public interface Trigger<T> {
    /**
     * 对比是否是同一个,有时需要利用到序列化，注意！
     *
     * @param compare
     * @return
     */
    boolean equals(Trigger<T> compare);

    T getTag();

    Serializable getSerializable();
}
