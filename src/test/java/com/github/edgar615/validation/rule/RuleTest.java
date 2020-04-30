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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Test;

/**
 * Created by Edgar on 2016/3/18.
 *
 * @author Edgar  Date 2016/3/18
 */
public class RuleTest {

  @Test
  public void testMaxLength() {

    Rule rule = Rule.maxLength(5);
    assertTrue(rule.isValid("abc"));
    assertTrue(rule.isValid("abcde"));
    assertFalse(rule.message(), rule.isValid("abcdef"));

    assertTrue(rule.message(), rule.isValid(1));
    assertFalse(rule.message(), rule.isValid(12345678));

    assertTrue(rule.message(), rule.isValid(new Object()));
  }

  @Test
  public void testMinLength() {
    Rule rule = Rule.minLength(5);
    assertTrue(rule.isValid("abcdef"));
    assertTrue(rule.isValid("abcde"));
    assertFalse(rule.message(), rule.isValid("abc"));

    assertFalse(rule.message(), rule.isValid(1));
    assertTrue(rule.message(), rule.isValid(12345678));
    assertTrue(rule.message(), rule.isValid(new Object()));
  }

  @Test
  public void testFixLength() {
    Rule rule = Rule.fixLength(5);
    assertFalse(rule.isValid("abcdef"));
    assertTrue(rule.isValid("abcde"));
    assertFalse(rule.message(), rule.isValid("abc"));

    assertFalse(rule.message(), rule.isValid(1));
    assertFalse(rule.message(), rule.isValid(12345678));
    assertTrue(rule.message(), rule.isValid(new Object()));
  }

  @Test
  public void testMax() {
    Rule rule = Rule.max(5);
    assertTrue(rule.isValid(1));
    assertTrue(rule.isValid(5));
    assertFalse(rule.message(), rule.isValid(15));

    assertTrue(rule.isValid("1"));
    assertTrue(rule.isValid("5"));
    assertFalse(rule.message(), rule.isValid("15"));

    assertTrue(rule.message(), rule.isValid(new Object()));

    rule = Rule.max(5);
    assertTrue(rule.isValid(1));
    assertTrue(rule.isValid(5));
    assertFalse(rule.message(), rule.isValid(15));

    assertTrue(rule.isValid("1"));
    assertTrue(rule.isValid("5"));
    assertFalse(rule.message(), rule.isValid("15"));

    rule = Rule.max(5);
    assertTrue(rule.isValid(1.0));
    assertTrue(rule.isValid(5.0));
    assertFalse(rule.message(), rule.isValid(15.0));

    assertTrue(rule.isValid("1.0"));
    assertTrue(rule.isValid("5.0"));
    assertFalse(rule.message(), rule.isValid("15.0"));
  }

  @Test
  public void testMin() {
    Rule rule = Rule.min(5);
    assertTrue(rule.isValid(10));
    assertTrue(rule.isValid(5));
    assertFalse(rule.message(), rule.isValid(1));

    assertTrue(rule.isValid("10"));
    assertTrue(rule.isValid("5"));
    assertFalse(rule.message(), rule.isValid("1"));

    assertTrue(rule.message(), rule.isValid(new Object()));

    rule = Rule.min(5);
    assertTrue(rule.isValid(10));
    assertTrue(rule.isValid(5));
    assertFalse(rule.message(), rule.isValid(1));

    assertTrue(rule.isValid("10"));
    assertTrue(rule.isValid("5"));
    assertFalse(rule.message(), rule.isValid("1"));

    rule = Rule.min(5);
    assertTrue(rule.isValid(10.0));
    assertTrue(rule.isValid(5.0));
    assertFalse(rule.message(), rule.isValid(1.0));

    assertTrue(rule.isValid("10.0"));
    assertTrue(rule.isValid("5.0"));
    assertFalse(rule.message(), rule.isValid("1.0"));
  }

  @Test
  public void testRequired() {
    Rule rule = Rule.required();
    assertTrue(rule.isValid("foo"));
    assertFalse(rule.message(), rule.isValid(null));
    assertFalse(rule.message(), rule.isValid(""));
    assertTrue(rule.isValid("     "));

    assertTrue(rule.isValid(new Object()));

  }

  @Test
  public void testRegex() {
    String regex = "[0-9]{11}";
    Rule rule = Rule.regex(regex);
    assertFalse(rule.message(), rule.isValid("abc"));
    assertFalse(rule.message(), rule.isValid("123"));
    assertFalse(rule.message(), rule.isValid("abc123"));
    assertTrue(rule.message(), rule.isValid("12345678901"));

    rule = Rule.regex("/users/[0-9]+/wallet");
    assertTrue(rule.message(), rule.isValid("/users/1/wallet"));
    assertFalse(rule.message(), rule.isValid("/users/a/wallet"));

    rule = Rule.regex("[0-9A-F]{16}");
    assertTrue(rule.message(), rule.isValid("0123456789ABCDEF"));
    assertFalse(rule.message(), rule.isValid("0123456789aBCDEF"));
  }

  @Test
  public void testProhibited() {
    Rule rule = Rule.prohibited();
    assertFalse(rule.message(), rule.isValid("abc"));
    assertTrue(rule.message(), rule.isValid(null));
  }

  @Test
  public void testOptional() {
    Rule rule = Rule.optional(ImmutableList.of(1, 2, 3));
    assertTrue(rule.message(), rule.isValid("1"));
    assertTrue(rule.message(), rule.isValid(1));
    assertTrue(rule.message(), rule.isValid(null));

    assertFalse(rule.message(), rule.isValid("4"));
  }

  @Test
  public void testEmail() {
    Rule rule = Rule.email();
    assertTrue(rule.message(), rule.isValid("1@qq.com"));
    assertTrue(rule.message(), rule.isValid("yuzhou.zhang@csst.com"));
    assertTrue(rule.message(), rule.isValid(null));
    assertTrue(rule.message(), rule.isValid("zyz@126.COM"));
    assertTrue(rule.message(), rule.isValid("zyz@qq"));
    assertFalse(rule.message(), rule.isValid("4"));
  }

  @Test
  public void testEqulas() {

    Rule rule = Rule.equals("5");
    assertTrue(rule.isValid(5));
    assertTrue(rule.isValid("5"));
    assertFalse(rule.message(), rule.isValid("1"));
    assertFalse(rule.message(), rule.isValid(new Object()));
  }

  @Test
  public void testByte() {

    Rule rule = Rule.byteRule();
    assertTrue(rule.isValid(5));
    assertTrue(rule.isValid("5"));
    assertFalse(rule.message(), rule.isValid("1111111111"));
    assertFalse(rule.message(), rule.isValid(129));
    assertFalse(rule.message(), rule.isValid(1111111111111111111l));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertFalse(rule.message(), rule.isValid("11111111111111111111"));
    assertFalse(rule.message(), rule.isValid(0.00));
  }

  @Test
  public void testShort() {

    Rule rule = Rule.shortRule();
    assertTrue(rule.isValid(5));
    assertTrue(rule.isValid("5"));
    assertFalse(rule.message(), rule.isValid("1111111111"));
    assertFalse(rule.message(), rule.isValid(32768));
    assertFalse(rule.message(), rule.isValid(1111111111111111111l));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertFalse(rule.message(), rule.isValid("11111111111111111111"));
    assertFalse(rule.message(), rule.isValid(0.00));
  }

  @Test
  public void testInt() {

    Rule rule = Rule.intRule();
    assertTrue(rule.isValid(5));
    assertTrue(rule.isValid("5"));
    assertTrue(rule.message(), rule.isValid("1111111111"));
    assertTrue(rule.message(), rule.isValid(-1111111111l));
    assertFalse(rule.message(), rule.isValid(1111111111111111111l));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertFalse(rule.message(), rule.isValid("11111111111111111111"));
    assertFalse(rule.message(), rule.isValid(0.00));
  }

  @Test
  public void testLong() {

    Rule rule = Rule.longRule();
    assertTrue(rule.isValid(5));
    assertTrue(rule.isValid("5"));
    assertTrue(rule.message(), rule.isValid("1111111111"));
    assertTrue(rule.message(), rule.isValid(1111111111l));
    assertTrue(rule.message(), rule.isValid(1111111111111111111l));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertTrue(rule.message(), rule.isValid("1111111111111111111"));
    assertFalse(rule.message(), rule.isValid("11111111111111111111111"));
    assertFalse(rule.message(), rule.isValid(0.00));

  }

  @Test
  public void testFloat() {

    Rule rule = Rule.floatRule();
    assertTrue(rule.isValid(5));
    assertTrue(rule.isValid("5"));
    assertTrue(rule.message(), rule.isValid("" + Integer.MAX_VALUE));
    assertTrue(rule.message(), rule.isValid(Integer.MAX_VALUE));
    assertTrue(rule.message(), rule.isValid(Long.MAX_VALUE));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertTrue(rule.message(), rule.isValid("" + Long.MAX_VALUE));
    assertTrue(rule.message(), rule.isValid(0.00));
    assertTrue(rule.message(), rule.isValid(Double.MAX_VALUE + ""));
  }

  @Test
  public void testDouble() {

    Rule rule = Rule.doubleRule();
    assertTrue(rule.isValid(5));
    assertTrue(rule.isValid("5"));
    assertTrue(rule.message(), rule.isValid("" + Integer.MAX_VALUE));
    assertTrue(rule.message(), rule.isValid(Integer.MAX_VALUE));
    assertTrue(rule.message(), rule.isValid(Long.MAX_VALUE));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertTrue(rule.message(), rule.isValid("-" + Float.MAX_VALUE));
    assertTrue(rule.message(), rule.isValid(0.00));
    assertTrue(rule.message(), rule.isValid(Double.MAX_VALUE + ""));
  }

  @Test
  public void testBool() {

    Rule rule = Rule.bool();
    assertTrue(rule.isValid("true"));
    assertTrue(rule.isValid(true));
    assertTrue(rule.isValid("false"));
    assertTrue(rule.isValid(false));
    assertFalse(rule.isValid(1));
    assertFalse(rule.isValid("0"));
    assertFalse(rule.message(), rule.isValid("1111111111"));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertFalse(rule.message(), rule.isValid("11111111111111111111"));
  }

  @Test
  public void testList() {

    Rule rule = Rule.list();
    assertTrue(rule.isValid(new ArrayList<>()));
    assertTrue(rule.isValid(Arrays.asList("1", "2")));
    assertTrue(rule.isValid(null));
    assertFalse(rule.isValid(new HashMap<>()));
    assertFalse(rule.isValid("true"));
    assertFalse(rule.isValid(true));
    assertFalse(rule.isValid("false"));
    assertFalse(rule.isValid(false));
    assertFalse(rule.isValid(1));
    assertFalse(rule.isValid("0"));
    assertFalse(rule.message(), rule.isValid("1111111111"));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertFalse(rule.message(), rule.isValid("11111111111111111111"));
  }

  @Test
  public void testMap() {

    Rule rule = Rule.map();
    assertTrue(rule.isValid(null));
    assertTrue(rule.isValid(new HashMap<>()));
    assertTrue(rule.isValid(ImmutableMap.of(1, 1)));
    assertFalse(rule.isValid(new ArrayList<>()));
    assertFalse(rule.isValid(Arrays.asList("1", "2")));
    assertFalse(rule.isValid("true"));
    assertFalse(rule.isValid(true));
    assertFalse(rule.isValid("false"));
    assertFalse(rule.isValid(false));
    assertFalse(rule.isValid(1));
    assertFalse(rule.isValid("0"));
    assertFalse(rule.message(), rule.isValid("1111111111"));
    assertFalse(rule.message(), rule.isValid(new Object()));
    assertFalse(rule.message(), rule.isValid("11111111111111111111"));
  }

  @Test
  public void testISO8601Date() {
    Rule rule = Rule.iso8601Date();
    assertFalse(rule.message(), rule.isValid("abc"));
    assertFalse(rule.message(), rule.isValid("123"));
    assertFalse(rule.message(), rule.isValid("20180313"));
    assertTrue(rule.message(), rule.isValid("2018-03-13"));

  }

  @Test
  public void testISO8601Time() {
    Rule rule = Rule.iso8601Time();
    assertFalse(rule.message(), rule.isValid("abc"));
    assertFalse(rule.message(), rule.isValid("123"));
    assertFalse(rule.message(), rule.isValid("141500"));
    assertTrue(rule.message(), rule.isValid("14:15:00"));
    assertTrue(rule.message(), rule.isValid("00:00:00"));
  }

  @Test
  public void testISO8601DateTime() {
    Rule rule = Rule.iso8601Datetime();
    assertFalse(rule.message(), rule.isValid("abc"));
    assertFalse(rule.message(), rule.isValid("123"));
    assertFalse(rule.message(), rule.isValid("141500"));
    assertFalse(rule.message(), rule.isValid("2018-03-1314:15:00"));
    assertFalse(rule.message(), rule.isValid("2018-03-13 00:00:00"));
    assertTrue(rule.message(), rule.isValid("2018-03-13T14:15:00"));
  }

  @Test
  public void testAlpha() {
    Rule rule = Rule.alpha();
    assertTrue(rule.message(), rule.isValid(""));
    assertTrue(rule.message(), rule.isValid("abc"));
    assertFalse(rule.message(), rule.isValid("123"));
    assertFalse(rule.message(), rule.isValid("a1"));
    assertFalse(rule.message(), rule.isValid("1a"));
  }

  @Test
  public void testAlphaUnderscore() {
    Rule rule = Rule.alphaUnderscore();
    assertTrue(rule.message(), rule.isValid(""));
    assertTrue(rule.message(), rule.isValid("abc"));
    assertTrue(rule.message(), rule.isValid("123"));
    assertTrue(rule.message(), rule.isValid("a1__"));
    assertTrue(rule.message(), rule.isValid("_1_a"));
    assertTrue(rule.message(), rule.isValid("_1_a"));
    assertTrue(rule.message(), rule.isValid("_1_a"));
    assertFalse(rule.message(), rule.isValid("a @ #"));
  }

  @Test
  public void testAlphaNumber() {
    Rule rule = Rule.alphaNumber();
    assertTrue(rule.message(), rule.isValid(""));
    assertTrue(rule.message(), rule.isValid("abc"));
    assertTrue(rule.message(), rule.isValid("123"));
    assertTrue(rule.message(), rule.isValid("a12e3"));
    assertFalse(rule.message(), rule.isValid("a1__"));
    assertFalse(rule.message(), rule.isValid("_1_a"));
    assertFalse(rule.message(), rule.isValid("_1_a"));
    assertFalse(rule.message(), rule.isValid("_1_a"));
    assertFalse(rule.message(), rule.isValid("a @ #"));
  }

  @Test
  public void testAlphaSpace() {
    Rule rule = Rule.alphaSpace();
    assertTrue(rule.message(), rule.isValid(""));
    assertTrue(rule.message(), rule.isValid("abc"));
    assertTrue(rule.message(), rule.isValid(" ab c "));
    assertFalse(rule.message(), rule.isValid("123"));
    assertFalse(rule.message(), rule.isValid("a12e3"));
    assertFalse(rule.message(), rule.isValid("a1__"));
    assertFalse(rule.message(), rule.isValid("_1_a"));
    assertFalse(rule.message(), rule.isValid("_1_a"));
    assertFalse(rule.message(), rule.isValid("_1_a"));
    assertFalse(rule.message(), rule.isValid("a @ #"));
  }

  @Test
  public void testDigits() {
    Rule rule = Rule.digits();
    assertFalse(rule.message(), rule.isValid(""));
    assertFalse(rule.message(), rule.isValid("abc"));
    assertTrue(rule.message(), rule.isValid("12345677889977687878"));
    assertTrue(rule.message(), rule.isValid("3"));
    assertFalse(rule.message(), rule.isValid("03"));
    assertTrue(rule.message(), rule.isValid(3));

    rule = Rule.digits(3);
    assertFalse(rule.message(), rule.isValid("a12e3"));
    assertFalse(rule.message(), rule.isValid("a1__"));
    assertFalse(rule.message(), rule.isValid("1"));
    assertFalse(rule.message(), rule.isValid("12"));
    assertFalse(rule.message(), rule.isValid("1234"));
    assertTrue(rule.message(), rule.isValid("132"));
    assertFalse(rule.message(), rule.isValid("012"));
  }

  @Test
  public void testDecimal() {
    Rule rule = Rule.decimal(2);
    assertFalse(rule.message(), rule.isValid(""));
    assertFalse(rule.message(), rule.isValid("abc"));
    assertFalse(rule.message(), rule.isValid("12345677889977687878"));
    assertFalse(rule.message(), rule.isValid("3"));
    assertFalse(rule.message(), rule.isValid("03"));
    assertTrue(rule.message(), rule.isValid(3.1));
    assertFalse(rule.message(), rule.isValid("01.11"));
    assertTrue(rule.message(), rule.isValid(0.00));
    assertTrue(rule.message(), rule.isValid(0.10));
    assertTrue(rule.message(), rule.isValid(0.11));
    assertTrue(rule.message(), rule.isValid(3.00));
    assertTrue(rule.message(), rule.isValid(3.10));
    assertTrue(rule.message(), rule.isValid(3.11));

  }
}
