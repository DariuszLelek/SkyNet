/*
 * Created by Dariusz Lelek on 9/17/17 12:27 AM
 * Copyright (c) 2017. All rights reserved.
 */

package constant;

public enum Number {
  O(0),
  ZERO(0),
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),

  TEN(10),
  ELEVEN(11),
  TWELVE(12),
  THIRTEEN(13),
  FOURTEEN(14),
  FIFTEEN(15),
  SIXTEEN(16),
  SEVENTEEN(17),
  EIGHTEEN(18),
  NINETEEN(19),

  TWENTY(20),
  THIRTY(30),
  FORTY(40),
  FIFTY(50),
  SIXTY(60),
  SEVENTY(70),
  EIGHTY(80),
  NINETY(90),

  HUNDRED(100),
  THOUSAND(1000),
  MILLION(1000000);

  private final long value;

  Number(long value) {
    this.value = value;
  }

  public long getValue() {
    return value;
  }
}