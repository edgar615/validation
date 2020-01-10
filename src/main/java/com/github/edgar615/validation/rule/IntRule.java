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
 * 校验是否是int.
 * <p>
 * 只校验String类型的值，其他类型默认为合法.
 *
 * @author Edgar  Date 2016/1/6
 */
class IntRule implements Rule {

  private static final String KEY = "int";

  private static final String TRUE = "true";

  private IntRule() {
  }

  static Rule create() {
    return new IntRule();
  }

  @Override
  public String message() {
    return "int Required";
  }

  @Override
  public boolean isValid(Object property) {
    if (property == null) {
      return true;
    }
    if (property instanceof Integer) {
      return true;
    }
    if (property instanceof Short) {
      return true;
    }
    if (property instanceof Byte) {
      return true;
    }
    if (property instanceof Long) {
      Long longVal = (Long) property;
      return longVal >= Integer.MIN_VALUE && longVal <= Integer.MAX_VALUE;
    }
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      try {
        Integer.parseInt(str);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("IntRule")
        .toString();
  }

  static class Parser implements RuleParser {

    @Override
    public Rule parse(List<String> keyAndValue) {
      String key = keyAndValue.get(0);
      if (!KEY.equals(key)) {
        return null;
      }
      if (keyAndValue.size() == 1) {
        return new IntRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new IntRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof IntRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
