package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;

import io.github.tacterex.odometry.abc.EncoderABC;

public class AS5600 extends EncoderABC{
    final private AnalogInput sensor;
    public AS5600(AnalogInput _sensor){
        super(3.3f);
        sensor = _sensor;
    }
    @Override
    protected float read_value() {
        if (sensor == null) return 0;
        return (float)sensor.getVoltage();
    }
}
