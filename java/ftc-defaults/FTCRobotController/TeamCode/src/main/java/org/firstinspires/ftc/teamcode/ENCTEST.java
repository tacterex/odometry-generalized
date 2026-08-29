package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;

@TeleOp
public class ENCTEST extends LinearOpMode {
    private AS5600 as5600;
    private AnalogInput encx;

    @Override
    public void runOpMode() {
        encx = hardwareMap.get(AnalogInput.class, "encx");
        as5600 = new AS5600(encx);
        as5600.reset();

        waitForStart();
        while(opModeIsActive()) {
            as5600.update_all();
            telemetry.addData("Pos", as5600.get_current_degrees());
            telemetry.update();
        }
    }
}
