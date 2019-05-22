public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    int row = grid.length;
    int col = grid[0].length;


    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] returnArray = new int[darts.length];
        /*
        1. grid[i, j] to dis-join set
        2. scan the grid and then union */
        UnionFind uf = makeUf();

        /* 3. check falls with darts[d]
        */
       for (int i = 0; i < returnArray.length; i++) {
           returnArray[i] = count(darts[i], uf);
       }

        /* 4. write into returnArray
         */

        return null;
    }

    // get the represent number of grid[i, j]
    private int numberOf(int i, int j) {
        // TODO
        return 1;
    }

    // from number to i position (which row)
    // TODO
    private int ipositionOf(int num) {
        return 0;
    }

    // from number to j position (which column)
    // TODO
    private int jpositionOf(int num) {
        return 0;
    }

    // make a UF, and then join
    private UnionFind makeUf() {
        // 1.
        UnionFind uf = new UnionFind(row * col);

        // 2.
        // not the last row or col
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col - 1; j++) {
                if (grid[i][j] == 1) {
                    if (grid[i + 1][j] == 1) {
                        uf.union(numberOf(i,j), numberOf(i + 1, j));
                    }
                    if (grid[i][j + 1] == 1) {
                        uf.union(numberOf(i,j), numberOf(i, j + 1));
                    }
                }
            }
        }
        // is the last row, union horizontally if possible
        for (int c = 0; c < col-1; c++) {
            if (grid[row - 1][c] == 1) {
                if (grid[row-1][c + 1] == 1) {
                    uf.union(numberOf(row-1, c), numberOf(row - 1, c + 1));
                }
            }
        }

        // is the last col
        for (int r = 0; r < row - 1; r++) {
            if (grid[r][col - 1] == 1) {
                if (grid[r + 1][col - 1] == 1){
                    uf.union(numberOf(r, col - 1), numberOf(r + 1, col - 1));
                }
            }
        }
        return uf;
    }

    private int count(int[] dart, UnionFind uf) {

        int fallAmount = 0;

        if (grid[dart[0]][dart[1]] == 0) {
            return 0;
        }

        int root = uf.find(numberOf(dart[0], dart[1]));
        if (root < grid.length) {
            int countChildren = 0;
            for (int i = dart[0]; i < row; i++) {
                for (int j = dart[1]; j < col; j++) {
                    if (uf.connected(numberOf(i, j), numberOf(i+1)))
                }
            }
        }

    }

}
