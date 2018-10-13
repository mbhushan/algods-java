import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 350. Intersection of Two Arrays II
 https://leetcode.com/problems/intersection-of-two-arrays-ii/description/

 Given two arrays, write a function to compute their intersection.

 Example 1:

 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2,2]
 Example 2:

 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [4,9]
 Note:

 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:

 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is
 limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionArrays {

    public static void main(String[] args) {

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();

        for (int i: nums1) {
            map1.put(i, map1.getOrDefault(i, 0)+1);
        }

        for (int i: nums2) {
            map2.put(i, map2.getOrDefault(i, 0)+1);
        }

        List<Integer> list = new ArrayList<>();
        for (int i: nums1) {
            if (map1.containsKey(i) && map2.containsKey(i)) {
                int count = Math.min(map1.get(i), map2.get(i));
                for (int j=0; j<count; j++) {
                    list.add(i);
                }
                map2.remove(i);
            }


        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

/**
 vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
 vector<int> ret;
 if(nums1.empty() || nums2.empty()) return ret;
 sort(nums1.begin(), nums1.end());
 sort(nums2.begin(), nums2.end());
 int j=0;
 for(int i=0; i<nums1.size(); ) {
 int index = lower_bound(nums2, nums1[i]);
 int count2 = 0;
 while(index<nums2.size() && nums2[index]==nums1[i]) {
 count2++;
 index++;
 }
 int count1 = 0;
 while(nums1[j]==nums1[i]) {
 count1++;
 j++;
 }
 ret.insert(ret.end(),min(count1,count2),nums1[i]);
 i=j;
 }
 return ret;
 }

 int lower_bound(const vector<int>& nums, int target) {
 int l=0, r=nums.size()-1;
 while(l<r) {
 int m=l+(r-l)/2;
 if(nums[m]<target) {l=m+1;}
 else {r=m;}
 }
 return r;
 }

 */
