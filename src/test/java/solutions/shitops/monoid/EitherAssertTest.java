package solutions.shitops.monoid;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;

class EitherAssertTest implements WithAssertions {

    private final Either<String, Integer> left = Either.left("Hello, World!");
    private final Either<String, Integer> right = Either.right(17);

    @Test
    void isLeftFailsIfRight() {
        assertThatExceptionOfType(AssertionError.class)
                  .isThrownBy(() -> MonoidAssertions.assertThat(right).isLeft())
                  .withMessage("Expected Left, was <Right 17>.");
    }

    @Test
    void isRightFailsIfLeft() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> MonoidAssertions.assertThat(left).isRight())
                .withMessage("Expected Right, was <Left Hello, World!>.");
    }

    @Test
    void isLeftSuccessIfLeft() {
        assertThatCode(() -> MonoidAssertions.assertThat(left).isLeft())
                .doesNotThrowAnyException();
    }

    @Test
    void isRightSuccessIfRight() {
        assertThatCode(() -> MonoidAssertions.assertThat(right).isRight())
                .doesNotThrowAnyException();
    }

    @Test
    void hasLeftFailsIfDifferentValue() {
       assertThatExceptionOfType(AssertionError.class)
               .isThrownBy(() -> MonoidAssertions.assertThat(left).hasLeft("Goodbye, World!"))
               .withMessage("Expected <Left Goodbye, World!>, was <Left Hello, World!>.");
    }

    @Test
    void hasRightFailsIfDifferentValue() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> MonoidAssertions.assertThat(right).hasRight(19))
                .withMessage("Expected <Right 19>, was <Right 17>.");
    }

    @Test
    void hasLeftFailsIfRight() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> MonoidAssertions.assertThat(right).hasLeft("Hello, World!"))
                .withMessage("Expected <Left Hello, World!>, was <Right 17>.");
    }

    @Test
    void hasRightFailsIfLeft() {
        assertThatExceptionOfType(AssertionError.class)
                .isThrownBy(() -> MonoidAssertions.assertThat(left).hasRight(17))
                .withMessage("Expected <Right 17>, was <Left Hello, World!>.");
    }

    @Test
    void hasLeftSucceedsIfCorrect() {
        assertThatCode(() -> MonoidAssertions.assertThat(left).hasLeft("Hello, World!"))
                .doesNotThrowAnyException();
    }

    @Test
    void hasRightSucceedsIfCorrect() {
        assertThatCode(() -> MonoidAssertions.assertThat(right).hasRight(17))
                .doesNotThrowAnyException();
    }
}
