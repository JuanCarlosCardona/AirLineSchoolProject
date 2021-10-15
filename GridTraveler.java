import java.util.HashMap;
import java.util.Map;

public class GridTraveler
{
    private Map<String,Long> memo = new HashMap<>();

    public long gridTravelerMem(int m, int n)
    {
        String key = m + "," + n;

        if(this.memo.containsKey(key)) return this.memo.get(key);
        if(m == 1 && n == 1) return 1;
        if(m == 0 || n == 0) return 0;

        long result = gridTravelerMem(m - 1, n) + gridTravelerMem(m,n-1);
        this.memo.put(key,result);

        return this.memo.get(key);
    }

    public static int gridTraveler(int m, int n)
    {
        if(m == 1 && n == 1)
            return 1;
        if(m == 0 || n == 0)
            return 0;
        return gridTraveler(m -1, n) + gridTraveler(m,n-1);
    }


    public static void main(String[] args) {
        //System.out.println(gridTraveler(18,18));
        GridTraveler gridTraveler = new GridTraveler();
        System.out.println(gridTraveler.gridTravelerMem(4,3));
    }

}
