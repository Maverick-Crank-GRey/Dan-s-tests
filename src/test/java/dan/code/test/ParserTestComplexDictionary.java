package dan.code.test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


/**
 * Created by maverick on 7/7/14.
 */
public class ParserTestComplexDictionary {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        parser = new Parser(ImmutableSet.of("a", "b", "c", "ab", "bc", "abc"));
    }

    @Test
    public void testParse_twoVariants() throws Exception {
        final Set<List<String>> lines = parser.parse("ab");

        Assert.assertThat(lines, containsInAnyOrder((List<String>)
                ImmutableList.of("a", "b"),
                ImmutableList.of("ab")));
    }

    @Test
    public void testParse_threeVariants() throws Exception {
        final Set<List<String>> lines = parser.parse("abc");

        Assert.assertThat(lines, containsInAnyOrder((List<String>)
                ImmutableList.of("a", "b", "c"),
                ImmutableList.of("ab", "c"),
                ImmutableList.of("a", "bc"),
                ImmutableList.of("abc")
        ));
    }
}
