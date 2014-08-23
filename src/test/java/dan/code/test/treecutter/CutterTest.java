package dan.code.test.treecutter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by maverick on 8/22/14.
 */
public class CutterTest {
    private Cutter tested;

    @Before
    public void setUp() throws Exception {
        //    1[1]
        //    └── 0[1]
        //        ├── 2[0]
        //        └── 3[0]
        //    5[5]
        //    └── 4[4]
        //    6[6]
        //    7[7]
        //    └── 8[7]
        //        └── 9[8]
        //            ├── 10[9]
        //            └── 11[9]

        //                     0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
        int[] tree = new int[]{1, 1, 0, 0, 5, 5, 6, 7, 7, 8,  9,  9};
        tested = new CycleCutter(tree);
    }

    @Test
    public void nothingToCut() throws Exception {
        Assert.assertArrayEquals(
                new int[]{1, 1, 0, 0, 5, 5, 6, 7, 7, 8, 9, 9},
                tested.cut(new int[]{}));
    }

    @Test
    public void cutAShortBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{1, 1, 0, 0, 5, 5, 7, 7, 8, 9, 9},
                tested.cut(new int[]{6}));
    }

    @Test
    public void cutASmallBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{1, 1, 0, 0, 6, 7, 7, 8, 9, 9},
                tested.cut(new int[]{5}));
    }

    @Test
    public void cutALongBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{5, 5, 6, 7, 7, 8, 9, 9},
                tested.cut(new int[]{1}));
    }

    @Test
    public void cutASubBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{1, 5, 5, 6, 7, 7, 8, 9, 9},
                tested.cut(new int[]{0}));
    }

    @Test
    public void cutABigSubBranch() throws Exception {
        Assert.assertArrayEquals(
                new int[]{1, 1, 0, 0, 5, 5, 6, 7},
                tested.cut(new int[]{8}));
    }
}
