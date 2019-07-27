package org.firstinspires.ftc.teamcode.section3;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Basic: Sensor mode", group = "Iterative Opmode")
public class SensorOpmode extends OpMode {
    private DcMotor leftFront = null;
    private DcMotor rightRear = null;
    private DcMotor rightFront = null;
    private DcMotor leftRear = null;
    private DistanceSensor sensor = null;
    static double ticksPerRotation = 537.6;
    static double distance = 36*2;
    static double diameter = 4;



    static public int getTicks(){
        return (int) (distance/(diameter*Math.PI)*ticksPerRotation*Math.sqrt(2)/2);
    }


    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");
        sensor = hardwareMap.get(DistanceSensor.class, "frontSensor");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightRear.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftRear.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        double distance = sensor.getDistance(DistanceUnit.INCH);
        telemetry.addData("Distance","%f",  distance);
        float x = gamepad1.left_stick_x;
        float y = -gamepad1.left_stick_y;
        float rightx = gamepad1.right_stick_x;

        if(distance<5){
            if(y>0){
                y=0;
            }
        }else if(distance<30){
            if(y>0) {
                y = (float) (y / (36 - distance));
            }
        }
        leftFront.setPower((-y - x) / 2-rightx);
        rightFront.setPower((y - x) / 2-rightx);
        leftRear.setPower((-y + x) / 2-rightx);
        rightRear.setPower((y + x) / 2-rightx);

        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }
}
