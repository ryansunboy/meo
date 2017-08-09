package com.asiainfo.meo.customer.level.app.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.meo.customer.level.app.model.entity.LevelDef;
import com.asiainfo.meo.customer.level.app.model.entity.LevelRule;

public class Level
{
    private LevelDef        levelDef;
    
    private List<LevelRule> levelRule;
    
    public LevelDef getLevelDef()
    {
        return levelDef;
    }
    
    public void setLevelDef(LevelDef levelDef)
    {
        this.levelDef = levelDef;
    }
    
    public List<LevelRule> getLevelRule()
    {
        if (levelRule == null)
        {
            levelRule = new ArrayList<LevelRule>();
        }
        return levelRule;
    }
    
    public void setLevelRule(List<LevelRule> levelRule)
    {
        this.levelRule = levelRule;
    }
    
}
