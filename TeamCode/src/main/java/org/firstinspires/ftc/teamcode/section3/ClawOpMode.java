package org.firstinspires.ftc.teamcode.section3;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Basic: Claw mode", group = "Iterative Opmode")
public class ClawOpMode extends OpMode {
    Servo claw = null;
    CRServo arm = null;
    double position = 0.50;

    @Override
    public void init() {
        claw = hardwareMap.get(Servo.class, "claw");
        arm = hardwareMap.get(CRServo.class, "arm");
    }

    @Override
    public void loop() {
        if (gamepad1.b) {
            position += 0.01;
        } else if (gamepad1.x) {
            position -= 0.01;
        }
        telemetry.addData("Position", position);
        claw.setPosition(position);
        position = Range.clip(position, 0, 1);
        if (gamepad1.a) {
            arm.setPower(-0.2);
        } else if (gamepad1.y) {
            arm.setPower(0.2);
        } else {
            arm.setPower(0);
        }
    }


}
