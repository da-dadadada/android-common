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

package osp.leobert.android.router.facade.enums;

/**
 * <p><b>Package:</b> osp.leobert.android.router.facade.enums </p>
 * <p><b>Project:</b> router-annotation </p>
 * <p><b>Classname:</b> NodeType </p>
 * <p><b>Description:</b> tag the type of one node in route net </p>
 * Created by leobert on 2017/9/19.
 */

public enum NodeType {
    ACTIVITY(0, "android.app.Activity"),
    INVALID(-1,"invalid node type, currently only activity allowed");

    int id;
    String className;

    NodeType(int id, String className) {
        this.id = id;
        this.className = className;
    }

    public int getId() {
        return id;
    }

    public NodeType setId(int id) {
        this.id = id;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public NodeType setClassName(String className) {
        this.className = className;
        return this;
    }

    public static NodeType parse(String name) {
        for (NodeType nodeType : NodeType.values()) {
            if (nodeType.getClassName().equals(name)) {
                return nodeType;
            }
        }

        return INVALID;
    }

}
