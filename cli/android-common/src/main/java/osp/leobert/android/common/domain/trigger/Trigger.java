/*
 * MIT License
 *
 * Copyright (c) 2017 leobert-lan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
