package solutions.shitops.monoid;

import java.util.function.Function;

/**
 * Right-biased Either.
 * @param <L> type of `Left`, typically an error.
 * @param <R> type of `Right`, typically a successful value.
 */
public final class Either <L, R> {
    private final Left<L> left;
    private final Right<R> right;

    private Either(Left<L> left) {
        this.left = left;
        this.right = null;
    }

    private Either(Right<R> right) {
        this.right = right;
        this.left = null;
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<>(new Left<>(left));
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<>(new Right<>(right));
    }

    public <U> U fold(Function<? super L, ? extends U> leftMapper,
                      Function<? super R, ? extends U> rightMapper) {
        return left != null
                ? leftMapper.apply(left.get())
                : rightMapper.apply(right.get());
    }

    public <U> Either<L, U> map(Function<? super R, ? extends U> rightMapper) {
        return left == null
                ? Either.right(rightMapper.apply(right.get()))
                : Either.left(left.get());
    }

    private record Left<T>(T value) {
        private Left {
            if (value == null)
                throw new IllegalArgumentException("Left cannot be null.");
        }
        private T get() { return value; }
    }

    private record Right<T>(T value) {
        private Right {
            if (value == null)
                throw new IllegalArgumentException("Right cannot be null.");
        }
        private T get() { return value; }
    }
}

