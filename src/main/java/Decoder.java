class Decoder {
    String[] decode(byte[] bytes) {
        String[] decodedValue = new String[2];
        StringBuilder manufacturerHex = new StringBuilder();
        StringBuilder serial = new StringBuilder();
        for (int i = bytes.length - 1; i >= 2; i--) {
            serial.append(byteToHex(bytes[i]));
        }
        System.out.println(serial);
        decodedValue[0] = serial.toString();

        for (int i = 1; i >= 0; i--) {
            manufacturerHex.append(byteToHex(bytes[i]));
        }
        int manufacturer = Integer.parseInt(manufacturerHex.toString(), 16);
        String first = Character.toString((char) ((manufacturer >> 10) + 64));
        String second = Character.toString((char) (((manufacturer & 0b1111100000) >> 5) + 64));
        String third = Character.toString((char) ((manufacturer & 0b11111) + 64));
        String manufacturerCode = first + second + third;
        System.out.println(manufacturerCode);
        decodedValue[1] = manufacturerCode;
        return decodedValue;
    }

    private String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }
}
