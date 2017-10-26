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

package osp.leobert.android.router.facade.model;


import java.util.Map;

import javax.lang.model.element.Element;
import osp.leobert.android.router.facade.enums.NodeType;

import static osp.leobert.android.router.facade.Utils.checkNull;


/**
 * <p><b>Package:</b> osp.leobert.android.router.facade.model </p>
 * <p><b>Project:</b> router-annotation </p>
 * <p><b>Classname:</b> Node </p>
 * <p><b>Description:</b> TODO </p>
 * Created by leobert on 2017/9/19.
 */

public class Node {
    private NodeType nodeType;
    private Element rawType;        // Raw type of route
    private Class<?> destination;   // Destination
    private String path;            // Path of route
    private int priority = -1;
    private Map<String, Integer> paramsType;


    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Element getRawType() {
        return rawType;
    }

    public void setRawType(Element rawType) {
        this.rawType = rawType;
    }

    public Class<?> getDestination() {
        return destination;
    }

    public void setDestination(Class<?> destination) {
        this.destination = destination;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Map<String, Integer> getParamsType() {
        return paramsType;
    }

    public void setParamsType(Map<String, Integer> paramsType) {
        this.paramsType = paramsType;
    }

    public static String formatAlias(Node node) {
        checkNull(node, "node");
        String path = node.getPath();
        return ("UI" + path.replaceAll("/", "_")).toUpperCase();
    }
}
