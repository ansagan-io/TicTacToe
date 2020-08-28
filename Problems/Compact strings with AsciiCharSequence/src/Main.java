import java.util.Arrays;

class AsciiCharSequence implements CharSequence {
    protected byte[] byteArray;

    public AsciiCharSequence(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    @Override
    public int length() {
        return byteArray.length;
    }

    @Override
    public char charAt(int index) {
        return (char) byteArray[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        AsciiCharSequence asciiCharSequence = new AsciiCharSequence(byteArray);
        asciiCharSequence.byteArray = Arrays.copyOfRange(byteArray, start, end);
        return asciiCharSequence;
    }

    public String toString(){
        return new String(byteArray);
    }
}