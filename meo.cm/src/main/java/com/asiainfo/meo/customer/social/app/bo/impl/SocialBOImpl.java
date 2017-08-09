package com.asiainfo.meo.customer.social.app.bo.impl;

import javax.annotation.Resource;

import com.asiainfo.meo.customer.social.app.bo.SocialBO;
import com.asiainfo.meo.customer.social.app.model.entity.SocialInfo;
import com.asiainfo.meo.customer.social.app.repository.SocialRepository;


public class SocialBOImpl implements SocialBO
{
    @Resource
    private SocialRepository socialRepository;
    
    public void saveSocial(SocialInfo socialInfo)
    {
        socialRepository.saveSocial(socialInfo);
    }
    
}
