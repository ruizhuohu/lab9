import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for PillCounterDecorator.
 * This test mimics the given test but uses the decorator pattern.
 */
public class PillCounterDecoratorTest {

  /**
   * Test that the decorator works exactly like the original pill counter.
   * This test should run exactly as the given test, because our decorator
   * is a simple pass-through.
   */
  @Test
  public void usage() {
    PillCounter counter = new PillCounterDecorator(new LoggingPillCounter());
    boolean result = conveyerBelt(counter);
    assertTrue(result);
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
        counter.addPill(2); //2 pills at a time (third machine)
      }
      assertEquals(200, counter.getPillCount());
      counter.reset();
    }
    return true;
  }
}

