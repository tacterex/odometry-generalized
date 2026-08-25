package odometry.abc;

public abstract class OdometryABC {
    private EncoderABC enc1, enc2;
    private IMUABC imu;

    private final float TO_CM_MULTIPLIER;
    private float dx_relative, dy_relative, dx_absolute, dy_absolute, w, dt, dphi, s, c;
    private float[] pos_buffer;
    private float x, y, phi;

    private long current_time, last_time;

    public OdometryABC(
        EncoderABC _enc1,
        EncoderABC _enc2,
        IMUABC _imu, 
        float wheel_d,
        float[] _pos_buffer
    ) {
        enc1 = _enc1;
        enc2 = _enc2;
        imu = _imu;
        pos_buffer = _pos_buffer;

        TO_CM_MULTIPLIER = wheel_d / 2;
        current_time = last_time = System.currentTimeMillis();

        x = y = phi = 0;
    }

    public final void reset() {
        x = y = phi = 0;
        enc1.reset();
        enc2.reset();
        current_time = last_time = System.currentTimeMillis();
    }

    public final void update_all(){
        dx_relative = enc1.get_step_radians() * TO_CM_MULTIPLIER;
        dy_relative = enc2.get_step_radians() * TO_CM_MULTIPLIER;

        w = imu.get_current_velocity();
        current_time = System.currentTimeMillis();
        dt = (current_time - last_time) / 1000.0f;

        dphi = w * dt;
        phi += dphi;

        s = (float)Math.sin(phi);
        c = (float)Math.cos(phi);

        dx_absolute = dx_relative * c - dy_relative * s;
        dy_absolute = dx_relative * s + dy_relative * c;

        x += dx_absolute;
        y += dy_absolute;

        pos_buffer[0] = x;
        pos_buffer[1] = y;
        pos_buffer[2] = phi;
    }

    public final float get_x() { return x; }
    public final float get_y() { return y; }
    public final float get_phi() { return phi; }
}
