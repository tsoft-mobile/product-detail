package com.tsoftmobile.library.productdetail.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * User: utku
 * Date: 30.10.2019
 * Time: 09:32
 * Web : http://www.utkukutlu.com
 * Github : http://www.github.com/utkukutlu
 */

data class Product(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("is_new_product")
    val isNewProduct: Int? = null,
    @SerializedName("is_active")
    val isActive: Int,
    @SerializedName("variant_id")
    val variantId: String? = null,
    @SerializedName("category_id")
    val categoryId: String? = null,
    @SerializedName("category_id_path")
    val categoryIdPath: String? = null,
    @SerializedName("category_ids")
    val categoryIds: String? = null,
    @SerializedName("price_sell")
    val priceSell: Double = 0.0,
    @SerializedName("price_visitor")
    val priceVisitor: String? = null,
    @SerializedName("price_buy")
    val priceBuy: Double = 0.0,
    @SerializedName("price_not_discounted")
    val priceNotDiscounted: Double = 0.0,
    @SerializedName("supplier_product_code")
    val supplierProductCode: String? = null,
    @SerializedName("currency_id")
    val currencyId: String? = null,
    @SerializedName("vat")
    val vat: Int = 0,
    @SerializedName("has_image")
    val hasImage: String? = null,
    @SerializedName("has_variant")
    val hasVariant: String? = null,
    @SerializedName("display_vat")
    val displayVat: String? = null,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("brand_id")
    val brandId: String,
    @SerializedName("brand_url")
    val brandUrl: String? = null,
    @SerializedName("brand_image")
    val brandImage: String? = null,
    @SerializedName("model")
    val model: String? = null,
    @SerializedName("model_id")
    val modelId: String? = null,
    @SerializedName("stock")
    val stock: Double = 0.0,
    @SerializedName("stock_unit")
    val stockUnit: String,
    @SerializedName("stock_unit_id")
    val stockUnitId: String? = null,
    @SerializedName("cbm")
    val cbm: Double,
    @SerializedName("video_info")
    val videoInfo: List<String>,
    @SerializedName("stock_increment")
    val stockIncrement: Double,
    @SerializedName("product_code")
    val productCode: String,
    @SerializedName("short_description")
    val shortDescription: String,
    @SerializedName("additional_field_1")
    val additionalField1: String? = null,
    @SerializedName("additional_field_2")
    val additionalField2: String? = null,
    @SerializedName("additional_field_3")
    val additionalField3: String? = null,
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("store_id")
    val storeId: Int,
    @SerializedName("is_discount_active")
    val isDiscountActive: String?=null,
    @SerializedName("is_display_discounted_active")
    val isDisplayDiscountedActive: Int,
    @SerializedName("discount_percent")
    val discountPercent: Double,
    @SerializedName("discount_percent_raw")
    val discountPercentRaw: Double,
    @SerializedName("cargo_free")
    val cargoFree: Double,
    @SerializedName("cargo_free_member")
    val cargoFreeMember: String,
    @SerializedName("cargo_free_vendor")
    val cargoFreeVendor: String,
    @SerializedName("delivery_time")
    val deliveryTime: String,
    @SerializedName("registration_date")
    val registrationDate: String,
    @SerializedName("is_multiple_discount_active")
    val isMultipleDiscountActive: Int,
    @SerializedName("min_sale_count")
    val minSaleCount: Double,
    @SerializedName("max_sale_count")
    val maxSaleCount: Double,
    @SerializedName("min_order_count")
    val minOrderCount: Double,
    @SerializedName("max_order_count")
    val maxOrderCount: Double,
    @SerializedName("category_url")
    val categoryUrl: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("category_path")
    val categoryPath: String,
    @SerializedName("filter_category_id")
    val filterCategoryId: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("model_url")
    val modelUrl: String,
    @SerializedName("in_stock")
    val inStock: Boolean,
    @SerializedName("is_display_product")
    val isDisplayProduct: String? = null,
    @SerializedName("variant_name")
    val variantName: String,
    @SerializedName("show_vendor")
    val showVendor: String? = null,
    @SerializedName("show_promotion")
    val showPromotion: Int,
    @SerializedName("active_on_groups")
    val activeOnGroups: String,
    @SerializedName("comment_rank")
    val commentRank: Double,
    @SerializedName("s1")
    val s1: String? = null,
    @SerializedName("symbol_data1")
    val symbolData1: String? = null,
    @SerializedName("symbol_tag1")
    val symbolTag1: String? = null,
    @SerializedName("s2")
    val s2: String? = null,
    @SerializedName("symbol_data2")
    val symbolData2: String? = null,
    @SerializedName("symbol_tag2")
    val symbolTag2: String? = null,
    @SerializedName("s3")
    val s3: String? = null,
    @SerializedName("symbol_data3")
    val symbolData3: String? = null,
    @SerializedName("symbol_tag3")
    val symbolTag3: String? = null,
    @SerializedName("s4")
    val s4: String? = null,
    @SerializedName("symbol_data4")
    val symbolData4: String? = null,
    @SerializedName("symbol_tag4")
    val symbolTag4: String? = null,
    @SerializedName("s5")
    val s5: String? = null,
    @SerializedName("symbol_data5")
    val symbolData5: String? = null,
    @SerializedName("symbol_tag5")
    val symbolTag5: String? = null,
    @SerializedName("s6")
    val s6: String? = null,
    @SerializedName("symbol_data6")
    val symbolData6: String? = null,
    @SerializedName("symbol_tag6")
    val symbolTag6: String? = null,
    @SerializedName("s7")
    val s7: String? = null,
    @SerializedName("symbol_data7")
    val symbolData7: String? = null,
    @SerializedName("symbol_tag7")
    val symbolTag7: String? = null,
    @SerializedName("s8")
    val s8: String? = null,
    @SerializedName("symbol_data8")
    val symbolData8: String? = null,
    @SerializedName("symbol_tag8")
    val symbolTag8: String? = null,
    @SerializedName("s9")
    val s9: String? = null,
    @SerializedName("symbol_data9")
    val symbolData9: String? = null,
    @SerializedName("symbol_tag9")
    val symbolTag9: String? = null,
    @SerializedName("s10")
    val s10: String? = null,
    @SerializedName("symbol_data10")
    val symbolData10: String? = null,
    @SerializedName("symbol_tag10")
    val symbolTag10: String? = null,
    @SerializedName("variant_code")
    val variantCode: String,
    @SerializedName("price_guest")
    val priceGuest: Double,
    @SerializedName("image_list")
    val imageList: ArrayList<ProductImage>,
    @SerializedName("image")
    val image: ProductImage,
    @SerializedName("image_count")
    val imageCount: Int,
    @SerializedName("image_ratio")
    val imageRatio: Double,
    @SerializedName("price_credit_cart")
    val priceCreditCart: Double,
    @SerializedName("price_money_order")
    val priceMoneyOrder: Double,
    @SerializedName("money_order_percent")
    val moneyOrderPercent: Double,
    @SerializedName("is_money_order_active")
    val isMoneyOrderActive: Boolean,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("target_currency")
    val targetCurrency: String,
    @SerializedName("price_sell_default")
    val priceSellDefault: Double,
    @SerializedName("price_raw")
    val priceRaw: Double,
    @SerializedName("target_currency_original")
    val targetCurrencyOriginal: String,
    @SerializedName("combination_id")
    val combinationId: Int,
    @SerializedName("can_add_cart_in_list")
    val canAddCartInList: Boolean,
    @SerializedName("variant_feature1_title")
    val variantFeature1Title: String? = null,
    @SerializedName("variant_feature2_title")
    val variantFeature2Title: String? = null,
    @SerializedName("variant_feature1_selected")
    val variantFeature1Selected: String? = null,
    @SerializedName("variant_feature2_selected")
    val variantFeature2Selected: String? = null,
    val variants: ArrayList<ProductVariant>? = null,
    val detail: String? = null
) : Serializable