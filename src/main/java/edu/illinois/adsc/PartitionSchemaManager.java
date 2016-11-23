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
    private RTree<FileMetaData, Rectangle> tree = RTree.create();

    public void add(FileMetaData fileMetaData) {
        tree = tree.add(fileMetaData, Geometries.rectangle(fileMetaData.keyRangeLowerBound, fileMetaData.startTime,
                fileMetaData.keyRangeUpperBound, fileMetaData.endTime));
    }

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

    public ArrayList<FileMetaData> keyRangedSearch(double keyRangeLowerBound, double keyRangeUpperBound) {
        return search(keyRangeLowerBound, keyRangeUpperBound, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public ArrayList<FileMetaData> timeRangedSearch(long startTime, long endTime) {
        return search(Double.MIN_VALUE, Double.MAX_VALUE, startTime, endTime);
    }
}
