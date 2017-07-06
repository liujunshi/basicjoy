package connection;

import java.util.ArrayList;

/**
 * Created by liujs on 17-7-7.
 */
public class ListTest {

    public void arrayListTest() {
        ArrayList<String> arrayList = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            arrayList.add("v" + i);
        }
        arrayList.add("va");
        arrayList.add("vb");

        for (String v : arrayList) {
            System.out.println(v);
        }
        arrayList.remove(0);
    }

    public static void main(String args[]) {
        ListTest listTest = new ListTest();
        listTest.arrayListTest();
    }
}
