package solutions.shitops.monoid;

public class MonoidAssertions {
    public static <L, R> EitherAssert<L, R> assertThat(Either<L, R> actual) {
        return new EitherAssert<>(actual);
    }
}
