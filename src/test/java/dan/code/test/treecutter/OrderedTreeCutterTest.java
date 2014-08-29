package dan.code.test.treecutter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maverick on 8/22/14.
 */
public class OrderedTreeCutterTest {
    private Cutter tested;

    @Before
    public void setUp() throws Exception {
        //    0[0]
        //    1[1]
        //    └── 2[1]
        //    3[2]
        //    4[4]
        //    ├── 5[4]
        //    │   └── 6[5]
        //    └── 7[4]
        //        └── 8[7]
        //            └── 9[8]
        //                ├── 10[9]
        //                └── 11[9]

        //                     0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        int[] tree = new int[]{0, 1, 1, 3, 4, 4, 5, 4, 7, 8,  9,  9};
        tested = new OrderedTreeCutter(tree);
    }

    @Test
    public void nothingToCut() throws Exception {
        Assert.assertArrayEquals(
                new int[]{0, 1, 1, 3, 4, 4, 5, 4, 7, 8, 9, 9},
                tested.cut(new int[]{}));
    }

    @Test
    public void cutShortBranches() throws Exception {
        Assert.assertArrayEquals(
                new int[]{1, 1, 4, 4, 5, 4, 7, 8, 9, 9},
                tested.cut(new int[]{0, 3}));
    }

    @Test
    public void cutASmallBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{0, 3, 4, 4, 5, 4, 7, 8,  9,  9},
                tested.cut(new int[]{1}));
    }

    @Test
    public void cutALongBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{0, 1, 1, 3},
                tested.cut(new int[]{4}));
    }

    @Test
    public void cutASubBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{0, 1, 1, 3, 4, 4, 7, 8, 9, 9},
                tested.cut(new int[]{5}));
    }

    @Test
    public void cutABigSubBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{0, 1, 1, 3, 4, 4, 5},
                tested.cut(new int[]{7}));
    }
}
