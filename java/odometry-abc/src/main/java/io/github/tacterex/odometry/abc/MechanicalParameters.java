package io.github.tacterex.odometry.abc;

public class MechanicalParameters {
    static protected float WHEEL_DIAMETER_MM = 50.0f;

    static void set_wheel_diameter(float d) {
        WHEEL_DIAMETER_MM = d;
    }
}
