package com.github.dev.muzi.base.concurrent.knowledge.exercise.struct.kmp;

public class KMPStringMatch {

    private KMPStringMatch() {
    }


    public static void main(String[] args) {
        matcher(new char[]{'a','b','a','c','v','b','d','c','a','a','a','c','b'},new char[]{'a','a','a'});
    }

    public static void matcher(char[] t, char[] p) {
        int n = t.length;
        int m = p.length;
        int[] pi = computePrefixFunction(p);
        int q = 0;

        for (int i = 0; i < n; i++) {
            while (q > 0 && (p[q + 1] != t[i])) {
                q = pi[q];
            }
            if (p[q + 1] == t[i]) {
                q = q + 1;
            }
            if (q == m) {
                System.out.println("Pattern occurs with shift " + (i - m));
            }
            q = pi[q];
        }
    }

    public static int[] computePrefixFunction(char[] p) {
        int m = p.length;
        int[] pi = new int[m];
        pi[1] = 0;
        int k = 0;
        for (int q = 2; q < m; q++) {
            while (k > 0 && (p[k + 1] != p[q])) {
                k = pi[k];
            }
            if (p[k + 1] == p[q]) {
                k += 1;
            }
            pi[q] = k;
        }
        return pi;
    }
}
