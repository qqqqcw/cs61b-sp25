import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(ad1.toList()).containsExactly("back").inOrder();

        ad1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(ad1.toList()).containsExactly("middle", "back").inOrder();

        ad1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        ad1.addLast(0);   // [0]
        ad1.addLast(1);   // [0, 1]
        ad1.addFirst(-1); // [-1, 0, 1]
        ad1.addLast(2);   // [-1, 0, 1, 2]
        ad1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(ad1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for ArrayDeque61B.
    @Test
    /** 测试isEmpty **/
    public void isEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        assertThat(ad1.isEmpty()).isTrue();
        ad1.addFirst(1);
        assertThat(ad1.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addLast(2);
        ad1.addFirst(4);
        assertThat(ad1.size()).isEqualTo(3);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        assertThat(ad1.get(1)).isNull();
        ad1.addFirst(10);
        ad1.addLast(20);
        ad1.addLast(30);
        assertThat(ad1.get(0)).isEqualTo(10);
        assertThat(ad1.get(3)).isNull();

    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.removeFirst();
        assertThat(ad1.toList()).isEmpty();
        assertThat(ad1.removeFirst()).isNull();
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.removeFirst();
        assertThat(ad1.toList()).containsExactly(2, 4).inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.removeLast();
        assertThat(ad1.toList()).isEmpty();
        assertThat(ad1.removeLast()).isNull();
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.addLast(4);
        ad1.removeLast();
        assertThat(ad1.toList()).containsExactly(3, 2).inOrder();
    }
}
