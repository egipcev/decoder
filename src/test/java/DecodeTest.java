import org.junit.Assert;
import org.junit.Test;

public class DecodeTest {

    @Test
    public void testDecode() {
        Decoder decoder = new Decoder();
        byte[] bytes = {(byte) 0xa5, 0x11, 0x06, 0x05, 0x60, 0x70};
        final String manufacturer = "DME";
        final String serial = "70600506";

        String[] decodedValue = decoder.decode(bytes);
        Assert.assertEquals(serial, decodedValue[0]);
        Assert.assertEquals(manufacturer, decodedValue[1]);

    }

    @Test
    public void testDecodeErrorCase() {
        Decoder decoder = new Decoder();
        byte[] bytes = {(byte) 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        final String serial = "00000000";

        String[] decodedValue = decoder.decode(bytes);
        Assert.assertEquals(serial, decodedValue[0]);
        Assert.assertFalse("Manufacturer code consist of 3 A-Z symbols", decodedValue[1].matches("[A-Z][A-Z][A-Z]"));
    }
}
