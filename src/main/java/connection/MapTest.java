package connection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by liujs on 17-7-7.
 */
public class MapTest {

    public void hashMapTest() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < 10; i++) {
            hashMap.put("k" + i, "v" + i);
        }
        hashMap.put("a","ka");
        hashMap.put("b","kb");

        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            System.out.println("HashMap: " + entry.getKey() + " : " + entry.getValue());
        }

    }

    public void treemapTest() {
        TreeMap<String, String> treeMap = new TreeMap<String, String>();
        for (int i = 0; i < 10; i++) {
            treeMap.put("k" + i, "v" + i);
        }
        treeMap.put("a","ka");
        treeMap.put("b","kb");

        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println("treeMap: " + entry.getKey() + " : " + entry.getValue());
        }
    }  public void linkedHashMapTest() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < 10; i++) {
            linkedHashMap.put("k" + i, "v" + i);
        }
        linkedHashMap.put("a","ka");
        linkedHashMap.put("b","kb");

        for (Map.Entry<String, String> entry : linkedHashMap.entrySet()) {
            System.out.println("linkedHashMap: " + entry.getKey() + " : " + entry.getValue());
        }
    }


    public static void main(String args[]) {

        MapTest mapTest = new MapTest();
        mapTest.hashMapTest();
        mapTest.treemapTest();
        mapTest.linkedHashMapTest();
    }
}
