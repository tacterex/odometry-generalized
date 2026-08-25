import odometry.abc.EncoderABC;

// to be clarified
final class EncoderTest extends EncoderABC {
    public EncoderTest(){
        super(0, 4096);
    }

    @Override
    protected int read_value(){
        return 1;
    }
}

public class Main {
    // test code to be added
}