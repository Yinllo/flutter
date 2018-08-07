package com.example.yinl.simplebutterknife.Annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解：
 * 需要给其定义 该注解 的 使用范围
 */
//表示当前注解存在于字节码中，当源码被编译成字节码时，注解不会被清除
//在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式。
//@Retention(RetentionPolicy.CLASS)
//表示当前注解存在于源码中，当源码别编译成字节码时，注解会被清除
//在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码
//@Retention(RetentionPolicy.SOURCE)

//表示当前注解使用在方法上
//@Target(ElementType.METHOD)

//用于描述类、接口或enum声明
//@Target(ElementType.TYPE)
//表示当前注解存在于虚拟机
//始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindView {

    // 如果注解中只有一个属性，可以直接命名为“value”，使用时无需再标明属性名。
    //@BindView(R.id.y)

//    int value();

    //如果是其他值 比如
    int age();
    String name();

    //则在使用时 需要表明属性名 如 @BindView(age=18,name="yinl")

}
