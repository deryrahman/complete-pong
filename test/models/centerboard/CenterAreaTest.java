package models.centerboard;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Rahman on 4/27/2017.
 */
public class CenterAreaTest {
    int _width= 320; int _height= 200;
    CenterArea centerArea= new CenterArea(_width,_height);
    @Test
    public void constructorTest() {
        assertTrue(centerArea.getWidth()==320);
        assertTrue(centerArea.getHeight()==200);
        assertTrue(centerArea.getMatrixWidth()==16);
        assertTrue(centerArea.getMatrixHeight()==5);
    }
    @Test
    public void generalTest() {
        centerArea.destroyCell(2,2);
        assertTrue(centerArea.getCell(2,2)==null);
    }
}