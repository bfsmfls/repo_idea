package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {
    public PageInfo findAllAdByPage(PromotionAdVo promotionAdVo);
    /*新增广告*/
    public void savePromotionAd(PromotionAd promotionAd);
    /*回显广告信息*/
    public PromotionAd findPromotionAdById(Integer id);
    /*修改广告*/
    public void updatePromotionAd(PromotionAd promotionAd);
    /*广告动态上下线*/
    public void updatePromotionAdStatus(Integer id,Integer status);
}
