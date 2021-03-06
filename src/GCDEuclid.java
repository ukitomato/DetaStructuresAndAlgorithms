public class GCDEuclid {
    public static void main(final String[] args) {
        // Process arguments.
        if (args.length != 2) {
            System.err.println("Usage: java GCDIter <int1> <int2>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        // Find the greatest common divisor.
        int gcd = euclid(n, m);
        System.out.println("The GCD of " + m + " and " + n + " is " + gcd + ".") ;
    }

    // Find the greatest common divisor of the two integers, n and m.
    static int euclid(int n, int m) {
        if (n < m) {
            int tmp = n;
            n = m;
            m = tmp;
        }
        while (m != 0) {
            int r = n % m;
            n = m;
            m = r;
        }
        return n;
    }
}
