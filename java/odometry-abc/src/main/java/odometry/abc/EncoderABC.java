package odometry.abc;

public abstract class EncoderABC {
    private final int min_possible_position;
    private int current_position, current_step;
    private int current_reading, last_reading, raw_step;
    private final int amplitude;

    private final float TO_DEGREE_MULTIPLIER;
    private final float TO_RAD_MULTIPLIER;

    abstract protected int read_value();

    public EncoderABC(int left_bound, int right_bound){
        min_possible_position = left_bound;
        amplitude = (right_bound - left_bound) / 2;
        reset();
        TO_DEGREE_MULTIPLIER = 360.0f / (right_bound - left_bound);
        TO_RAD_MULTIPLIER = 2 * (float)Math.PI / (right_bound - left_bound);
    }

    public final void update_all() {
        current_reading = read_value();
        raw_step = current_reading - last_reading;
        if (raw_step < -amplitude)
            raw_step += 2 * amplitude;
        if(raw_step > amplitude)
            raw_step -= 2 * amplitude;

        current_step = raw_step;
        current_position += raw_step;
        last_reading = current_reading;
    }

    public final int get_current_position() {
        return current_position;
    }

    public final int get_current_step() {
        return current_step;
    }

    public final float get_current_degrees() {
        return (current_position - min_possible_position) * TO_DEGREE_MULTIPLIER;
    }

    public final float get_current_radians() {
        return (current_position - min_possible_position) * TO_RAD_MULTIPLIER;
    }

    public final float get_step_degrees() {
        return current_step * TO_DEGREE_MULTIPLIER;
    }

    public final float get_step_radians() {
        return current_step * TO_RAD_MULTIPLIER;
    }

    public final void reset() {
        current_position = min_possible_position;
        current_reading = last_reading = min_possible_position;
        current_step = raw_step = 0;
    }
}
