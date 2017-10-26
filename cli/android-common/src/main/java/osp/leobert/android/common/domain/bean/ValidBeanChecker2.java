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

package osp.leobert.android.common.domain.bean;

/**
 * <p><b>Package:</b> osp.leobert.android.common.domain.bean </p>
 * <p><b>Project:</b> cli </p>
 * <p><b>Classname:</b> ValidBeanChecker2 </p>
 * <p><b>Description:</b> when you have complex checking rules for a Data-Class,
 * you can implement this interface on a checker class encapsulating the logical
 * Then relay on the checker class when you need.
 * <p>
 * Created by leobert on 2017/10/18.
 */

public interface ValidBeanChecker2<B> {
    boolean VALID = true;
    boolean INVALID = false;

    /**
     * @return {@link #VALID} if the bean is valid,{@link #INVALID} otherwise
     */
    boolean isValid(B bean);
}
