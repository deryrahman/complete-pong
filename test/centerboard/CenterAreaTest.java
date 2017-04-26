package centerboard;;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/26/2017.
 */
public class CenterAreaTest {
    CenterArea centerArea= new CenterArea(10,10);
    @Test
    public void generalTest() {
        assertTrue(centerArea.getWidth()==10);
        assertTrue(centerArea.getHeight()==10);
    }
}