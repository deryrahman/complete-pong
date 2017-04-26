package models.centerboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit for unit test. Testing CenterArea class
 * @author Abdurrahman <13515024@std.stei.itb.ac.id>
 * @version 1.0
 * @since 1.0
 */
public class CenterAreaTest {

    /**
     * initialize center area
     */
    CenterArea centerArea= new CenterArea(10,10);

    /**
     * method test
     */
    @Test
    public void generalTest() {
        assertTrue(centerArea.getWidth()==10);
        assertTrue(centerArea.getHeight()==10);
    }
}