/**
 * A decorator that implements lazy-adding of pills.
 * This decorator accumulates pills when addPill is called,
 * but only forwards them to the delegate when getPillCount()
 * or reset() is called. This reduces the number of calls to
 * the underlying PillCounter, improving performance.
 */
public class BatchAddingDecorator extends PillCounterDecorator {
  private int accumulatedPills;

  /**
   * Constructs a BatchAddingDecorator with the given PillCounter delegate.
   *
   * @param delegate the PillCounter to delegate calls to
   */
  public BatchAddingDecorator(PillCounter delegate) {
    super(delegate);
    this.accumulatedPills = 0;
  }

  @Override
  public void addPill(int count) {
    if (count > 0) {
      accumulatedPills += count;
    }
  }

  @Override
  public void removePill() {
    flushAccumulatedPills();
    super.removePill();
  }

  @Override
  public void reset() {
    flushAccumulatedPills();
    super.reset();
  }

  @Override
  public int getPillCount() {
    flushAccumulatedPills();
    return super.getPillCount();
  }

  /**
   * Flushes accumulated pills to the delegate by calling addPill
   * with the accumulated count, then resets the accumulator.
   */
  private void flushAccumulatedPills() {
    if (accumulatedPills > 0) {
      super.addPill(accumulatedPills);
      accumulatedPills = 0;
    }
  }
}

