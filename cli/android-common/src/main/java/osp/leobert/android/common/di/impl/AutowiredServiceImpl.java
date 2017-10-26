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

package osp.leobert.android.common.di.impl;

import android.util.LruCache;

import java.util.ArrayList;
import java.util.List;

import osp.leobert.android.common.di.AutowiredService;
import osp.leobert.android.common.di.ISyringe;

/**
 * <p><b>Package:</b> osp.leobert.android.common.di.impl </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> AutowiredServiceImpl </p>
 * <p><b>Description:</b> implement of {@link AutowiredService},used to fetch
 * data from bundles in the intent</p>
 * Created by leobert on 2017/9/18.
 */

public class AutowiredServiceImpl implements AutowiredService {

    private LruCache<String, ISyringe> classCache = new LruCache<>(50);
    private List<String> blackList = new ArrayList<>();

    //attention! make sure this keeps same with the one in AutowiredProcessor
    private static final String SUFFIX_AUTOWIRED = "$$Router$$Autowired";

    @Override
    public void autowire(Object instance) {
        String className = instance.getClass().getName();
        try {
            if (!blackList.contains(className)) {
                ISyringe autowiredHelper = classCache.get(className);
                if (null == autowiredHelper) {  // No cache.
                    autowiredHelper = (ISyringe) Class.forName(instance.getClass().getName() + SUFFIX_AUTOWIRED)
                            .getConstructor().newInstance();
                }
                autowiredHelper.inject(instance);
                classCache.put(className, autowiredHelper);
            }
        } catch (Exception ex) {
            blackList.add(className);    // This instance need not autowired.
        }
    }
}
