package dev.unexist.showcase.todo;

import dev.unexist.showcase.todo.application.TodoResourceTest;
import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeTodoResourceIT extends TodoResourceTest {

    // Execute the same tests but in native mode.
}