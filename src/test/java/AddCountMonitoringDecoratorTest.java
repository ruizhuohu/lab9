import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for AddCountMonitoringDecorator.
 * This test uses the monitoring decorator and prints the add counts
 * to help identify performance issues.
 */
public class AddCountMonitoringDecoratorTest {

  /**
   * Test that uses the monitoring decorator to track add counts.
   * This test prints the list of add counts at the end to show
   * how many times addPill was called before each reset.
   */
  @Test
  public void usage() {
    AddCountMonitoringDecorator counter = 
        new AddCountMonitoringDecorator(new LoggingPillCounter());
    boolean result = conveyerBelt(counter);
    assertTrue(result);

    List<Integer> addCounts = counter.getAddCounts();
    System.out.println("Add counts (number of addPill calls before each reset):");
    System.out.println("Total number of resets: " + addCounts.size());
    System.out.println("Add counts: " + addCounts);

    assertEquals(1600, addCounts.size());

    for (int i = 0; i < 100; i++) {
      assertEquals(100, (int) addCounts.get(i));
    }

    for (int i = 100; i < 1100; i++) {
      assertEquals(5, (int) addCounts.get(i));
    }
    

    for (int i = 1100; i < 1600; i++) {
      assertEquals(100, (int) addCounts.get(i));
    }
  }

  private boolean conveyerBelt(PillCounter counter) {
    for (int bottle = 0; bottle < 100; bottle += 1) {
      for (int pill = 0; pill < 100; pill += 1) {
        counter.addPill(1);
      }
      assertEquals(100, counter.getPillCount());
      counter.reset();
    }

    for (int bottle = 0; bottle < 1000; bottle += 1) {
      for (int pill = 0; pill < 20; pill += 4) {
        counter.addPill(4);
      }
      assertEquals(20, counter.getPillCount());
      counter.reset();
    }

    for (int bottle = 0; bottle < 500; bottle += 1) {
      for (int pill = 0; pill < 200; pill += 2) {
        counter.addPill(2);
      }
      assertEquals(200, counter.getPillCount());
      counter.reset();
    }
    return true;
  }
}

