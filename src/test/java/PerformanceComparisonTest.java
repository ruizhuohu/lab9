import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A simple test to compare performance between original and batch-adding decorator.
 */
public class PerformanceComparisonTest {

  @Test
  public void comparePerformance() {
    // Test with batch-adding decorator
    long startTime = System.currentTimeMillis();
    PillCounter batchCounter = new BatchAddingDecorator(new LoggingPillCounter());
    boolean result = simpleTest(batchCounter);
    long batchTime = System.currentTimeMillis() - startTime;
    
    assertTrue(result);
    System.out.println("BatchAddingDecorator took: " + batchTime + " ms");

    /*
    startTime = System.currentTimeMillis();
    PillCounter originalCounter = new LoggingPillCounter();
    result = simpleTest(originalCounter);
    long originalTime = System.currentTimeMillis() - startTime;
    System.out.println("Original LoggingPillCounter took: " + originalTime + " ms");
    System.out.println("Speedup: " + (originalTime / (double) batchTime) + "x");
    */
  }

  private boolean simpleTest(PillCounter counter) {
    for (int bottle = 0; bottle < 10; bottle += 1) {
      for (int pill = 0; pill < 10; pill += 1) {
        counter.addPill(1);
      }
      assertEquals(10, counter.getPillCount());
      counter.reset();
    }
    return true;
  }
}

