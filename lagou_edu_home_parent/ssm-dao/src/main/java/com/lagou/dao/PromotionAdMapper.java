package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    /*
分页获取所有的广告列表
*/
    public List<PromotionAd> findAllAdByPage();

    /*新增广告*/
    public void savePromotionAd(PromotionAd promotionAd);
    /*修改广告*/
    public void updatePromotionAd(PromotionAd promotionAd);
    /*回显广告信息*/
    public PromotionAd findPromotionAdById(Integer id);
    /*广告动态上下线*/
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
