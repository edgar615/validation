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
import java.util.List;

/**
 * 字符串最大长度的校验.
 * <p>
 * 只校验String和Number类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class MaxLengthRule implements Rule {

  private static final String KEY = "maxLength";

  /**
   * 最大长度.
   */
  private final int value;

  private MaxLengthRule(int value) {
    this.value = value;
  }

  static Rule create(int value) {
    return new MaxLengthRule(value);
  }

  @Override
  public String message() {
    return "MaxLength:" + value;
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      return str.length() <= value;
    }
    if (property != null && (property instanceof Number)) {
      String str = property.toString();
      return str.length() <= value;
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("MaxLengthRule")
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
        return new MaxLengthRule(Integer.parseInt(keyAndValue.get(1)));
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof MaxLengthRule) {
        return Lists.newArrayList(KEY, ((MaxLengthRule) rule).value + "");
      }
      return Lists.newArrayList();
    }
  }
}
