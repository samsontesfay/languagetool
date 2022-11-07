package org.languagetool.rule.ti;

import org.jetbrains.annotations.NotNull;
import org.languagetool.UserConfig;
import org.languagetool.language.Tigrinya;
import org.languagetool.rules.spelling.hunspell.HunspellRule;

import java.util.ResourceBundle;

public final class TigrinyaHunspellRule extends HunspellRule {

  public static final String RULE_ID = "HUNSPELL_RULE_TI";
  private static final String RESOURCE_FILENAME = "/ti/hunspell/ti.dic";

  public TigrinyaHunspellRule(ResourceBundle messages, UserConfig userConfig) {
    super(messages, new Tigrinya(), userConfig);
  }

  public TigrinyaHunspellRule(ResourceBundle messages) {
    this(messages, null);
  }


  @Override
  public String getId() {
    return RULE_ID;
  }

  @Override
  @NotNull
  protected String getDictFilenameInResources(String langCountry) {
    return RESOURCE_FILENAME;
  }

  @Override
  protected boolean isLatinScript() {
    return false;
  }
}
