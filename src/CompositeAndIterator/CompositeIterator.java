package CompositeAndIterator;

import java.util.Iterator;
import java.util.Stack;

public class CompositeIterator implements Iterator<Hardware> { // Implement Iterator<Hardware> here
    private final Stack<Iterator<Hardware>> stack = new Stack<>();

    public CompositeIterator(Iterator<Hardware> iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            Iterator<Hardware> iterator = stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public Hardware next() {
        if (hasNext()) {
            Iterator<Hardware> iterator = stack.peek();
            Hardware hardware = iterator.next();
            if (hardware instanceof HardwareStock) {
                stack.push(hardware.createIterator()); // This will work because HardwareStock implements createIterator
            }
            return hardware;
        } else {
            return null;
    }

}
}
