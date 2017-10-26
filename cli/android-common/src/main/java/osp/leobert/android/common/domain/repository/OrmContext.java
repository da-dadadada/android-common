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

package osp.leobert.android.common.domain.repository;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.repository </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> OrmContext </p>
 * <p><b>Description:</b>
 * define a class to implement this interface as follow:
 * <p>
 * public class DemoOrmContext&lt;Model&gt; implements OrmContext&lt;DemoOrmContext&lt;Model&gt;,Model&gt;
 * <p>
 * maybe LiteOrmContext with LiteOrm or OrmLiteContext with OrmLite
 * <p>
 * Created by leobert on 2017/10/18.
 */

public interface OrmContext<O extends OrmContext<O, Model>, Model> {

    int SINGLE = 1;

    int CASCADE = 2;

    O getOrmContext();


    @IntDef({SINGLE, CASCADE})
    @Retention(RetentionPolicy.SOURCE)
    @interface InstanceType {
    }

    boolean isConnected();


    long save(Model model);

    int save(Collection<Model> collection);

    long insert(Model model);

    int insert(Collection collection);

    int update(Model model);

    int update(Collection<Model> collection);

    int delete(Model model);

    int delete(Class<Model> aClass);

    int deleteAll(Class<Model> aClass);

    int delete(Collection<Model> collection);

    ArrayList<Model> query(Class<Model> aClass);

    Model queryById(long l, Class<Model> aClass);

    Model queryById(String s, Class<Model> aClass);

    long queryCount(Class<Model> aClass);

    boolean dropTable(Class<Model> aClass);

    void close();

}
