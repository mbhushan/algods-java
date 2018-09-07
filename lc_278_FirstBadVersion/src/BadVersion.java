
public class BadVersion {
    private boolean [] A;

    BadVersion() {
        A = new boolean[10];

        init();

    }

    public void init() {
        for (int i=7; i<A.length; i++) {
            A[i] = true;
        }
    }

    public static void main(String[] args) {

        BadVersion bv = new BadVersion();

        bv.firstBadVersion(10);

    }


    public int firstBadVersion(int n) {

        int low = 0;
        int high = n-1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (!isBadVersion(mid)) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }

        System.out.println("low: " + low + ", high: " + high);
        return high;

    }

    public boolean isBadVersion(int version) {

        if (version < A.length) {
            return A[version];
        }

        return false;

    }
}

