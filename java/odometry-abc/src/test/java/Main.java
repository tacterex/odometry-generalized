import io.github.tacterex.odometry.abc.EncoderABC;

// to be clarified
final class EncoderTest extends EncoderABC {
    public EncoderTest(){
        super(4096);
    }

    @Override
    protected float read_value(){
        return 1;
    }
}

public class Main {
    // test code to be added
}