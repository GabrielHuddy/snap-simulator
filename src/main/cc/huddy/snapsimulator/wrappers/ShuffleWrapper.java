package cc.huddy.snapsimulator.wrappers;

import java.util.Collections;
import java.util.List;

/**
 * Wrapper for the static shuffle function of Java's collections class.
 * Using this wrapper is preferred in order to allow for proper unit testing of dependent classes
 */
public class ShuffleWrapper {
    /**
     * Shuffle a list.
     * @param list the list to shuffle
     */
    public void shuffle(List<?> list) {
        Collections.shuffle(list);
    }
}
