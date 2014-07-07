package dan.code.test;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;

/**
 * Created by maverick on 7/7/14.
 */
public class Parser {
    private final Set<String> dictionary;

    public Parser(ImmutableSet<String> dictionary) {
        this.dictionary = dictionary;
    }

    @Nonnull
    public Set<List<String>> parse(final @Nonnull String line) {
        Preconditions.checkNotNull(line, "The line must exist!");
        if (line.isEmpty()) {
            return ImmutableSet.of();
        }

        Set<List<String>> result = Sets.newHashSet();
        final ImmutableList.Builder<String> builder = ImmutableList.builder();

        int startIndex = 0;
        int currentIndex = 1;
        final int lastIndex = line.length();
        int lastFoundIndex = startIndex;

        while(currentIndex <= lastIndex) {
            String suspect = line.substring(startIndex, currentIndex);
            if (dictionary.contains(suspect)) {
                lastFoundIndex = currentIndex;
                builder.add(suspect);
                startIndex = currentIndex;
            }

            currentIndex++;
        }

        final boolean lastFoundWordIsEndedTheLine = lastFoundIndex == lastIndex;
        if (lastFoundWordIsEndedTheLine) {
            result.add(builder.build());
        }
        return result;
    }
}
