package pl.lodz.p.zzpj.testing.junit.stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackExerciseTest {

    StackExercise stack;
    String item1 = "first item";
    String item2 = "second item";

    @Before
    public void setUp() {
        stack = new StackExercise();
        stack.push(item1);
    }

    @Test
    public void shouldReturnTopElementAndNotRemoveIt() throws StackEmptyException {
        assertFalse(stack.isEmpty());
        assertEquals(item1, stack.top());
        assertFalse(stack.isEmpty());
    }

    @Test
    public void shouldPopItemFromStack() throws StackEmptyException {
        String result = stack.pop();
        assertEquals(item1, result);
    }

    @Test(expected = StackEmptyException.class)
    public void shouldThrowExceptionOnPopOnEmptyStack() throws StackEmptyException {
        assertEquals(item1, stack.pop());
        stack.pop();
    }

    @Test(expected = StackEmptyException.class)
    public void shouldThrowExceptionOnTopOnEmptyStack() throws StackEmptyException {
        assertEquals(item1, stack.pop());
        stack.top();
    }

    @Test
    public void shouldPushItemToStack() throws StackEmptyException {
        assertFalse(stack.isEmpty());
        stack.pop();

        assertTrue(stack.isEmpty());
        stack.push(item2);

        assertEquals(item2, stack.pop());
    }

    @Test
    public void shouldCheckIfStackIsEmpty() throws StackEmptyException {
        stack.pop();

        assertTrue(stack.isEmpty());
    }

    @Test
    public void shouldCheckIfStackIsNotEmpty() {
        assertFalse(stack.isEmpty());
    }

}
