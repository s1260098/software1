import java.util.*;

public class hashtest
{
    public static void main(String args[]) {
        HashSet<MyClass> h = new HashSet<>();
        MyClass t=new MyClass(0, "a", "c");
        h.add(new MyClass(0, "a", "b"));
        h.add(new MyClass(0, "a", "c"));
        System.out.println(h.contains(t));

        for(MyClass c:h)
        System.out.println(c);

    }






}