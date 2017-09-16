/*
 * Created by Dariusz Lelek on 9/16/17 5:40 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.candidate;

public class Candidate implements Comparable<Candidate>{
  private final long matchCount;

  public Candidate(long matchCount) {
    this.matchCount = matchCount;
  }

  public long getMatchCount() {
    return matchCount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Candidate candidate = (Candidate) o;

    return matchCount == candidate.matchCount;
  }

  @Override
  public int hashCode() {
    return (int) (matchCount ^ (matchCount >>> 32));
  }

  @Override
  public int compareTo(Candidate o) {
    return Long.compare(o.matchCount, this.matchCount);
  }
}
