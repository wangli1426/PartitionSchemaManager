package edu.illinois.adsc;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Rectangle;
import rx.Observable;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by robert on 23/11/16.
 */
public class PartitionSchemaManager {

    // We relay on a R Tree implementation to store the fileMetaData
    private RTree<FileMetaData, Rectangle> tree = RTree.create();


    // Add a FileMetaData to the manager
    public void add(FileMetaData fileMetaData) {
        tree = tree.add(fileMetaData, Geometries.rectangle(fileMetaData.keyRangeLowerBound, fileMetaData.startTime,
                fileMetaData.keyRangeUpperBound, fileMetaData.endTime));
    }

    // Retrieve the set of files for a given key range and time duration
    public ArrayList<FileMetaData> search(double keyRangeLowerBound, double keyRangeUpperBound, long startTime,
                                          long endTime) {
        ArrayList<FileMetaData> ret = new ArrayList<FileMetaData>();
        Observable<Entry<FileMetaData, Rectangle>> result = tree.search(Geometries.rectangle(keyRangeLowerBound,
                startTime, keyRangeUpperBound, endTime));


        for (Entry<FileMetaData, Rectangle> e : result.toBlocking().toIterable()) {
            ret.add(e.value());
        }

        return ret;
    }

    //Retrieve the set of files for a given key range
    public ArrayList<FileMetaData> keyRangedSearch(double keyRangeLowerBound, double keyRangeUpperBound) {
        return search(keyRangeLowerBound, keyRangeUpperBound, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    //Retrieve the set of files for a given time duration
    public ArrayList<FileMetaData> timeRangedSearch(long startTime, long endTime) {
        return search(Double.MIN_VALUE, Double.MAX_VALUE, startTime, endTime);
    }

    public static void main(String[] args) {
        PartitionSchemaManager manager = new PartitionSchemaManager();
        //We add a file whose name is filename, key range is 100 ~ 200, time is 50000 ~ 70000
        manager.add(new FileMetaData("Filename", 100, 200, 50000, 70000));

        //We retrieve the set of files who might have data falling in the given key range and time duration.
        ArrayList<FileMetaData> files = manager.search(150, 160, 60000, 80000);
    }
}
