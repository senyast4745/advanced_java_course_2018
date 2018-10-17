import java.util.stream.IntStream;

public class CustomString implements CharSequence {
    private char[][] ch;
    private int offset;
    private int count;
    private int lengthChunk = 0;

    private int isPrimeNumbers (int i) {
        int j = (int)Math.sqrt(i);
        for (; j > 0; j--) {
            if (i/j == 0) {
                return j;
            }
        }
        return 1;
    }

    public CustomString(char[][] ch, int offset, int count, int lengthChunk) {
        this.ch = ch;
        this.offset = offset;
        this.count = count;
        this.lengthChunk = lengthChunk;
    }

    public CustomString(String value) {
        offset = 0;
        count = value.length();
        if(count < 4){
            lengthChunk = count;

        } else {
            int tmp = isPrimeNumbers(count);
            if (tmp == 1) {
                lengthChunk = tmp;
            } else {
                lengthChunk = isPrimeNumbers(count + 1);
            }
        }
        int amountChunk = count / lengthChunk;
        ch = new char[amountChunk][lengthChunk];
        for (int i = 0, m = 0; i < amountChunk; i++) {
            for (int j = 0; (j < lengthChunk) && (m < count); j++) {
                ch[i][j] = value.charAt(m);
                m++;
            }
        }
    }

    @Override
    public int length() {
        return count;
    }

    @Override
    public char charAt(int index) {
        if ((offset + index >= count) || (offset + index < count)) {
            throw new NullPointerException();
        }
        int chunk = (offset + index) / lengthChunk;
        int in = (offset + index) % lengthChunk;
        return ch[chunk][in];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }

    public CustomString subString(int start, int end) {
        if ((end >= count) || (start < offset) || (start > end)) {
            throw new NullPointerException();
        }
        int tmpLen = ((offset + end - start)/lengthChunk)-((offset + start) / lengthChunk);
        char [][] copArray =  new char[tmpLen][];
        for(int i = 0 ; i  < tmpLen; i++){
            copArray[i] = ch[(offset + start) / lengthChunk + i];
        }
        return new CustomString(copArray, offset + start, offset + end - start + 1, lengthChunk);
    }

    public String toString() {
        StringBuffer res = new StringBuffer();
        //int amountChunk = count/ lengthChunk;
        int i = offset / lengthChunk;
        for (int j = offset % lengthChunk; j < count; j++) {
            if (j == lengthChunk) {
                i++;
            }
            res.append(ch[i][j % lengthChunk]);

        }
        return res.toString();
    }

    @Override
    public IntStream chars() { return null; }

    @Override
    public IntStream codePoints() {
        return null;
    }

}