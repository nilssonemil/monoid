package solutions.shitops.monoid;

import org.assertj.core.api.AbstractAssert;

public class EitherAssert<L, R> extends AbstractAssert<EitherAssert<L, R>, Either<L, R>> {
    private final Either<L, R> actual;

    public EitherAssert(Either<L, R> actual) {
        super(actual, EitherAssert.class);
        this.actual = actual;
    }

    public static <L, R> EitherAssert<L, R> assertThat(Either<L, R> actual) {
        return new EitherAssert<>(actual);
    }

    public EitherAssert<L, R> isLeft() {
        boolean failure = actual.fold((l) -> false, (r) -> true);
        if (failure) failWithMessage("Expected Left, was %s.", actual);
        return this;
    }

    public EitherAssert<L, R> isRight() {
        boolean failure = actual.fold((l) -> true, (r) -> false);
        if (failure) failWithMessage("Expected Right, was %s.", actual);
        return this;
    }

    public EitherAssert<L, R> hasLeft(L expectedLeft) {
        boolean failure = !actual.fold(
                (actualLeft) -> actualLeft.equals(expectedLeft),
                (r) -> false);

        if (failure)
            failWithMessage("Expected %s, was %s.",
                            Either.left(expectedLeft),
                            actual);
        return this;
    }

    public EitherAssert<L, R> hasRight(R expectedRight) {
        boolean failure = actual.fold(
                (l) -> true,
                (r) -> !r.equals(expectedRight));

        if (failure)
            failWithMessage("Expected %s, was %s.",
                            Either.right(expectedRight),
                            actual);
        return this;
    }
}
