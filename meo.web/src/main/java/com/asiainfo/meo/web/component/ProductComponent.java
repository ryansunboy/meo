package com.asiainfo.meo.web.component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.asiainfo.meo.common.core.utils.PageInfo;
import com.asiainfo.meo.common.core.utils.ValidateUtil;
import com.asiainfo.meo.product.profile.app.model.vo.DiscountRuleVO;
import com.asiainfo.meo.product.profile.app.model.vo.PriceVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdMnoMappingVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdPricePlanVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProdPriceVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoQueryConditionVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductBasicInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecInfoVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductSpecVO;
import com.asiainfo.meo.product.profile.app.model.vo.ProductVO;
import com.asiainfo.meo.web.product.model.vo.UIDiscountRuleVO;
import com.asiainfo.meo.web.product.model.vo.UIPriceVO;
import com.asiainfo.meo.web.product.model.vo.UIProdMnoMappingInfoVO;
import com.asiainfo.meo.web.product.model.vo.UIProdPricePlanVO;
import com.asiainfo.meo.web.product.model.vo.UIProdPriceVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicInfoVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicListVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicOutputVO;
import com.asiainfo.meo.web.product.model.vo.UIProductBasicVO;
import com.asiainfo.meo.web.product.model.vo.UIProductMappingVO;
import com.asiainfo.meo.web.product.model.vo.UIProductPriceVO;
import com.asiainfo.meo.web.product.model.vo.UIProductSpecVO;
import com.asiainfo.meo.web.product.model.vo.UIProductSpecialVO;
import com.asiainfo.meo.web.product.model.vo.UIProductVO;

public class ProductComponent
{
    //private static final Log LOG = LogFactory.getLog(ProductComponent.class);
    
    public List<UIProductBasicOutputVO> transformProductVOListToUIProductBasicOutputVOList(
            List<ProductBasicInfoVO> productBasicInfoVOList)
    {
        List<UIProductBasicOutputVO> uiProductBasicOutputVOList = new ArrayList<UIProductBasicOutputVO>();
        for (ProductBasicInfoVO productBasicInfoVO : productBasicInfoVOList)
        {
            UIProductBasicOutputVO uiProductBasicOutputVO = new UIProductBasicOutputVO();
            BeanUtils.copyProperties(productBasicInfoVO, uiProductBasicOutputVO);
            uiProductBasicOutputVO.setProductValidDate(productBasicInfoVO.getProductValidDate() != null ? productBasicInfoVO.getProductValidDate().getTime() : null);
            uiProductBasicOutputVO.setProductExpiredDate(productBasicInfoVO.getProductExpiredDate() != null ? productBasicInfoVO.getProductExpiredDate().getTime() : null);
            uiProductBasicOutputVOList.add(uiProductBasicOutputVO);
        }
        return uiProductBasicOutputVOList;
    }
    
    public ProductBasicInfoVO transformUIProductBasicInfoVOToProductBasicOutputVO(UIProductBasicInfoVO uiProductCondition)
    {
        ProductBasicInfoVO productBasicInfoVO = new ProductBasicInfoVO();
        if (ValidateUtil.isNotEmpty(uiProductCondition))
        {
            BeanUtils.copyProperties(uiProductCondition, productBasicInfoVO);
            productBasicInfoVO.setProductValidDate(uiProductCondition.getValidDate() != null ? new Timestamp(uiProductCondition.getValidDate()) : null);
            productBasicInfoVO.setProductExpiredDate(uiProductCondition.getExpiredDate() != null ? new Timestamp(uiProductCondition.getExpiredDate()) : null);
        }
        return productBasicInfoVO;
    }
    
    public PageInfo<UIProductBasicListVO> transformProductVOListToUIProductBasicListVOList(
            PageInfo<ProductBasicInfoVO> pageInfo)
    {
        final PageInfo<UIProductBasicListVO> uiPageInfo = new PageInfo<UIProductBasicListVO>();
        if (ValidateUtil.isEmpty(pageInfo) || ValidateUtil.isEmpty(pageInfo.getResult()))
        {
            return uiPageInfo.emptyPageInfo();
        }
        List<UIProductBasicListVO> uiProductBasicListVOlist = new ArrayList<UIProductBasicListVO>();
        for (ProductBasicInfoVO productBasicInfoVO : pageInfo.getResult())
        {
            UIProductBasicListVO uiProductBasicListVO = new UIProductBasicListVO();
            BeanUtils.copyProperties(productBasicInfoVO, uiProductBasicListVO);
            uiProductBasicListVOlist.add(uiProductBasicListVO);
        }
        uiPageInfo.setResult(uiProductBasicListVOlist);
        uiPageInfo.setCurrentPage(pageInfo.getCurrentPage());
        uiPageInfo.setPageSize(pageInfo.getPageSize());
        uiPageInfo.setTotalSize(pageInfo.getTotalSize());

        return uiPageInfo;
    }
    
    public ProductBasicInfoQueryConditionVO transformProductVOCriteriaToQueryConditionVO(
            final Integer pageNo,
            final Integer pageSize)
    {
        ProductBasicInfoQueryConditionVO conditionVO = new ProductBasicInfoQueryConditionVO();
        conditionVO.setPageNo(pageNo);
        conditionVO.setPageSize(pageSize);
        return conditionVO;
    }
    
    
    public ProductVO transformUIProductVOtoProductVO(UIProductVO uiProductVO){
        if(ValidateUtil.isNull(uiProductVO)){
            return null;
        }
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(uiProductVO, productVO, new String[]{});
        return productVO;
    }
    
    
    public ProductSpecVO transformUIProductSpecVOtoProductSpecVO(UIProductSpecVO uiProductSpecVO){
        if(ValidateUtil.isNull(uiProductSpecVO)){
            return null;
        }
        ProductSpecVO productSpecVO = new ProductSpecVO();
        BeanUtils.copyProperties(uiProductSpecVO, productSpecVO, new String[]{});
        return productSpecVO;
    }
    
    public List<ProdPricePlanVO> transformUIProductPriceVOListtoProdPricePlanVOList(List<UIProdPricePlanVO> uiProductPriceVOs){
        if(ValidateUtil.isEmpty(uiProductPriceVOs)){
            return null;
        }
        List<ProdPricePlanVO> prodPricePlanVos = new ArrayList<ProdPricePlanVO>();
        for(UIProdPricePlanVO uiProductPriceVO : uiProductPriceVOs)
        {
            ProdPricePlanVO prodPricePlanVO = new ProdPricePlanVO();
            BeanUtils.copyProperties(uiProductPriceVO, prodPricePlanVO, new String[]{});
            prodPricePlanVos.add(prodPricePlanVO);
        }
        return prodPricePlanVos;
    }
    
    public List<ProdMnoMappingVO> transformUIProductMappingVOListtoProdMnoMappingVOList(List<UIProductMappingVO> uiProductMappings){
        if(ValidateUtil.isNull(uiProductMappings)){
            return null;
        }
        List<ProdMnoMappingVO> prodMnoMappings= new ArrayList<ProdMnoMappingVO>();
        for(UIProductMappingVO uiProductMappingVO:uiProductMappings){
            ProdMnoMappingVO prodMnoMappingVO = new ProdMnoMappingVO();
            BeanUtils.copyProperties(uiProductMappingVO, prodMnoMappingVO, new String[]{});
            prodMnoMappings.add(prodMnoMappingVO);
        }
        return prodMnoMappings;
    }
   

    public List<UIProdMnoMappingInfoVO> transformProductMappingVOListtoUIrodMnoMappingVOList(
            List<ProdMnoMappingInfoVO> prodMnoMappingInfoVOList)
    {
        List<UIProdMnoMappingInfoVO> uiProdMnoMappingInfoVOList = new ArrayList<UIProdMnoMappingInfoVO>();
        for (ProdMnoMappingInfoVO prodMnoMappingInfoVO : prodMnoMappingInfoVOList)
        {
            UIProdMnoMappingInfoVO uiProdMnoMappingInfoVO = new UIProdMnoMappingInfoVO();
            BeanUtils.copyProperties(prodMnoMappingInfoVO, uiProdMnoMappingInfoVO);
            uiProdMnoMappingInfoVOList.add(uiProdMnoMappingInfoVO);
        }
        return uiProdMnoMappingInfoVOList;
    }

    public com.asiainfo.meo.web.product.model.vo.UIProductBasicVO transformProductBasicInfoVOtoUIProductBasicVO(
            ProductBasicInfoVO productBasicInfoVO)
    {
        UIProductBasicVO UIProductBasicVO = new UIProductBasicVO();
        if (productBasicInfoVO != null)
            BeanUtils.copyProperties(productBasicInfoVO, UIProductBasicVO);
        UIProductBasicVO.setProductValidDate(productBasicInfoVO.getProductValidDate() != null ? productBasicInfoVO.getProductValidDate().getTime() : null);
        UIProductBasicVO.setProductExpiredDate(productBasicInfoVO.getProductExpiredDate() != null ? productBasicInfoVO.getProductExpiredDate().getTime() : null);
        return UIProductBasicVO;
    }

    public UIProductSpecialVO transformProductSpecialVOtoUIProductSpecialVO(ProductSpecInfoVO productSpecInfoVO)
    {
        UIProductSpecialVO uiProductSpecialVO = new UIProductSpecialVO();
        if (productSpecInfoVO != null)
            BeanUtils.copyProperties(productSpecInfoVO, uiProductSpecialVO);
        return uiProductSpecialVO;
    }

    public List<UIProdPriceVO> transformProdPriceVOListtoUIProdPriceVOList(List<ProdPriceVO> prodPriceVOList)
    {
        List<UIProdPriceVO> uiProdPriceVOList = new ArrayList<UIProdPriceVO>();
        for (ProdPriceVO prodPriceVO : prodPriceVOList)
        {
            UIProdPriceVO UIProdPriceVO = new UIProdPriceVO();
            BeanUtils.copyProperties(prodPriceVO, UIProdPriceVO);
            uiProdPriceVOList.add(UIProdPriceVO);
        }
        return uiProdPriceVOList;
    }

    public PriceVO transformUIPriceVOToPriceVO(UIPriceVO uiPriceVO)
    {
        PriceVO priceVO = new PriceVO();
        priceVO.setPrice(uiPriceVO.getPrice());
        priceVO.setPriceId(uiPriceVO.getPriceId());
        priceVO.setPriceName(uiPriceVO.getPriceName());
        priceVO.setPriceType(1);
        priceVO.setPriceUnit(uiPriceVO.getPriceUnit());
        return priceVO;
    }

    public PageInfo<UIPriceVO> transformPagePriceVOToPageUIPriceVO(PageInfo<PriceVO> pageInfo)
    {
        PageInfo<UIPriceVO> page = new PageInfo<UIPriceVO>();
        List<UIPriceVO> listUI = new ArrayList<UIPriceVO>();
        List<PriceVO> list = pageInfo.getResult();
        Integer pageNo = pageInfo.getCurrentPage();
        Integer pageSize = pageInfo.getPageSize();
        Integer totalSize = pageInfo.getTotalSize();
        for (PriceVO priceVO : list)
        {
            UIPriceVO uiPriceVO = new UIPriceVO();
            uiPriceVO.setPrice(priceVO.getPrice());
            uiPriceVO.setPriceId(priceVO.getPriceId());
            uiPriceVO.setPriceName(priceVO.getPriceName());
            uiPriceVO.setPriceType(priceVO.getPriceType());
            uiPriceVO.setPriceUnit(priceVO.getPriceUnit());
            uiPriceVO.setUnit(priceVO.getUnit());
            uiPriceVO.setType(priceVO.getType());
            listUI.add(uiPriceVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, listUI);
    }

    public UIPriceVO transformPriceVOToUIPriceVO(PriceVO priceVO)
    {
        if (ValidateUtil.isEmpty(priceVO))
        {
            return null;
        }
        UIPriceVO uiPriceVO = new UIPriceVO();
        uiPriceVO.setPrice(priceVO.getPrice());
        uiPriceVO.setPriceId(priceVO.getPriceId());
        uiPriceVO.setPriceName(priceVO.getPriceName());
        uiPriceVO.setPriceType(priceVO.getPriceType());
        uiPriceVO.setType(priceVO.getType());
        uiPriceVO.setPriceUnit(priceVO.getPriceUnit());
        uiPriceVO.setUnit(priceVO.getUnit());
        return uiPriceVO;
    }

    public DiscountRuleVO transformUIDiscountRuleVOToDiscountRuleVO(UIDiscountRuleVO uiDiscountRuleVO)
    {
        DiscountRuleVO discountRuleVO = new DiscountRuleVO();
        discountRuleVO.setDiscountId(uiDiscountRuleVO.getDiscountId());
        discountRuleVO.setDiscountName(uiDiscountRuleVO.getDiscountName());
        discountRuleVO.setDiscountPrice(uiDiscountRuleVO.getDiscountPrice());
        discountRuleVO.setDiscountTypeId(uiDiscountRuleVO.getDiscountTypeId());
        discountRuleVO.setMaxCount(uiDiscountRuleVO.getMaxCount());
        discountRuleVO.setMinCount(uiDiscountRuleVO.getMinCount());
        discountRuleVO.setPriceUnit(uiDiscountRuleVO.getPriceUnit());
        return discountRuleVO;
    }

    public PageInfo<UIDiscountRuleVO> transformDiscountRuleVOToPageDiscountRuleVO(PageInfo<DiscountRuleVO> pageInfo)
    {
        PageInfo<UIDiscountRuleVO> page = new PageInfo<UIDiscountRuleVO>();
        List<UIDiscountRuleVO> listUI = new ArrayList<UIDiscountRuleVO>();
        List<DiscountRuleVO> list = pageInfo.getResult();
        Integer pageNo = pageInfo.getCurrentPage();
        Integer pageSize = pageInfo.getPageSize();
        Integer totalSize = pageInfo.getTotalSize();
        for (DiscountRuleVO discountRuleVO : list)
        {
            UIDiscountRuleVO uiDiscountRuleVO = new UIDiscountRuleVO();
            uiDiscountRuleVO.setDiscountId(discountRuleVO.getDiscountId());
            uiDiscountRuleVO.setDiscountName(discountRuleVO.getDiscountName());
            uiDiscountRuleVO.setDiscountPrice(discountRuleVO.getDiscountPrice());
            uiDiscountRuleVO.setDiscountTypeId(discountRuleVO.getDiscountTypeId());
            uiDiscountRuleVO.setDiscountType(discountRuleVO.getDiscountType());
            uiDiscountRuleVO.setMaxCount(discountRuleVO.getMaxCount());
            uiDiscountRuleVO.setMinCount(discountRuleVO.getMinCount());
            uiDiscountRuleVO.setPriceUnit(discountRuleVO.getPriceUnit());
            uiDiscountRuleVO.setUnit(discountRuleVO.getUnit());
            listUI.add(uiDiscountRuleVO);
        }
        return page.createPageInfo(pageNo, pageSize, totalSize, listUI);
    }

    public UIDiscountRuleVO transformDiscountRuleVOToUIDiscountRuleVO(DiscountRuleVO discountRuleVO)
    {
        if (ValidateUtil.isEmpty(discountRuleVO))
        {
            return null;
        }
        UIDiscountRuleVO uiDiscountRuleVO = new UIDiscountRuleVO();
        uiDiscountRuleVO.setDiscountId(discountRuleVO.getDiscountId());
        uiDiscountRuleVO.setDiscountName(discountRuleVO.getDiscountName());
        uiDiscountRuleVO.setDiscountPrice(discountRuleVO.getDiscountPrice());
        uiDiscountRuleVO.setDiscountTypeId(discountRuleVO.getDiscountTypeId());
        uiDiscountRuleVO.setDiscountType(discountRuleVO.getDiscountType());
        uiDiscountRuleVO.setMaxCount(discountRuleVO.getMaxCount());
        uiDiscountRuleVO.setMinCount(discountRuleVO.getMinCount());
        uiDiscountRuleVO.setPriceUnit(discountRuleVO.getPriceUnit());
        uiDiscountRuleVO.setUnit(discountRuleVO.getUnit());
        return uiDiscountRuleVO;
    }
}