package org.dhruvnotes.copyShallowDeep;

class test {
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long[] prefix = new long[n+1];
        prefix[0] = 0;
        for(int i = 0; i < n; i++){
            prefix[i] = (long)nums[i] + prefix[i-1];
        }

        int left = 0;
        long count = 0;
        for(int right = 0; right < n; right++){
            while(left <= right){
                long sum = prefix[right+1] - prefix[left]; //as we have taken one extra length prefix array
                long len = (right - left + 1);
                long score = sum * len;

                if(score < k) break;
                left++;
            }
            count += (right - left + 1);
        }
        return count;
    }
}

/*
(prefixSum[r] - prefixSum[l-1]) * (r - l + 1) < k

nums = [2, 1, 4, 3, 5]
prefixSum = [2, 3, 7, 10, 15]
score[i] = prefixSum[i] * (i + 1)
*/
