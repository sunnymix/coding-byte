package com.sunnymix.coding.bytes.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Javassist老版本(javassist:javassist:3.12.1.GA)无法自动添加当前的classpath，需要手动添加：
 * pool.insertClassPath(new ClassClassPath(Demo.class));
 * <p>
 * Javassist新版本(org.javassist:javassist:3.29.0-GA)已优化。
 */
public class DemoEnhance {

    public static void main(String[] args) throws Throwable {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("com.sunnymix.coding.bytes.demo.Demo");
        CtMethod m = cc.getDeclaredMethod("process");
        m.insertBefore("{ System.out.println(\"start\"); }");
        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        cc.writeFile("/tmp/coding/bytes");
        Demo demo = (Demo) c.getDeclaredConstructor().newInstance();
        demo.process();
    }

}
