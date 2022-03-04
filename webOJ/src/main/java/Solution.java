import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int temp;
        for(int i = 0;i < nums.length;i++){
            temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{map.get(temp),i};
            }
            map.put(nums[i],i);
        }
        return null;
    }
    public static void main(String[] args) {
//        Solution solution = new Solution();
//        int len = 2;
//        int[] result = null;
//        boolean flag = true;
//        if(flag && ((result = solution.twoSum(new int[]{2,7,11,15},9)) == null || result.length != len || result[0] != 0 || result[1] != 1)){
//            System.out.println("{2,7,11,15},9 用例不通过");
//            flag = false;
//        }
//        if(flag && ((result = solution.twoSum(new int[]{3,2,4},6)) == null || result.length != len || result[0] != 1 || result[1] != 2)){
//            System.out.println("{3,2,4},6 用例不通过");
//            flag = false;
//        }
//        if(flag && ((result = solution.twoSum(new int[]{3,3},6)) != null || result.length != len || result[0] != 0 || result[1] != 1)){
//            System.out.println("{3,3},6 用例不通过");
//            flag = false;
//        }
//        if(flag){
//            System.out.println("恭喜用例全部通过");
//        }
        Solution solution = new Solution();
        int[] ints = solution.twoSum(new int[]{3, 3}, 6);
        System.out.println(ints[0]+","+ints[1]);
    }
}
