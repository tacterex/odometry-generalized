package odometry.abc;

public abstract class IMUABC {
    private final int sensivity, max_velocity;
    private int current_reading;

    private final float TO_RPS_MULTIPLIER;

    abstract protected int read_value();

    public IMUABC(int _sensivity, int _max_velocity){
        sensivity = _sensivity;
        max_velocity = _max_velocity;
        TO_RPS_MULTIPLIER = 1.0f * _max_velocity / _sensivity;
    }

    public final void update_all() {
        current_reading = read_value();
    }

    public final int get_sensivity(){
        return sensivity;
    }

    public final int get_max_velocity(){
        return max_velocity;
    }

    public final float get_current_velocity() {
        return current_reading * TO_RPS_MULTIPLIER;
    }
}
