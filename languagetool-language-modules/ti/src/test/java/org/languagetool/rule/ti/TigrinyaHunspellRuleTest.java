package org.languagetool.rule.ti;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.Languages;
import org.languagetool.TestTools;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TigrinyaHunspellRuleTest {

  private final Rule rule = new TigrinyaHunspellRule(TestTools.getMessages("ti"));
  private final JLanguageTool languageTool = new JLanguageTool(Languages.getLanguageForShortCode("ti"));

  @Test
  public void testTigrinyaWordSpell() throws IOException {
    assertEquals(1, rule.match(languageTool.getAnalyzedSentence("መወ")).length);
    List<String> suggestedReplacements = rule.match(languageTool.getAnalyzedSentence("መዊፀ"))[0].getSuggestedReplacements();
    assertEquals(1, suggestedReplacements.size());
    assertEquals("መወፀ", suggestedReplacements.get(0));
  }

  @Test
  public void wordWithPrefixZ() throws IOException {
    RuleMatch[] rule = this.rule.match(languageTool.getAnalyzedSentence("ዝመወወ"));
    assertEquals(3, rule[0].getSuggestedReplacements().size());
    assertTrue(rule[0].getSuggestedReplacements().containsAll(ImmutableList.of("ዝመወፀ","ዝመወሰ","ዝመወፀ")));
//    assertEquals("ዝመወፀ", rule[0].getSuggestedReplacements().get(0));
  }

  @Test
  public void wordWithNegatedCircumfix() throws IOException {
    RuleMatch[] suffixMissing = this.rule.match(languageTool.getAnalyzedSentence("ኣይመወፀ"));
    assertEquals("ኣይመወፀን", suffixMissing[0].getSuggestedReplacements().get(0));
    RuleMatch[] prefixMissing = this.rule.match(languageTool.getAnalyzedSentence("ኣመወፀን"));
    assertEquals("ኣይመወፀን", prefixMissing[0].getSuggestedReplacements().get(0));
  }

}