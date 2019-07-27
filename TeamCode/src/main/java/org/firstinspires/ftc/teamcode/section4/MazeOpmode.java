package org.firstinspires.ftc.teamcode.section4;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name = "Maze", group = "Iterative Opmode")
public class MazeOpmode extends LinearOpMode {
    private DcMotor leftFront = null;
    private DcMotor rightRear = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DistanceSensor sensor = null;
    static double ticksPerRotation = 537.6;
    static double distance = 36;
    static double diameter = 4;
    static double distance2 = 20;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initializing");
        sensor = hardwareMap.get(DistanceSensor.class, "frontSensor");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightRear.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Status", "Initialized");


        waitForStart();
        distance = 36;
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setTargetPosition(getTicks());
        leftFront.setTargetPosition(getTicks());
        rightFront.setTargetPosition(getTicks());
        leftRear.setTargetPosition(getTicks());
        leftFront.setPower(.4);
        leftRear.setPower(.4);
        rightFront.setPower(.4);
        rightRear.setPower(.4);
        telemetry.addLine("goingfoward");
        telemetry.update();
        while (rightRear.isBusy() || leftFront.isBusy() || rightFront.isBusy() || leftRear.isBusy()) {

        }
        resetWheels();
        distance = 24;
        rightRear.setTargetPosition(getTicks());
        leftFront.setTargetPosition(getTicks());
        rightFront.setTargetPosition(-getTicks());
        leftRear.setTargetPosition(-getTicks());
        leftFront.setPower(.4);
        leftRear.setPower(-.4);
        rightFront.setPower(-.4);
        rightRear.setPower(.4);
        telemetry.addLine("goingright");
        telemetry.update();
        while (rightRear.isBusy() || leftFront.isBusy() || rightFront.isBusy() || leftRear.isBusy()) {

        }
        resetWheels();
        distance = 72;
        rightRear.setTargetPosition(getTicks());
        leftFront.setTargetPosition(getTicks());
        rightFront.setTargetPosition(getTicks());
        leftRear.setTargetPosition(getTicks());
        leftFront.setPower(.4);
        leftRear.setPower(.4);
        rightFront.setPower(.4);
        rightRear.setPower(.4);
        telemetry.addLine("goingfoward again");
        telemetry.update();
        while (distance2 > 2) {
            distance2 = sensor.getDistance(DistanceUnit.INCH);
            if (distance2 < 2) {
                rightRear.setPower(0);
                leftFront.setPower(0);
                rightFront.setPower(0);
                leftRear.setPower(0);
            } else if (distance2 < 10) {
                rightRear.setPower(0.2);
                leftFront.setPower(0.2);
                rightFront.setPower(0.2);
                leftRear.setPower(0.2);
            }
        }


    }

    public void resetWheels() {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    static public int getTicks() {
        return (int) (distance / (diameter * Math.PI) * ticksPerRotation * Math.sqrt(2) / 2);
    }
}