package edu.illinois.adsc;

/**
 * This class contains the basic information of a chunk file.
 * One can add as many new fields as needed.
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
