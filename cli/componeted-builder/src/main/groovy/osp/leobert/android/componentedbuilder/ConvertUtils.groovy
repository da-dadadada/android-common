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

package osp.leobert.android.componentedbuilder

import com.android.SdkConstants
import com.android.build.api.transform.TransformInput
import javassist.ClassPool
import javassist.CtClass

import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.regex.Matcher

class ConvertUtils {
    static List<CtClass> toCtClasses(Collection<TransformInput> inputs,
                                     ClassPool classPool) {
        List<String> classNames = new ArrayList<>()
        List<CtClass> allClass = new ArrayList<>();
        inputs.each {
            it.directoryInputs.each {
                def dirPath = it.file.absolutePath
                classPool.insertClassPath(it.file.absolutePath)
                org.apache.commons.io.FileUtils.listFiles(it.file, null, true).each {
                    if (it.absolutePath.endsWith(SdkConstants.DOT_CLASS)) {
                        def className = it.absolutePath.substring(dirPath.length() + 1, it.absolutePath.length() - SdkConstants.DOT_CLASS.length()).replaceAll(Matcher.quoteReplacement(File.separator), '.')
                        if (classNames.contains(className)) {
                            throw new RuntimeException("You have duplicate classes with the same name : " + className + " please remove duplicate classes ")
                        }
                        classNames.add(className)
                    }
                }
            }

            it.jarInputs.each {
                classPool.insertClassPath(it.file.absolutePath)
                def jarFile = new JarFile(it.file)
                Enumeration<JarEntry> classes = jarFile.entries();
                while (classes.hasMoreElements()) {
                    JarEntry libClass = classes.nextElement();
                    String className = libClass.getName();
                    if (className.endsWith(SdkConstants.DOT_CLASS)) {
                        className = className.substring(0, className.length() - SdkConstants.DOT_CLASS.length()).replaceAll('/', '.')
                        if (classNames.contains(className)) {
                            throw new RuntimeException("You have duplicate classes with the same name : " + className + " please remove duplicate classes ")
                        }
                        classNames.add(className)
                    }
                }
            }
        }
        classNames.each {
            try {
                allClass.add(classPool.get(it));
            } catch (javassist.NotFoundException e) {
                println "class not found exception class name:  $it "
            }
        }
        return allClass;
    }


}