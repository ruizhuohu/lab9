/**
 * A decorator for PillCounter that implements the decorator pattern.
 * This decorator acts as a pass-through, forwarding all method calls
 * to the underlying PillCounter delegate.
 */
public class PillCounterDecorator implements PillCounter {
  protected PillCounter delegate;

  /**
   * Constructs a PillCounterDecorator with the given PillCounter delegate.
   *
   * @param delegate the PillCounter to delegate all calls to
   */
  public PillCounterDecorator(PillCounter delegate) {
    this.delegate = delegate;
  }

  @Override
  public void addPill(int count) {
    delegate.addPill(count);
  }

  @Override
  public void removePill() {
    delegate.removePill();
  }

  @Override
  public void reset() {
    delegate.reset();
  }

  @Override
  public int getPillCount() {
    return delegate.getPillCount();
  }
}

