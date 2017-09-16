/*
 * Created by Dariusz Lelek on 9/16/17 5:41 PM
 * Copyright (c) 2017. All rights reserved.
 */

package process.candidate;

public class StringCandidate extends Candidate {
  private final String content;

  public StringCandidate(long matchCount, String content) {
    super(matchCount);
    this.content = content;
  }

  public String getContent() {
    return content;
  }
}
