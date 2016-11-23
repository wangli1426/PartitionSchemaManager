package edu.illinois.adsc;

/**
 * Created by robert on 23/11/16.
 */
public class FileMetaData {
    String filename;
    long startTime;
    long endTime;
    double keyRangeLowerBound;
    double keyRangeUpperBound;

    FileMetaData(String filename,  double keyRangeLowerBound, double keyRangeUpperBound, long startTime, long endTime) {
        this.filename = filename;
        this.startTime = startTime;
        this.endTime = endTime;
        this.keyRangeUpperBound = keyRangeUpperBound;
        this.keyRangeLowerBound = keyRangeLowerBound;
    }
}
