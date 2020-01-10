/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.edgar615.validation.rule;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.util.List;

/**
 * 数值最大值的校验.
 * <p>
 * 只校验数值类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class MinRule implements Rule {

  private static final String KEY = "min";
  /**
   * 最小值.
   */
  private final int value;

  private MinRule(int value) {
    this.value = value;
  }

  static Rule create(int value) {
    return new MinRule(value);
  }

  @Override
  public String message() {
    return "Min value:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && property instanceof Short) {
      return Short.class.cast(property) >= value;
    }
    if (property != null && property instanceof Integer) {
      return Integer.class.cast(property) >= value;
    }
    if (property != null && property instanceof Long) {
      return Long.class.cast(property) >= value;
    }
    if (property != null && property instanceof Float) {
      return Float.class.cast(property) >= value;
    }
    if (property != null && property instanceof Double) {
      return Double.class.cast(property) >= value;
    }
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      try {
        return new BigDecimal(str).compareTo(new BigDecimal(value + "")) >= 0;
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("MinRule")
        .add("value", value)
        .toString();
  }

  int value() {
    return value;
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() > 1) {
        return new MinRule(Integer.parseInt(keyAndValue.get(1)));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof MinRule) {
        return Lists.newArrayList(KEY, ((MinRule) rule).value + "");
      }
      return Lists.newArrayList();
    }
  }
}
