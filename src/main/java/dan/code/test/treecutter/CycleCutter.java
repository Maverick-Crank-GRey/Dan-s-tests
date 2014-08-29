package dan.code.test.treecutter;

import com.google.common.collect.ImmutableSet;

/**
 * Created by maverick on 8/22/14.
 */
public class CycleCutter extends DumbCutter implements Cutter {

    /**
     * Instantiate a class with a tree structure encoded as an array.
     * The array entries contains indexes of parent entry and so forth.
     *
     * @param tree The tree encoded as an array.
     */
    public CycleCutter(int[] tree) {
        super(tree);
    }

    @Override
    public int[] cut(int[] condemnedIndexes) {
        final ImmutableSet.Builder<Integer> builder = ImmutableSet.builder();
        for (int condemnedIndex : condemnedIndexes) {
            builder.add(condemnedIndex);
        }

        int numberOfDeadElements = markDeadBranches(tree, builder.build());
        final int newTreeSize = tree.length - numberOfDeadElements;
        return createNewTree(newTreeSize);
    }

    static int markDeadBranches(int[] tree, ImmutableSet<Integer> condemnedIndexes) {
        int numberOfDeadElements = 0;
        while (!condemnedIndexes.isEmpty()) {
            final ImmutableSet.Builder<Integer> builderOfNextCondemned = ImmutableSet.builder();
            for (int condemned : condemnedIndexes) {
                for (int i = 0; i < tree.length; i++) {
                    int next = tree[i];
                    if (next == condemned && next != i) {
                        builderOfNextCondemned.add(i);
                    }
                }
                tree[condemned] = DEAD_INDEX;
                numberOfDeadElements++;
            }

            condemnedIndexes = builderOfNextCondemned.build();
        }

        return numberOfDeadElements;
    }
}
