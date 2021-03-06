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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 只能包含字母、数字.
 *
 * @author Edgar
 * @create 2018-09-04 15:13
 **/
class AlphaNumberRule implements Rule {

  private static final String KEY = "alphaNumber";

  private static final String TRUE = "true";

  /**
   * 正则表达式
   */
  private static final Pattern PATTERN = Pattern.compile("[0-9A-Za-z]*");

  private AlphaNumberRule() {
  }

  static Rule create() {
    return new AlphaNumberRule();
  }

  @Override
  public String message() {
    return "only contain alphabetic characters or numbers";
  }

  @Override
  public boolean isValid(Object property) {
    if (property != null && (property instanceof String)) {
      String str = String.class.cast(property);
      Matcher matcher = PATTERN.matcher(str);
      return matcher.matches();
    }
    return true;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("AlphaNumberRule")
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
        return new AlphaNumberRule();
      }
      if (TRUE.equalsIgnoreCase(keyAndValue.get(1))) {
        return new AlphaNumberRule();
      }
      return null;
    }

    @Override
    public List<String> toParsableString(Rule rule) {
      if (rule instanceof AlphaNumberRule) {
        return Lists.newArrayList(KEY);
      }
      return Lists.newArrayList();
    }
  }
}
