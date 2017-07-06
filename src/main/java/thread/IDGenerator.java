package thread;

/**
 * Created by liujs on 17-7-7.
 */
public class IDGenerator {


    private IDGenerator(String name) {
        this.name = name;
    }

    private static volatile IDGenerator ID_GENERATOR;


    // 单例double check
    public static IDGenerator getInstance(String name) {
        if (ID_GENERATOR == null) {
            synchronized (IDGenerator.class) {
                if (ID_GENERATOR == null) {
                    ID_GENERATOR = new IDGenerator(name);
                    return ID_GENERATOR;
                }
            }
        }
        return ID_GENERATOR;
    }

    float maxid = 1f;

    float loadfactor = 0.75f;

    int step = 10;

    int concunrrentid = 1;

    String name;


    public int getNextId() {

        System.out.println("concunrrentid " + concunrrentid + " maxid = " + maxid + " 负载因子 =  " + (concunrrentid / maxid));

        //判断加载因子，实际会从DB中取出，并更新DB
        if (concunrrentid / maxid > loadfactor) {
            maxid += step;
            System.out.println("id用完，增加： " + step + " maxid = " + maxid);
        }
        int returnid = concunrrentid++;

        if (concunrrentid > maxid) {
            System.out.println("错误!!!! concunrrentid " + concunrrentid + " maxid = " + maxid);
        }
        //System.out.println("get id : " + returnid);
        return returnid;
    }


    public String getName() {
        return name;
    }
}
