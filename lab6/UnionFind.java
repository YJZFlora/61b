public class UnionFind {

    private int[] parent;
    private int count;
    private int[] size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        count = n;
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        int n = parent.length;
        if (vertex >= n || vertex < 0) {
            throw new IllegalArgumentException("illegal vertex");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        return size[root];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        if (v1 == parent[v1]) {
            return -1;
        }
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return;
        }
        int bigger = v2;
        int smaller = v1;

        if (sizeOf(v1) > sizeOf(v2)) {
            bigger = v1;
            smaller = v2;
        }
        int broot = find(bigger);
        int sroot = find(smaller);

        size[broot] += sizeOf(sroot);
        parent[sroot] = broot;

        count--;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = vertex;
        while (root != parent[root]) {
            root = parent[root];
        }
        while (root != parent[vertex]) {
            int newv = parent[vertex];
            parent[vertex] = root;
            vertex = newv;
        }
        return root;
    }

}
