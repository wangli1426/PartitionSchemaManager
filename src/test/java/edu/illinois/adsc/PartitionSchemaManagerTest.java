package edu.illinois.adsc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by robert on 23/11/16.
 */
public class PartitionSchemaManagerTest
        extends TestCase {

    public void testInsertAndSearch() {
        PartitionSchemaManager partitionSchemaManager = new PartitionSchemaManager();
        partitionSchemaManager.add(new FileMetaData("file 1", 50, 100, 20000,30000));
        assert(partitionSchemaManager.search(60, 61, 20000, 25000).get(0).filename.equals("file 1") );
    }

    public void testInsertAndPartialSearch() {
        PartitionSchemaManager partitionSchemaManager = new PartitionSchemaManager();
        partitionSchemaManager.add(new FileMetaData("file 1", 50, 100, 20000,30000));
        assert(partitionSchemaManager.search(0, 200, 0, 500000).get(0).filename.equals("file 1") );
    }

    public void testInsertAndOnlyKeyRangeSearch() {
        PartitionSchemaManager partitionSchemaManager = new PartitionSchemaManager();
        partitionSchemaManager.add(new FileMetaData("file 1", 50, 100, 20000,30000));
        assert(partitionSchemaManager.keyRangedSearch(0, 200).get(0).filename.equals("file 1") );
    }

    public void testInsertAndOnlyTimeRangeSearch() {
        PartitionSchemaManager partitionSchemaManager = new PartitionSchemaManager();
        partitionSchemaManager.add(new FileMetaData("file 1", 50, 100, 20000,30000));
        assert(partitionSchemaManager.timeRangedSearch(20000, 25000).get(0).filename.equals("file 1") );
    }

}
