package dan.code.test;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

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

        return parse(ImmutableList.<String>of(), line);
    }

    @Nonnull
    Set<List<String>> parse(@Nonnull List<String> alreadyFoundWords, @Nonnull String line) {
        Preconditions.checkNotNull(alreadyFoundWords, "The 'alreadyFoundWords' must not be null!");
        Preconditions.checkNotNull(line, "The line must exist!");
        Preconditions.checkArgument(!line.isEmpty(), "The line must not be empty!");

        final ImmutableSet.Builder<List<String>> resultBuilder = ImmutableSet.builder();

        final int startIndex = 0;
        final int lastIndex = line.length();

        for(int currentIndex = 0; currentIndex <= lastIndex; currentIndex++) {
            String suspect = line.substring(startIndex, currentIndex);
            if (dictionary.contains(suspect)) {
                final List<String> newVariant = ImmutableList.<String>builder()
                        .addAll(alreadyFoundWords)
                        .add(suspect)
                        .build();

                final String theRestOfTheLine = line.substring(currentIndex, lastIndex);

                final boolean shallWeTryAnotherVariants = !theRestOfTheLine.isEmpty();
                if (shallWeTryAnotherVariants) {
                    final Set<List<String>> otherVariants = parse(newVariant, theRestOfTheLine);
                    resultBuilder.addAll(otherVariants);
                } else {
                    resultBuilder.add(newVariant);
                }
            }
        }

        return resultBuilder.build();
    }
}
