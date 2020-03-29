package com.lvbby.coderflow.compile;

import java.util.Arrays;

public class DynaCompTest
{
    public static void main(String[] args) throws Exception {
        String fullName = "com.seeyon.proxy.MyClass";
        String src = "package com.seeyon.proxy;"
                     + "public class MyClass {\n"
                     + "\n"
                     + "int a; String b;"
                     + "}";

        System.out.println(src);
        DynamicEngine de = DynamicEngine.getInstance();
        Class instance =  de.javaCodeToClass(fullName,src.toString());
        Arrays.stream(instance.getDeclaredFields()).forEach(f->{
            System.out.println(f.getName());
        });
    }
}