package boofcv.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/*
 this class holds the coordinates of each point
 it is useful in cases of loading points into file for two option:
 - measuring distance.
 - ego motion.
 */
public class XYZPoint {
    private double x, y, z;

    // constructor
    public XYZPoint(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // get real coordinate x (in meter)
    public double getX() {
        return this.x;
    }

    // get real coordinate y (in meter)
    public double getY() {
        return this.y;
    }

    // get real coordinate Z (in meter)
    public double getZ() {
        return this.z;
    }

    // set real coordinate x (in meter)
    public void setX(double x) {
        this.x = x;
    }

    // set real coordinate y (in meter)
    public void setY(double y) {
        this.y = y;
    }

    // set real coordinate Z (in meter)
    public void setZ(double z) {
        this.z = z;
    }

    // show the string
    public String toString() {
        return "( " + this.x + " , " + this.y + " , " + this.z + " )";
    }

    // write to file
    public static void writeXYZFormat(List<XYZPoint> XYZ, String whereCreateFile, String nameOfFile) {

        try {

            File file = new File(whereCreateFile + nameOfFile + ".asc");

            // if file doesn't exists, then create new one
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileWriter fw = new FileWriter(file.getAbsoluteFile(), true)) {
                try (BufferedWriter bw = new BufferedWriter(fw)) {

                    double x, y, z;
                    for (XYZPoint xyzPoint : XYZ) {

                        x = xyzPoint.getX();
                        y = xyzPoint.getY();
                        z = xyzPoint.getZ();

                        x = Math.floor(x * 1000) / 1000;
                        y = Math.floor(y * 1000) / 1000;
                        z = Math.floor(z * 1000) / 1000;

                        bw.write(x + ",\t" + y + ",\t" + z + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}