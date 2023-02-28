package solutions.shitops.monoid;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class EitherTest {

    @Test
    void foldOfLeftReturnLeftMapped() {
        String expectedError = "Path is null.";
        Either<IOException, File> either = Either.left(new IOException(expectedError));
        String str = either.fold(
                Throwable::getMessage,
                (_file) -> "Should never happen...");

        assertThat(str).isEqualTo(expectedError);
    }

    @Test
    void foldOfRightReturnRightMapped() {
        String expectedFile = "file.ext";
        Either<IOException, File> either = Either.right(new File(expectedFile));
        String str = either.fold(
                (_e) -> "Should never happen...",
                File::getName);

        assertThat(str).isEqualTo(expectedFile);
    }

    @Test
    void mapOfLeftMaintainsValue() {
        Either<IOException, File> either = Either.left(new IOException("Path is null."));
        String str = either
                .map((File::isFile))
                .fold((e) -> "Success",
                      (f) -> "Should never happen...");

        assertThat(str).isEqualTo("Success");
    }

    @Test
    void mapOfLeftChangesType() {
        Either<IOException, File> either = Either.left(new IOException("Path is null."));
        assertThat(either.map((File::isFile)) instanceof Either<IOException, Boolean>)
                .isTrue();
    }

    @Test
    void mapOfRightChangesValue() {
        Either<IOException, String> either = Either.right("1337");
        int number = either
                .map(Integer::parseInt)
                .fold((e) -> 0, (n) -> n);

        assertThat(number).isEqualTo(1337);
    }
}