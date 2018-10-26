import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**

 */

public class SmallestRange {

    public static void main(String[] args) {
        List<List<Integer>> input = new ArrayList<>();

        List<Integer> A = Arrays.asList(new Integer[]{4,10,15,24,26});
        List<Integer> B = Arrays.asList(new Integer[]{0,9,12,20});
        List<Integer> C = Arrays.asList(new Integer[]{5,18,22,30});

        input.add(A);
        input.add(B);
        input.add(C);

        SmallestRange sr = new SmallestRange();

        int [] arr = sr.smallestRange(input);
        System.out.println(Arrays.toString(arr));

    }

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> min=new PriorityQueue<>(1,new Comparator<int[]>(){
            public int compare(int[] n1,int[] n2){
                return nums.get(n1[0]).get(n1[1])-nums.get(n2[0]).get(n2[1]);
            }
        });

        PriorityQueue<int[]> max=new PriorityQueue<>(1,new Comparator<int[]>(){
            public int compare(int[] n1,int[] n2){
                return nums.get(n2[0]).get(n2[1])-nums.get(n1[0]).get(n1[1]);
            }
        });

        for(int i=0;i<nums.size();i++){
            int[] tmp=new int[]{i,0};
            min.offer(tmp);
            max.offer(tmp);
        }
        int[] res=new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE};

        while(min.size()==nums.size()){
            int[] m1=max.peek();
            int[] m2=min.poll();
            if((long)nums.get(m1[0]).get(m1[1])-(long)nums.get(m2[0]).get(m2[1])<(long)res[1]-(long)res[0]){
                res[0]=nums.get(m2[0]).get(m2[1]);
                res[1]=nums.get(m1[0]).get(m1[1]);
            }

            if(m2[1]+1<nums.get(m2[0]).size()){
                int[] m3=new int[]{m2[0],m2[1]+1};
                min.offer(m3);
                max.offer(m3);
                max.remove(m2);
            }
        }

        return res;
    }
}
