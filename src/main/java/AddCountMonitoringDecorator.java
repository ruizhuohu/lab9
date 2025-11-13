import java.util.ArrayList;
import java.util.List;

/**
 * A decorator that monitors the number of times addPill is called
 * before each reset. This decorator extends PillCounterDecorator
 * and tracks add counts across multiple reset cycles.
 */
public class AddCountMonitoringDecorator extends PillCounterDecorator {
  private List<Integer> addCounts;
  private int currentAddCount;

  /**
   * Constructs an AddCountMonitoringDecorator with the given PillCounter delegate.
   *
   * @param delegate the PillCounter to delegate all calls to
   */
  public AddCountMonitoringDecorator(PillCounter delegate) {
    super(delegate);
    this.addCounts = new ArrayList<>();
    this.currentAddCount = 0;
  }

  @Override
  public void addPill(int count) {
    super.addPill(count);
    currentAddCount++;
  }

  @Override
  public void reset() {
    if (currentAddCount > 0) {
      addCounts.add(currentAddCount);
    }
    currentAddCount = 0;
    super.reset();
  }

  /**
   * Returns a list of all add counts recorded so far.
   * Each count represents the number of times addPill was called
   * before each reset.
   *
   * @return a list of add counts
   */
  public List<Integer> getAddCounts() {
    return new ArrayList<>(addCounts);
  }
}

